package com.catalogos.productos.enums;

import lombok.Getter;

@Getter
public enum EMensajeException {

    E400("400.Andy-Mandy-Gestion-Productos.1400",
            "Parametros no validos, por favor valide su informacion.",
            "https://baz-developer.bancoazteca.com.mx/info#400.Andy-Mandy-Gestion-Productos"),

    E401("401.Necsus-Proveedores-Productos-Servicios.1401",
            "No estas autorizado, favor de validar",
            "https://baz-developer.bancoazteca.com.mx/info#400.Andy-Mandy-Gestion-Productos"),

    E500("500.Necsus-Proveedores-Productos-Servicios.1500",
            "Problema interno en el servidor, favor de validar.",
            "https://baz-developer.bancoazteca.com.mx/info#500.Andy-Mandy-Gestion-Productos"),

    E404("404.Necsus-Proveedores-Productos-Servicios.1404",
            "Informacion no encontrada, favor de validar.",
            "https://baz-developer.bancoazteca.com.mx/info#404.Andy-Mandy-Gestion-Productos");

    private final String codigo;

    private final String mensaje;

    private final String info;

    EMensajeException(String codigo, String mensaje, String info) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.info = info;
    }
}