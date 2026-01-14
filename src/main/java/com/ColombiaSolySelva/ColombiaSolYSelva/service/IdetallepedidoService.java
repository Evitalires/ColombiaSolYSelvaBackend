package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;

import java.util.List;
import java.util.Optional;

public interface IdetallepedidoService {
    List<detallepedido> obtenerTodos();
    Optional<detallepedido> obtenerPorId(Long id);
    void guardardetallepedido(detallepedido detallepedido);
    void deletedetallepedido(Long id);
    void editardetallepedido(Long id, detallepedido detallepedidoActualizado);
}
