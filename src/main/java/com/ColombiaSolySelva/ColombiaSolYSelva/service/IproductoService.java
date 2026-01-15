package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;

import java.util.List;
import java.util.Optional;

public interface IproductoService {
    List<producto> obtenerTodos();
    Optional<producto> obtenerPorId(Long id);
    void guardarProducto(producto producto);
    void deleteProducto(Long id);
    void editarProducto(Long id, producto productoActualizado);
}
