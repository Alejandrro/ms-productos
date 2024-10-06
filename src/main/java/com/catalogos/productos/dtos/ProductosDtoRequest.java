package com.catalogos.productos.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductosDtoRequest {

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;

    private String url;

    private Long fechaCreacion;
}