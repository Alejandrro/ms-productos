package com.catalogos.productos.utils;


import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import com.catalogos.productos.aspect.BodyString;
import com.catalogos.productos.enums.EMensajeException;
import com.catalogos.productos.enums.ETipoString;
import com.catalogos.productos.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import static com.catalogos.productos.constant.Constantes.PARAMETROS_NO_VALIDOS;
import static com.catalogos.productos.enums.ERegex.getRegex;

public class ValidaString extends JsonDeserializer<String> implements ContextualDeserializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidaString.class);
    private ETipoString anotacion;
    private boolean isRequired;

    public ValidaString(ETipoString anotacion, boolean isRequired) {
        this.anotacion = anotacion;
        this.isRequired = isRequired;
    }

    public ValidaString() {
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) {

        if (property == null) {
            LOGGER.error("fallo al obtener las anotaciones");
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }

        anotacion = property.getAnnotation(BodyString.class).tipo();
        isRequired = property.getAnnotation(BodyString.class).requerido();
        return new ValidaString(anotacion, isRequired);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        JsonNode node = p.readValueAsTree();

        String keyName = p.getParsingContext().getCurrentName();

        LOGGER.info("nombre de la propiedad = {}", keyName);

        if (!node.isTextual()) {
            LOGGER.error("No es un texto " + keyName + node);
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }

        if (isRequired) {
            LOGGER.info("validacion atributo requerido " + keyName + " " + node);
            isEmpty(node.asText(), keyName);
            isBlank(node.asText(), keyName);

            validaNodo(node, keyName);
            return node.asText();
        }

        if (node.asText().isEmpty()) {
            return node.asText();
        }
        isBlank(node.asText(), keyName);
        validaNodo(node, keyName);

        return node.asText();
    }

    public void validaNodo(JsonNode nodo, String keyName) {
        LOGGER.info("El tipo de anotacion es: " + anotacion);
        if (!Pattern.matches(getRegex(anotacion), nodo.asText())) {
            LOGGER.error("No cumple el regex " + keyName + " " + nodo.asText());
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }

    }

    public void isBlank(String node, String keyName) {
        if (node.isBlank()) {
            LOGGER.error("el atributo " + keyName + " es incorrecto Texto con espacios enblanco");
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }
    }

    public void isEmpty(String node, String keyName) {
        if (node.isEmpty()) {
            LOGGER.error("el atributo " + keyName + " es incorrecto Texto en blanco");
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }
    }
}