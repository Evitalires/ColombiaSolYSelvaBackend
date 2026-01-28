package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.service.IpedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin("*")
public class MercadoPagoWebhookController {

    @Autowired
    private IpedidosService pedidosService;

    @PostMapping("/webhook")
    public ResponseEntity<String> recibirWebhook(
            @RequestParam Map<String, String> params
    ) {

        String type = params.get("type");
        String paymentId = params.get("data.id");

        if (!"payment".equals(type) || paymentId == null) {
            return ResponseEntity.ok("Evento ignorado");
        }

        pedidosService.procesarPago(paymentId);

        return ResponseEntity.ok("OK");

    }
}