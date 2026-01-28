package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CarritoDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CheckoutRequest;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IclienteRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IdetallepedidoRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IpedidosRepository;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class pedidosService implements IpedidosService {

    private final IpedidosRepository pedidosRepository;
    private final IdetallepedidoRepository detallepedidoRepository;
    private final detallepedidoService detallepedidoService;
    private final clienteService clienteService;

    @Autowired
    public pedidosService(
            IpedidosRepository pedidosRepository,
            IdetallepedidoRepository detallepedidoRepository,
            detallepedidoService detallepedidoService,
            clienteService clienteService
    ) {
        this.pedidosRepository = pedidosRepository;
        this.detallepedidoRepository = detallepedidoRepository;
        this.detallepedidoService = detallepedidoService;
        this.clienteService = clienteService;
    }

    // =========================
    // FLUJO PRINCIPAL
    // =========================
    @Transactional
    public pedidos crearPedidoDesdeCarrito(
            Authentication auth,
            List<CarritoDTO> carrito
    ) {

        cliente cliente = obtenerClienteDesdeAuth(auth);

        // 1️⃣ Crear pedido base
        pedidos pedido = new pedidos();
        pedido.setCliente(cliente);
        pedido.setFechaPedido(new Date());
        pedido.setEstadoPago("PENDIENTE");
        pedido.setNoGuiaPedido("SIN_GUIA");
        pedido.setTransportadoraPedido("POR_ASIGNAR");

        pedido = pedidosRepository.save(pedido);

        // 2️⃣ Crear detalles
        detallepedidoService.crearDetallesDesdeCarrito(
                pedido,
                cliente,
                carrito
        );

        // 3️⃣ Calcular total desde detalles
        calcularTotalPedido(pedido);

        return pedido;
    }

    // =========================
    // TOTAL DEL PEDIDO
    // =========================
    public BigDecimal calcularTotalPedido(pedidos pedido) {

        List<detallepedido> detalles =
                detallepedidoRepository.findByPedido(pedido);

        BigDecimal total = BigDecimal.ZERO;

        for (detallepedido d : detalles) {
            total = total.add(d.getSubTotalDTPedido());
        }

        pedido.setValorPedido(total);
        pedidosRepository.save(pedido);

        return total;
    }

    // =========================
    // AUTH
    // =========================
    public cliente obtenerClienteDesdeAuth(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado");
        }

        return clienteService.buscarPorCorreo(auth.getName());
    }

    // =========================
    // MERCADOPAGO
    // =========================
    @Override
    public void procesarPago(String paymentId) {

        try {
            PaymentClient paymentClient = new PaymentClient();
            Payment payment = paymentClient.get(Long.parseLong(paymentId));

            String status = payment.getStatus();
            String statusDetail = payment.getStatusDetail();
            String externalReference = payment.getExternalReference();

            if (externalReference == null) return;

            Long pedidoId = Long.parseLong(externalReference);
            pedidos pedido = pedidosRepository.findById(pedidoId).orElseThrow();

            if ("PAGADO".equals(pedido.getEstadoPago())) return;

            if ("approved".equals(status) && "accredited".equals(statusDetail)) {
                pedido.setEstadoPago("PAGADO");
                pedido.setMpPaymentId(paymentId);
            } else if ("rejected".equals(status)) {
                pedido.setEstadoPago("RECHAZADO");
            }

            pedidosRepository.save(pedido);

        } catch (MPException | MPApiException e) {
            e.printStackTrace(); // nunca romper webhook
        }
    }

    @Override
    public String obtenerEstadoPedido(Long idPedido) {
        pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        return pedido.getEstadoPago();
    }

    @Override
    public void actualizarPreference(Long pedidoId, String preferenceId) {
        pedidos pedido = pedidosRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedido.setMpPreferenceId(preferenceId);
        pedidosRepository.save(pedido);
    }

    @Override
    public void editarPedidos(Long id, pedidos pedidoActualizado) {
        pedidos pedidoExistente = pedidosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedidoExistente.setEstadoPago(pedidoActualizado.getEstadoPago());
        pedidoExistente.setMpPreferenceId(pedidoActualizado.getMpPreferenceId());

        pedidosRepository.save(pedidoExistente);
    }

    @Override
    public void deletePedidos(Long idPedido) {

        pedidos pedido = pedidosRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedidosRepository.delete(pedido);
    }
    @Override
    public Optional<pedidos> obtenerPorId(Long id) {
        return pedidosRepository.findById(id);
    }

    @Override
    public List<pedidos> obtenerTodos() {
        return pedidosRepository.findAll();
    }
}
