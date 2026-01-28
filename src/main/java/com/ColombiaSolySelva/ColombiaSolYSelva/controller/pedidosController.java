package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.clienteService;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.pedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class pedidosController {
    private final pedidosService pedidosService;
    private final clienteService clienteService;

    @Autowired
    public pedidosController(pedidosService pedidosService, clienteService clienteService) {
        this.pedidosService = pedidosService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<pedidos> listapedidos(){
        return pedidosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<pedidos> obtenerPorId(@PathVariable Long id){
        return pedidosService.obtenerPorId(id);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> pedidosPedido(@PathVariable Long id){
        pedidosService.deletePedidos(id);
        return ResponseEntity.ok ("Pedido eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarpedidos(@PathVariable Long id, @RequestBody pedidos pedidosActualizado){
        pedidosService.editarPedidos(id, pedidosActualizado);
        return ResponseEntity.ok("Pedido actualizado exitosamente");
    }

    @GetMapping("/estado/{idPedido}")
    public ResponseEntity<String> estadoPedido(@PathVariable Long idPedido) {
        String estado = pedidosService.obtenerEstadoPedido(idPedido);
        return ResponseEntity.ok(estado);
    }
}

