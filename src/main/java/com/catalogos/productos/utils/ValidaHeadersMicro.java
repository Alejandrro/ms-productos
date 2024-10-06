package com.catalogos.productos.utils;

import com.catalogos.productos.enums.EMensajeException;
import com.catalogos.productos.enums.ETipoString;
import com.catalogos.productos.exception.ApiException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.catalogos.productos.constant.Constantes.*;
import static com.catalogos.productos.enums.ERegex.getRegex;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidaHeadersMicro {

    public static void headersPutPost(Map<String, String> headers) {

        List<String> headersRequeridos = List.of(IDIOMA, TOKEN_USUARIO, AUTORIZADOR,
                ORIGEN, ID_USUARIO.getFirst());

        if (!headers.containsKey(ID_ACCESO))
            throw new ApiException(List.of(NO_AUTORIZADO), EMensajeException.E401);

        if (!headers.keySet().containsAll(headersRequeridos))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get(ID_USUARIO.getFirst())))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
    }

    public static void headersGetProductos(Map<String, String> headers) {

        List<String> headersRequeridos = List.of(IDIOMA, TOKEN_USUARIO, AUTORIZADOR,
                ORIGEN, ID_PROVEEDOR);

        if (!headers.containsKey(ID_ACCESO))
            throw new ApiException(List.of(NO_AUTORIZADO), EMensajeException.E401);

        if (!headers.keySet().containsAll(headersRequeridos))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get(ID_PROVEEDOR)))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
    }

    public static void headersGetProductosDetalles(Map<String, String> headers) {

        List<String> headersRequeridos = List.of(IDIOMA, TOKEN_USUARIO, AUTORIZADOR,
                ORIGEN, ID_PROVEEDOR, ID_PROSER);

        if (!headers.containsKey(ID_ACCESO))
            throw new ApiException(List.of(NO_AUTORIZADO), EMensajeException.E401);

        if (!headers.keySet().containsAll(headersRequeridos))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get(ID_PROVEEDOR)))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get(ID_PROSER)))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
    }

    public static void headersDelete(Map<String, String> headers) {

        List<String> headersRequeridos = List.of(IDIOMA, TOKEN_USUARIO, AUTORIZADOR,
                ORIGEN, "x-id-usuario-cambios", "x-id-entidad-proveedor", ID_PROSER);

        if (!headers.containsKey(ID_ACCESO))
            throw new ApiException(List.of(NO_AUTORIZADO), EMensajeException.E401);

        if (!headers.keySet().containsAll(headersRequeridos))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get(ID_PROSER)))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get("x-id-usuario-cambios")))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);

        if (!Pattern.matches(getRegex(ETipoString.ID), headers.get("x-id-entidad-proveedor")))
            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
    }
}