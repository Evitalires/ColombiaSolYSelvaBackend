package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;

import java.util.List;
import java.util.Optional;

public interface IpedidosService {
    List<pedidos> obtenerTodos();
    Optional<pedidos> obtenerPorId(Long id);
    void guardarPedidos(pedidos pedidos);
    void deletePedidos(Long id);
    void editarPedidos(Long id, pedidos pedidoActualizado);
}
