package com.catalogos.productos.enums;

import java.util.List;
import java.util.stream.Stream;

import com.catalogos.productos.exception.ApiException;
import lombok.Getter;

import static com.catalogos.productos.constant.Constantes.PARAMETROS_NO_VALIDOS;

@Getter
public enum ERegex {

    TEXTO("^[a-zA-Z\\u00C0-\\u017F\\s\\-\\,\\.\\'\\´\\`\\:\\;\\(\\)\\[\\]\\{\\}]+$", ETipoString.TEXTO),
    TEXTO_NUMEROS("^[A-Za-z0-9]+$", ETipoString.TEXTO_NUMEROS),
    ENTEROS("\\d+", ETipoString.ENTEROS),
    DECIMALES("-?\\d+(.\\d+)?", ETipoString.DECIMALES),
    USUARIO("^[a-zA-Z0-9]{1,19}-[a-zA-Z0-9]{1,}-[a-zA-Z0-9]+$", ETipoString.USUARIO),
    RFC("^(?:[A-Z]{4}[0-9]{6}[A-Z0-9]{3}|[A-Z]{3}[0-9]{6}[A-Z0-9-]{3,4}|[A-Z0-9-]{8,20})$", ETipoString.RFC),
    FECHAISO("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z$", ETipoString.FECHAISO),
    TIPOESTATUS("^[a-zA-Z\\s]+$", ETipoString.TIPOESTATUS),
    TEXTO_TEST(".*", ETipoString.TEXTO_TEST),
    CORREOS("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", ETipoString.CORREOS),
    RELACION("(Fabricante|Distribuidor\\s+autorizado|Distribuidor\\s+exclusivo|Servicios)", ETipoString.RELACION),
    ID("^[0-9A-Fa-f]+$", ETipoString.ID);

    private final String regex;

    private final ETipoString eTipoString;

    ERegex(String regex, ETipoString eTipoString) {
        this.regex = regex;
        this.eTipoString = eTipoString;
    }

    public static String getRegex(ETipoString tipoString) {

        ERegex eRegex = Stream.of(ERegex.values()).filter(f -> f.eTipoString.equals(tipoString)).findFirst()
                .orElseThrow(() -> new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400));

        return eRegex.getRegex();
    }
}