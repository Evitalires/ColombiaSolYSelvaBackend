package com.ColombiaSolySelva.ColombiaSolYSelva.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @NotBlank(message = "Campo no puede estar vacío")
    @Size(max = 50)
    private String nombreProducto;

    @NotNull(message = "Campo no puede estar vacío")
    @Positive
    private BigDecimal precioProducto;

    @NotBlank(message = "Campo no puede estar vacío")
    private String descripcionProducto;

    @NotBlank(message = "Campo no puede estar vacío")
    @Size(max = 30)
    private String categoriaProducto;

    @NotNull(message = "Campo no puede estar vacío")
    @Positive
    private Integer stockProducto;

    @NotBlank(message = "Campo no puede estar vacío")
    private String imagenProducto;

    // ONE TO MANY: Un producto puede estar en varios detalles de pedido
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
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

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public List<detallepedido> getDetallepedido() {
        return detallepedido;
    }

    public void setDetallepedido(List<detallepedido> detallepedido) {
        this.detallepedido = detallepedido;
    }

    public producto(Long idProducto, String nombreProducto, BigDecimal precioProducto, String descripcionProducto,
            String categoriaProducto, Integer stockProducto, String imagenProducto, List<detallepedido> detallepedido) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.descripcionProducto = descripcionProducto;
        this.categoriaProducto = categoriaProducto;
        this.stockProducto = stockProducto;
        this.imagenProducto = imagenProducto;
        this.detallepedido = detallepedido;
    }
}
