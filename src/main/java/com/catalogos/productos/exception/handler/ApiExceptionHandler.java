package com.catalogos.productos.exception.handler;

import com.catalogos.productos.exception.ApiError;
import com.catalogos.productos.exception.ApiException;
import com.catalogos.productos.utils.Util;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.catalogos.productos.constant.Constantes.MSJ_CAMPO_REQUERIDO;
import static com.catalogos.productos.constant.Constantes.PARAMETROS_NO_VALIDOS;
import static com.catalogos.productos.enums.EMensajeException.E400;
import static com.catalogos.productos.enums.EMensajeException.E500;


@RestControllerAdvice
public class ApiExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final Util util;

    public ApiExceptionHandler(Util util) {
        this.util = util;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions400(MethodArgumentNotValidException ex) {

        List<String> detalles = ex.getBindingResult().getFieldErrors().stream().map(this::generarMensajeCampo)
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(E400, util.getFolio(), detalles);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseEntity<ApiError> handleValidationExceptions401(MethodArgumentNotValidException ex) {

        List<String> detalles = ex.getBindingResult().getFieldErrors().stream().map(this::generarMensajeCampo)
                .collect(Collectors.toList());

        ApiError apiError = new ApiError(E400, util.getFolio(), detalles);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity<ApiError> handleValidationExceptions404(MissingServletRequestParameterException ex) {

        List<String> detalles = new ArrayList<>();

        detalles.add((MSJ_CAMPO_REQUERIDO.concat(ex.getParameterName())));

        ApiError apiError = new ApiError(E400, util.getFolio(), detalles);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }


    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {HttpServerErrorException.InternalServerError.class})
    public ResponseEntity<ApiError> handleValidationExceptions500(HttpServerErrorException.InternalServerError ex) {

        LOGGER.info("Dentro del Handeler de 500 default");

        ApiError apiError = new ApiError(E500, util.getFolio(), new ArrayList<>());

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public String generarMensajeCampo(FieldError error) {

        String fieldName = error.getField();
        String errorMessage = error.getDefaultMessage();

        LOGGER.error("Campo :{} Error: {}", fieldName, errorMessage);

        return MSJ_CAMPO_REQUERIDO.concat(fieldName);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleValidationExceptionsBody(HttpMessageNotReadableException ex) {

        LOGGER.info("El error es ={}", ex.getLocalizedMessage());
        ApiError apiError = new ApiError(E400, util.getFolio(), List.of(PARAMETROS_NO_VALIDOS));

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    //ESTE  HANDLER 500 CAE SI EN VIAS UN EROR DE TIPO Exception
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleValidateError(Exception ex) {

        LOGGER.error("EL ERROR ES EL SIGUIENTE ={}", ex.getMessage());

        LOGGER.error("EL TIPO DE ERROR ES ={}", ex.getClass());

        ApiError apiError = new ApiError(E500, util.getFolio(), List.of("Problema interno en el servidor"));

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiError> handleApiException(ApiException ex) {

        LOGGER.error("EL ERROR ES EL SIGUIENTES ={}", ex.getBody().getDetalles());

        ResponseEntity<ApiError> response;

        response = switch (ex.getOption()) {
            case E400 ->
                // Error 400
                    new ResponseEntity<>(buildApiError(ex), HttpStatus.BAD_REQUEST);
            case E404 ->
                // Error 404
                    new ResponseEntity<>(buildApiError(ex), HttpStatus.NOT_FOUND);
            case E500 ->
                // Error 500
                    new ResponseEntity<>(buildApiError(ex), HttpStatus.INTERNAL_SERVER_ERROR);
            default ->
                // Error default
                    new ResponseEntity<>(ex.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
        };

        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> numberFormatException(MethodArgumentTypeMismatchException ex) {

        LOGGER.info(ex.getMessage());
        List<String> detalles = new ArrayList<>();

        detalles.add(PARAMETROS_NO_VALIDOS);

        ApiError apiError = new ApiError(E400, util.getFolio(), detalles);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    public ApiError buildApiError(ApiException e) {

        ApiError apiError = new ApiError();

        apiError.setCodigo(e.getOption().getCodigo());
        apiError.setMensaje(e.getOption().getMensaje());
        apiError.setInfo(e.getOption().getInfo());
        apiError.setFolio(util.getFolio());
        apiError.setDetalles(e.getBody().getDetalles());

        return apiError;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> argumentoInvalido(IllegalArgumentException ex) {

        LOGGER.info(ex.getMessage());

        List<String> detalles = new ArrayList<>();

        detalles.add(PARAMETROS_NO_VALIDOS);

        ApiError apiError = new ApiError(E400, util.getFolio(), detalles);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}