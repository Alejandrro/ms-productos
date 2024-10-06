package com.catalogos.productos.exception;

import java.util.List;

import com.catalogos.productos.enums.EMensajeException;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final EMensajeException option;

    private final ApiError body;

    private final String error;

    public ApiException(String folio, List<String> detalles, EMensajeException option) {
        super(option.getMensaje());
        this.option = option;
        this.body = new ApiError(option.getCodigo(), option.getMensaje(), folio, option.getInfo(), detalles);
        this.error = "";

    }

    public ApiException(List<String> detalles, EMensajeException option) {
        super();
        this.option = option;
        this.body = new ApiError(option.getCodigo(), option.getMensaje(), null, option.getInfo(), detalles);
        this.error = "";
    }
}