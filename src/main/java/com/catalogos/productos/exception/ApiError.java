package com.catalogos.productos.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.catalogos.productos.enums.EMensajeException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiError implements Serializable {

    private String codigo;

    private String mensaje;

    private String folio;

    @JsonIgnore
    private String info;

    private List<String> detalles;

    public ApiError() {
        super();
    }

    public ApiError(String codigo, String mensaje, String folio, String info, List<String> detalles) {
        super();
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.folio = folio;
        this.info = info;
        this.detalles = Collections.unmodifiableList(detalles);
    }

    public ApiError(EMensajeException error, String folio, List<String> detalles) {
        super();
        this.codigo = error.getCodigo();
        this.mensaje = error.getMensaje();
        this.info = error.getInfo();
        this.folio = folio;
        this.detalles = Collections.unmodifiableList(detalles);
    }

    public List<String> getDetalles() {
        if (detalles == null || detalles.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(detalles);
    }

    public void setDetalles(List<String> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return;
        }

        this.detalles = Collections.unmodifiableList(detalles);
    }
}