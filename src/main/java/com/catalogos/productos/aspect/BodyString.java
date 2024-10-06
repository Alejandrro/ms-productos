package com.catalogos.productos.aspect;

import com.catalogos.productos.enums.ETipoString;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface BodyString {

    ETipoString tipo() default ETipoString.TEXTO_TEST;

    boolean requerido() default true;

    ETipoString tipoRegex() default ETipoString.TEXTO_TEST;

}