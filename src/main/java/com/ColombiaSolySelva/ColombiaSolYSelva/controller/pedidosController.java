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

    @GetMapping("/crear")
    public ResponseEntity<String> pedidos(@RequestBody pedidos pedidos){
        pedidosService.guardarPedidos(pedidos);
        return ResponseEntity.ok("Detalle Pedido agregado con éxito");
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<String> pedidosPedido(@PathVariable Long id){
        pedidosService.deletePedidos(id);
        return ResponseEntity.ok ("Detalle Pedido eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarpedidos(@PathVariable Long id, @RequestBody pedidos pedidosActualizado){
        pedidosService.editarPedidos(id, pedidosActualizado);
        return ResponseEntity.ok("Detalle Pedido agregado exitosamente");
    }
}

