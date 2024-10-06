package com.catalogos.productos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    private String mensaje;

    private String folio;

    private T resultado;

    public GenericResponse() {
        this.mensaje = "Operación exitosa";
        this.folio = UUID.randomUUID().toString();
    }

    public GenericResponse(T resultado) {
        this.mensaje = "Operación exitosa";
        this.folio = UUID.randomUUID().toString();
        this.resultado = resultado;
    }

}