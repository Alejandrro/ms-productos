package com.catalogos.productos.repositories;

import com.catalogos.productos.models.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductosRepository extends JpaRepository<Productos, Long> {
}