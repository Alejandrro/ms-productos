package com.catalogos.productos.controllers;

import com.catalogos.productos.aspect.ValidHeaders;
import com.catalogos.productos.dtos.GenericResponse;
import com.catalogos.productos.dtos.ProductosDtoRequest;
import com.catalogos.productos.models.Productos;
import com.catalogos.productos.services.ProductosServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("productos")
public class ProductosController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductosController.class);

    private final ProductosServiceImpl service;

    public ProductosController(ProductosServiceImpl service) {
        this.service = service;
    }

    @ValidHeaders
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public <T> GenericResponse<T> savedProductos(@Valid @RequestBody ProductosDtoRequest request) {

        LOGGER.info("INICIANDO EL PROCESO DE GUARDADO DEL PRODUCTO = {}", request);

        return service.savedProducts(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public GenericResponse<List<Productos>> getAllProducts() {

        return service.getAllProductos();
    }
}