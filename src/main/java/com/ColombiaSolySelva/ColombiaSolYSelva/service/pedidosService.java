package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IclienteRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IpedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class pedidosService implements IpedidosService{
    private final IpedidosRepository pedidosRepository;

    @Autowired
    public pedidosService(IpedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    @Override
    public List<pedidos> obtenerTodos() {
        return pedidosRepository.findAll();
    }

    @Override
    public Optional<pedidos> obtenerPorId(Long id) {
        return pedidosRepository.findById(id);
    }

    @Override
    public void guardarPedidos(pedidos pedidos) {
        pedidosRepository.save(pedidos);
    }

    @Override
    public void deletePedidos(Long id) {
        pedidosRepository.deleteById(id);
    }

    @Override
    public void editarPedidos(Long id, pedidos pedidoActualizado) {
        pedidos pedidoExistente = pedidosRepository.findById(id).orElse(null);

        if (pedidoExistente != null) {
            //Actualizar los campos del pedido existente
            pedidoExistente.setValor_Pedido(pedidoActualizado.getValor_Pedido());
            pedidoExistente.setFecha_Pedido(pedidoActualizado.getFecha_Pedido());
            pedidoExistente.setTransportadora_Pedido(pedidoActualizado.getTransportadora_Pedido());
            pedidoExistente.setNo_Guia_Pedido(pedidoActualizado.getNo_Guia_Pedido());

            // Guardo el pedido actualizado
            pedidosRepository.save(pedidoExistente);
        } else {
            throw new RuntimeException("Pedido no encontrado con el id: " + id);
        }
    }
}
