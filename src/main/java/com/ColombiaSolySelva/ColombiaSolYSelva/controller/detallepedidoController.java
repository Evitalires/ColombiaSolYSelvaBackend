package com.ColombiaSolySelva.ColombiaSolYSelva.controller;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.detallepedido;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.detallepedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detallepedido")
public class detallepedidoController {
    private final detallepedidoService detallepedidoService;

    @Autowired
    public detallepedidoController(detallepedidoService detallepedidoService) {
        this.detallepedidoService = detallepedidoService;
    }

    @GetMapping
    public List<detallepedido> listadetallepedido(){
        return detallepedidoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<detallepedido> obtenerPorId(@PathVariable Long id){
        return detallepedidoService.obtenerPorId(id);
    }

    @GetMapping("/crear")
    public ResponseEntity<String> guardardetallePedido(@RequestBody detallepedido detallepedido){
        detallepedidoService.guardardetallepedido(detallepedido);
        return ResponseEntity.ok("Detalle Pedido agregado con éxito");
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<String> deletedetallePedido(@PathVariable Long id){
        detallepedidoService.deletedetallepedido(id);
        return ResponseEntity.ok ("Detalle Pedido eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editardetallePedido(@PathVariable Long id, @RequestBody detallepedido detallepedidoActualizado){
        detallepedidoService.editardetallepedido(id, detallepedidoActualizado);
        return ResponseEntity.ok("Detalle Pedido agregado exitosamente");
    }
}
