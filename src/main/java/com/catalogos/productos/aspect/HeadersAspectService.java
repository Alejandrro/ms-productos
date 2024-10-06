package com.catalogos.productos.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

import static com.catalogos.productos.constant.Constantes.*;
import static com.catalogos.productos.utils.ValidaHeadersMicro.*;

@Aspect
@Component
public class HeadersAspectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HeadersAspectService.class);

    private final ObjectMapper map;

    public HeadersAspectService(ObjectMapper map) {
        this.map = map;
    }

    @Before(value = "@annotation(ValidHeaders)")
    public void validarHeaders(JoinPoint joinPoint) {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();

        ValidHeaders anotacion = method.getAnnotation(ValidHeaders.class);

        String verbo = anotacion.verbo();

        String endPoint = recurso(method.getAnnotations());

        LOGGER.info("El recurso es {} el verbo es {}", endPoint, verbo);

        Map<String, String> headers = map.convertValue(joinPoint.getArgs()[0], new TypeReference<>() {
        });

        examinarRecursos(endPoint, verbo, headers);

        if ("/productos-servicios/proveedores".equals(endPoint) && VERBOS.get(3).equals(verbo))
            headersDelete(headers);

        if ("/productos-servicios/transaccionales".equals(endPoint) && VERBOS.get(2).equals(verbo))
            headersGetProductos(headers);

        if ("/productos-servicios-detalles/transaccionales".equals(endPoint) && VERBOS.get(2).equals(verbo))
            headersGetProductosDetalles(headers);
    }

    private String recurso(Annotation[] anoAnnotations) {

        if (anoAnnotations[2] instanceof PostMapping postMapping) {
            return postMapping.value()[0];
        }

        if (anoAnnotations[2] instanceof PutMapping putMapping) {
            return putMapping.value()[0];
        }

        if (anoAnnotations[2] instanceof DeleteMapping deleteMapping) {
            return deleteMapping.value()[0];
        }

        if (anoAnnotations[2] instanceof GetMapping getMapping) {
            return getMapping.value()[0];
        }

        return "";
    }

    private void examinarRecursos(String endPoint, String verbo, Map<String, String> headers) {

        if (RECURSO_DEF.equals(endPoint) && VERBOS.get(0).equals(verbo))
            headersPutPost(headers);

        if (RECURSO_DEF.equals(endPoint) && VERBOS.get(1).equals(verbo))
            headersPutPost(headers);

        if (RECURSO_DEF.equals(endPoint) && VERBOS.get(2).equals(verbo))
            headersGetProductos(headers);

        if ("/productos-servicios-detalles".equals(endPoint) && VERBOS.get(2).equals(verbo))
            headersGetProductosDetalles(headers);

        if ("/productos-servicios/proveedores".equals(endPoint) && VERBOS.get(1).equals(verbo))
            headersPutPost(headers);
    }
}