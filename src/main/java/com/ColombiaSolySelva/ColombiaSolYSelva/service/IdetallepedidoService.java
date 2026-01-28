package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CarritoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;

import java.util.List;
import java.util.Optional;

public interface IdetallepedidoService {
    List<detallepedido> obtenerTodos();
    Optional<detallepedido> obtenerPorId(Long id);
    void guardardetallepedido(Long pedidoId, detallepedido detallepedido);
    void deletedetallepedido(Long id);
    void editardetallepedido(Long id, detallepedido detallepedidoActualizado);
    void crearDetallesDesdeCarrito(pedidos pedido, cliente cliente, List<CarritoDTO> carrito);
}
