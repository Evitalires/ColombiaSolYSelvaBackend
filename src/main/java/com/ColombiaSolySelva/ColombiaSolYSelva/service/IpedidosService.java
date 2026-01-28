package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CarritoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CheckoutRequest;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IpedidosService {

    pedidos crearPedidoDesdeCarrito(
            Authentication auth,
            List<CarritoDTO> carrito
    );

    BigDecimal calcularTotalPedido(pedidos pedido);

    cliente obtenerClienteDesdeAuth(Authentication auth);

    void procesarPago(String paymentId);

    String obtenerEstadoPedido(Long idPedido);

    void actualizarPreference(Long pedidoId, String preferenceId);

    void editarPedidos(Long id, pedidos pedidoActualizado);

    void deletePedidos(Long idPedido);

    Optional<pedidos> obtenerPorId(Long id);

    List<pedidos> obtenerTodos();
    pedidos guardarPedido(pedidos pedido);
}
