package com.ColombiaSolySelva.ColombiaSolYSelva.controller;


import com.ColombiaSolySelva.ColombiaSolYSelva.model.producto;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.productoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class productoController {
    private final productoService productoService;

    @Autowired
    public productoController(productoService productosService) {
        this.productoService = productosService;
    }

    @GetMapping
    public List<producto> listaproductos(){
        return productoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Optional<producto> obtenerPorId(@PathVariable Long id){
        return productoService.obtenerPorId(id);
    }

    @GetMapping("/crear")
    public ResponseEntity<String> productos(@RequestBody producto productos){
        productoService.guardarProducto(productos);
        return ResponseEntity.ok("Detalle Pedido agregado con éxito");
    }

    @GetMapping("/borrar/{id}")
    public ResponseEntity<String> pedidosProductos(@PathVariable Long id){
        productoService.deleteProducto(id);
        return ResponseEntity.ok ("Detalle Pedido eliminado con éxito");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProductos(@PathVariable Long id, @RequestBody producto productoActualizado){
        productoService.editarProducto(id, productoActualizado);
        return ResponseEntity.ok("Producto agregado exitosamente");
    }
}


