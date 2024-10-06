package com.catalogos.productos.repositories;

import com.catalogos.productos.models.Estatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstatusRepository extends JpaRepository<Estatus, Long> {
}
