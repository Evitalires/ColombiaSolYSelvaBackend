package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Producto;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String nombre_Producto;

    @NotNull(message="Campo no puede estar vacío")
    float precio_Producto;

    @NotBlank(message="Campo no puede estar vacío")
    private String descripcion_Producto;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=30)
    private String categoria_Producto;

    @NotNull(message="Campo no puede estar vacío")
    private Integer stock_Producto;

    public producto() {
    }

    public producto(Long id_Producto, String nombre_Producto, float precio_Producto, String descripcion_Producto, String categoria_Producto, Integer stock_Producto) {
        this.id_Producto = id_Producto;
        this.nombre_Producto = nombre_Producto;
        this.precio_Producto = precio_Producto;
        this.descripcion_Producto = descripcion_Producto;
        this.categoria_Producto = categoria_Producto;
        this.stock_Producto = stock_Producto;
    }

    public Long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombre_Producto() {
        return nombre_Producto;
    }

    public void setNombre_Producto(String nombre_Producto) {
        this.nombre_Producto = nombre_Producto;
    }

    public float getPrecio_Producto() {
        return precio_Producto;
    }

    public void setPrecio_Producto(float precio_Producto) {
        this.precio_Producto = precio_Producto;
    }

    public String getDescripcion_Producto() {
        return descripcion_Producto;
    }

    public void setDescripcion_Producto(String descripcion_Producto) {
        this.descripcion_Producto = descripcion_Producto;
    }

    public String getCategoria_Producto() {
        return categoria_Producto;
    }

    public void setCategoria_Producto(String categoria_Producto) {
        this.categoria_Producto = categoria_Producto;
    }

    public Integer getStock_Producto() {
        return stock_Producto;
    }

    public void setStock_Producto(Integer stock_Producto) {
        this.stock_Producto = stock_Producto;
    }
}
