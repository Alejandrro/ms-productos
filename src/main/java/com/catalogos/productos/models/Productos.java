package com.catalogos.productos.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Productos {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "imagen_url")
    private String url;

    @Column(name = "fecha_creacion")
    private Timestamp fechaCreacion;

    @OneToOne
    @JoinColumn(name = "id_status")
    private Estatus estatus;

    public Boolean idNoExiste() {

        return id == null;
    }
}