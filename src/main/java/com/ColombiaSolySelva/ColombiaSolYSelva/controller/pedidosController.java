package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.pedidos;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.pedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class pedidosController {
    private final pedidosService pedidosService;

    @Autowired
    public pedidosController(pedidosService pedidosService) {
        this.pedidosService = pedidosService;
    }

    @GetMapping
    public List<pedidos> listapedidos(){
        return pedidosService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<pedidos> obtenerPorId(@PathVariable Long id){
        return pedidosService.obtenerPorId(id);
    }

    @PostMapping("/cliente/{clienteId}/pedido")
    public ResponseEntity<String> crearpedido(
            @PathVariable Long clienteId,
            @RequestBody pedidos pedido){
        pedidosService.guardarPedidos(clienteId, pedido);
        return ResponseEntity.ok("Pedido agregado con éxito");
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
}

