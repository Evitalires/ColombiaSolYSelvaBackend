package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.ProductoPagoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;

import java.math.BigDecimal;
import java.util.List;

public interface ImercadoPagoService {
    String crearPreferencia(List<ProductoPagoDTO> productos, pedidos pedido);
    String crearPago(pedidos pedido, BigDecimal total);
}
