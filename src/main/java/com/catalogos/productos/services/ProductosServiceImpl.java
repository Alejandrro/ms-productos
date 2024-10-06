package com.catalogos.productos.services;

import com.catalogos.productos.dtos.GenericResponse;
import com.catalogos.productos.dtos.ProductosDtoRequest;
import com.catalogos.productos.enums.EMensajeException;
import com.catalogos.productos.exception.ApiException;
import com.catalogos.productos.mapper.ProductosMapper;
import com.catalogos.productos.models.Estatus;
import com.catalogos.productos.models.Productos;
import com.catalogos.productos.repositories.EstatusRepository;
import com.catalogos.productos.repositories.ProductosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.catalogos.productos.constant.Constantes.INFORMACION_NO_ENCONTRADA;
import static com.catalogos.productos.constant.Constantes.PARAMETROS_NO_VALIDOS;
import static com.catalogos.productos.models.Estatus.ACTIVO;

@Service
public class ProductosServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductosServiceImpl.class);

    private final ProductosRepository repo;

    private final ProductosMapper mapper;

    private final EstatusRepository repository;

    public ProductosServiceImpl(ProductosRepository repo, ProductosMapper mapper, EstatusRepository repository) {
        this.repo = repo;
        this.mapper = mapper;
        this.repository = repository;
    }

    public <T> GenericResponse<T> savedProducts(ProductosDtoRequest request) {

        Productos productos = mapper.maping(request);

        Estatus estatus = repository.getReferenceById(ACTIVO);

        if (estatus.idNoExiste()) {

            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }

        productos.setEstatus(estatus);

        repo.save(productos);

        LOGGER.info("Datos Insertados ={}", productos);

        return new GenericResponse<>();
    }

    public <T> GenericResponse<T> updateProducts(Long id, ProductosDtoRequest request) {

        Productos productos = repo.getReferenceById(id);

        if (productos.idNoExiste()) {

            throw new ApiException(List.of(INFORMACION_NO_ENCONTRADA), EMensajeException.E404);
        }

        productos.setDescripcion(request.getDescripcion());
        productos.setStock(request.getStock());
        productos.setNombre(request.getNombre());
        productos.setPrecio(request.getPrecio());
        productos.setUrl(request.getUrl());

        repo.save(productos);

        return new GenericResponse<>();
    }

    public GenericResponse<List<Productos>> getAllProductos() {

        List<Productos> productos = repo.findAll();

        if (productos.isEmpty()) {

            throw new ApiException(List.of(INFORMACION_NO_ENCONTRADA), EMensajeException.E404);
        }

        return new GenericResponse<>(productos);
    }

    public GenericResponse<Productos> productoById(Long id) {

        Productos producto = repo.getReferenceById(id);

        if (producto.idNoExiste()) {

            throw new ApiException(List.of(INFORMACION_NO_ENCONTRADA), EMensajeException.E404);
        }

        return new GenericResponse<>(producto);
    }

    public <T> GenericResponse<T> deleteProductoById(Long id, Long idEstatus) {

        Productos productos = repo.getReferenceById(id);

        if (productos.idNoExiste()) {

            throw new ApiException(List.of(INFORMACION_NO_ENCONTRADA), EMensajeException.E404);
        }

        Estatus estatus = repository.getReferenceById(idEstatus);

        if (estatus.idNoExiste()) {

            throw new ApiException(List.of(PARAMETROS_NO_VALIDOS), EMensajeException.E400);
        }

        productos.setEstatus(estatus);

        repo.save(productos);

        return new GenericResponse<>();
    }
}