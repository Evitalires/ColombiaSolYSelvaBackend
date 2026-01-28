package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.dto.PagoRequestDTO;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IpedidosRepository;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.ImercadoPagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoPagoController {

    private final ImercadoPagoService mercadoPagoService;
    private final IpedidosRepository pedidosRepository;

    public ProductoPagoController(ImercadoPagoService mercadoPagoService, IpedidosRepository pedidosRepository) {
        this.mercadoPagoService = mercadoPagoService;
        this.pedidosRepository = pedidosRepository;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearPreferencia(@RequestBody PagoRequestDTO request) {
        System.out.println("🔥 ENTRO AL CONTROLLER /api/pagos/crear");
        if (request.getProductos() == null || request.getProductos().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "El carrito está vacío"));
        }

        if (request.getPedidoId() == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "pedidoId es obligatorio"));
        }

        return pedidosRepository.findById(request.getPedidoId())
                .map(pedido -> {
                    String initPoint = mercadoPagoService.crearPreferencia(
                            request.getProductos(),
                            pedido
                    );
                    return ResponseEntity.ok(
                            Map.of("initPoint", initPoint)
                    );
                })
                .orElseGet(() ->
                        ResponseEntity.status(404)
                                .body(Map.of("error", "Pedido no encontrado"))
                );
    }
}
