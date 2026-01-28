package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CarritoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IdetallepedidoRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IpedidosRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class detallepedidoService implements IdetallepedidoService{
    private final IdetallepedidoRepository detallepedidoRepository;
    private final IpedidosRepository ipedidosRepository;
    private final IproductoRepository productoRepository;

    @Autowired
    public detallepedidoService(IdetallepedidoRepository detallepedidoRepository, IpedidosRepository ipedidosRepository, IproductoRepository productoRepository) {
        this.detallepedidoRepository = detallepedidoRepository;
        this.ipedidosRepository = ipedidosRepository;
        this.productoRepository = productoRepository;
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
    public void guardardetallepedido(Long pedidoId, detallepedido detallepedido) {
        pedidos pedido = ipedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        detallepedido.setPedido(pedido);
        detallepedidoRepository.save(detallepedido);
    }

    @Override
    public void deletedetallepedido(Long id) {
        detallepedidoRepository.deleteById(id);
    }

    @Override
    public void editardetallepedido(Long pedidoId, detallepedido detallepedidoActualizado) {
        detallepedido detallepedidoExistente = detallepedidoRepository.findById(pedidoId).orElse(null);

        if (detallepedidoExistente != null) {
            //Actualizar los campos del detalle pedido existente
            detallepedidoExistente.setCantidadDTPedido(detallepedidoActualizado.getCantidadDTPedido());
            detallepedidoExistente.calcularSubTotal();
            detallepedidoExistente.setProducto(detallepedidoActualizado.getProducto());
            // Guardo el detalle pedido actualizado
            detallepedidoRepository.save(detallepedidoExistente);
        } else {
            throw new RuntimeException("Pedido no encontrado con el id: " + pedidoId);
        }
    }
    public void crearDetallesDesdeCarrito(
            pedidos pedido,
            cliente cliente,
            List<CarritoDTO> carrito
    ) {

        for (CarritoDTO item : carrito) {

            producto prod = productoRepository.findById(item.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            detallepedido detalle = new detallepedido();
            detalle.setPedido(pedido);
            detalle.setProducto(prod);
            detalle.setCantidadDTPedido(item.getCantidad());

            detalle.calcularSubTotal();

            detallepedidoRepository.save(detalle);
        }
    }
}

