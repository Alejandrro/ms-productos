package com.catalogos.productos.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constantes {

    public static final String MSJ_CAMPO_REQUERIDO = "Campo Requerido: ";

    public static final String PARAMETROS_NO_VALIDOS = "Parametros no validos, por favor valide su informacion.";

    public static final List<String> ID_USUARIO = List.of("x-id-usuario", "x-id-usuario-cambios");

    public static final String ID_ACCESO = "x-id-acceso";

    public static final String NO_AUTORIZADO = "No estas autorizado, favor de validar.";

    public static final List<String> VERBOS = List.of("POST", "PUT", "GET", "DELETE");

    public static final String RECURSO_DEF = "/productos-servicios";

    public static final String TOKEN_USUARIO = "x-token-usuario";

    public static final String IDIOMA = "x-idioma";

    public static final String AUTORIZADOR = "x-autorizador";

    public static final String ORIGEN = "x-nombre-origen";

    public static final String ID_PROVEEDOR = "x-id-proveedor";

    public static final String ID_PROSER = "x-id-producto-servicio";

    public static final String INFORMACION_NO_ENCONTRADA = "informacion no encontrada";
}