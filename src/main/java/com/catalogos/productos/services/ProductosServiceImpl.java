package com.catalogos.productos.services;

import com.catalogos.productos.dtos.GenericResponse;
import com.catalogos.productos.dtos.ProductosDtoRequest;
import com.catalogos.productos.mapper.ProductosMapper;
import com.catalogos.productos.models.Productos;
import com.catalogos.productos.repositories.ProductosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductosServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductosServiceImpl.class);

    private final ProductosRepository repo;

    private final ProductosMapper mapper;

    public ProductosServiceImpl(ProductosRepository repo, ProductosMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    public <T> GenericResponse<T> savedProductos(ProductosDtoRequest request) {

        Productos productos = mapper.maping(request);

        repo.save(productos);

        LOGGER.info(" Datos Insertados ={}", productos);

        return new GenericResponse<>();
    }

    public List<Productos> getAllProductos() {

        return repo.findAll();
    }
}
