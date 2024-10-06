package com.catalogos.productos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estatus {

    @Transient
    public static final Long ACTIVO = 1L;

    @Transient
    public static final Long INACTIVO = 2L;

    @Transient
    public static final Long BLOQUEADO = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    public Boolean idNoExiste() {

        return id == null;
    }
}