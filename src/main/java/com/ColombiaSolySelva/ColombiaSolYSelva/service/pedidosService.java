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
    private final IclienteRepository iclienteRepository;

    @Autowired
    public pedidosService(IpedidosRepository pedidosRepository, IclienteRepository iclienteRepository) {
        this.pedidosRepository = pedidosRepository;
        this.iclienteRepository = iclienteRepository;
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
    public void guardarPedidos(Long clienteId, pedidos pedido) {
        cliente cliente = iclienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        pedido.setCliente(cliente);
        pedidosRepository.save(pedido);

    }

    @Override
    public void deletePedidos(Long id) {
        pedidosRepository.deleteById(id);
    }

    @Override
    public void editarPedidos(Long pedidoId, pedidos pedidoActualizado) {
        pedidos pedidoExistente = pedidosRepository.findById(pedidoId).orElse(null);

        if (pedidoExistente != null) {
            //Actualizar los campos del pedido existente
            pedidoExistente.setValorPedido(pedidoActualizado.getValorPedido());
            pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());
            pedidoExistente.setTransportadoraPedido(pedidoActualizado.getTransportadoraPedido());
            pedidoExistente.setNoGuiaPedido(pedidoActualizado.getNoGuiaPedido());

            // Guardo el pedido actualizado
            pedidosRepository.save(pedidoExistente);
        } else {
            throw new RuntimeException("Pedido no encontrado con el id: " + pedidoId);
        }
    }
}
