package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productoService implements IproductoService{
    private final IproductoRepository productoRepository;

    @Autowired
    public productoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public void guardarProducto(producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public void editarProducto(Long id, producto productoActualizado) {
        producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente != null) {
            //Actualizar los campos del producto existente
            productoExistente.setNombre_Producto(productoActualizado.getNombre_Producto());
            productoExistente.setPrecio_Producto(productoActualizado.getPrecio_Producto());
            productoExistente.setDescripcion_Producto(productoActualizado.getDescripcion_Producto());
            productoExistente.setCategoria_Producto(productoActualizado.getCategoria_Producto());
            productoExistente.setStock_Producto(productoActualizado.getStock_Producto());

            // Guardo el producto actualziado
            productoRepository.save(productoExistente);
        } else {
            throw new RuntimeException("Producto no encontrado con el id: " + id);
        }
    }
}
