package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;

import java.util.List;
import java.util.Optional;

public interface IclienteService {
    List<cliente> obtenerTodos();
    Optional<cliente> obtenerPorId(Long id);
    void guardarCliente(cliente cliente);
    void deleteCliente(Long id);
    void editarCliente(Long id, cliente clienteActualizado);
}
