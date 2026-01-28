package com.ColombiaSolySelva.ColombiaSolYSelva.dto;

import java.math.BigDecimal;

public class ProductoPagoDTO {
    private String titulo;
    private Integer cantidad;
    private BigDecimal precio;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
}
