package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=50)
    private String nombreProducto;

    @NotNull(message="Campo no puede estar vacío")
    private Float precioProducto;

    @NotBlank(message="Campo no puede estar vacío")
    private String descripcionProducto;

    @NotBlank(message="Campo no puede estar vacío")
    @Size(max=30)
    private String categoriaProducto;

    @NotNull(message="Campo no puede estar vacío")
    private Integer stockProducto;

    //ONE TO MANY: Un producto puede estar en varios detalles de pedido
    @OneToMany(mappedBy = "producto",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<detallepedido> detallepedido;


    public producto() {
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Float getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Float precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(String categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
    }

    public Integer getStockProducto() {
        return stockProducto;
    }

    public void setStockProducto(Integer stockProducto) {
        this.stockProducto = stockProducto;
    }

    public List<detallepedido> getDetallepedido() {
        return detallepedido;
    }

    public void setDetallepedido(List<detallepedido> detallepedido) {
        this.detallepedido = detallepedido;
    }

    public producto(Long idProducto, String nombreProducto, Float precioProducto, String descripcionProducto, String categoriaProducto, Integer stockProducto, List<detallepedido> detallepedido) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.descripcionProducto = descripcionProducto;
        this.categoriaProducto = categoriaProducto;
        this.stockProducto = stockProducto;
        this.detallepedido = detallepedido;


    }
}
