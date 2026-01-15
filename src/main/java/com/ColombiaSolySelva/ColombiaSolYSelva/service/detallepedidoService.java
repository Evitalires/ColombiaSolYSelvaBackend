package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IdetallepedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class detallepedidoService implements IdetallepedidoService{
    private final IdetallepedidoRepository detallepedidoRepository;

    @Autowired
    public detallepedidoService(IdetallepedidoRepository detallepedidoRepository) {
        this.detallepedidoRepository = detallepedidoRepository;
    }

    @Override
    public List<detallepedido> obtenerTodos() {
        return detallepedidoRepository.findAll();
    }

    @Override
    public Optional<detallepedido> obtenerPorId(Long id) {
        return detallepedidoRepository.findById(id);
    }

    @Override
    public void guardardetallepedido(detallepedido detallepedido) {
        detallepedidoRepository.save(detallepedido);

    }

    @Override
    public void deletedetallepedido(Long id) {
        detallepedidoRepository.deleteById(id);

    }

    @Override
    public void editardetallepedido(Long id, detallepedido detallepedidoActualizado) {
        detallepedido detallepedidoExistente = detallepedidoRepository.findById(id).orElse(null);

        if (detallepedidoExistente != null) {
            //Actualizar los campos del detalel pedido existente
            detallepedidoExistente.setCantidad_DTPedido(detallepedidoActualizado.getCantidad_DTPedido());
            detallepedidoExistente.setSubTotal_DTPedido(detallepedidoActualizado.getSubTotal_DTPedido());
            // Guardo el detalle pedido actualziado
            detallepedidoRepository.save(detallepedidoExistente);
        } else {
            throw new RuntimeException("Detalle Pedido no encontrado con el id: " + id);
        }
    }
}

