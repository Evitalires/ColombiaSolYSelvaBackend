package com.ColombiaSolySelva.ColombiaSolYSelva.dto;

import java.math.BigDecimal;
import java.util.List;

public class CheckoutRequest {
    private String direccion;
    private String ciudad;
    private String metodoPago;
    private BigDecimal valorPedido;

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    private List<CarritoDTO> carrito;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public List<CarritoDTO> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<CarritoDTO> carrito) {
        this.carrito = carrito;
    }
}
