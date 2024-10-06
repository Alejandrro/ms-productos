package com.catalogos.productos.mapper;


import com.catalogos.productos.dtos.ProductosDtoRequest;
import com.catalogos.productos.models.Productos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;

@Mapper
public interface DtoMapper {

    @Mapping(target = "descripcion", source = "descripcion")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "precio", source = "precio")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "fechaCreacion", source = "fechaCreacion", qualifiedByName = "fecha")
    Productos setProductos(ProductosDtoRequest request);

    @Named("fecha")
    default Timestamp toFecha(Long fecha) {

        return new Timestamp(fecha);
    }
}