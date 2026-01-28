package com.ColombiaSolySelva.ColombiaSolYSelva.dto;

import java.util.List;

public class PagoRequestDTO {

    private Long pedidoId;
    private List<ProductoPagoDTO> productos;

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public List<ProductoPagoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoPagoDTO> productos) {
        this.productos = productos;
    }
}
