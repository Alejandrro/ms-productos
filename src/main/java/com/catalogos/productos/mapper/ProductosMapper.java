package com.catalogos.productos.mapper;

import com.catalogos.productos.dtos.ProductosDtoRequest;
import com.catalogos.productos.models.Productos;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductosMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductosMapper.class);

    private final DtoMapper mapper = Mappers.getMapper(DtoMapper.class);

    public Productos maping(ProductosDtoRequest register) {

        Productos doc = mapper.setProductos(register);

        LOGGER.info(" Mapper Objeto after ={}", doc);

        return doc;
    }

}