package com.ColombiaSolySelva.ColombiaSolYSelva.dto;

import java.math.BigDecimal;
import java.util.List;

public class CrearOrdenDTO {
    private Long idCliente;
    private BigDecimal total;
    private List<ProductoPagoDTO> productos;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ProductoPagoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPagoDTO> productos) {
        this.productos = productos;
    }
}
