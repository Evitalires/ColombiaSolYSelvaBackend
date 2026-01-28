package com.ColombiaSolySelva.ColombiaSolYSelva.dto;

public class CarritoDTO {
    private Long productoId;
    private Integer cantidad;

    public CarritoDTO(Long productoId) {
    }

    public CarritoDTO(Long productoId, Integer cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
