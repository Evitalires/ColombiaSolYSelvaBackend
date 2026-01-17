package com.ColombiaSolySelva.ColombiaSolYSelva.controller;


import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class clienteController {
    private final clienteService clienteService;

    @Autowired
    public clienteController(clienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<cliente> listacliente(){
        return clienteService.obtenerTodos();
    }

    @GetMapping("{id}")
    public Optional<cliente> obtenerPorId(@PathVariable Long id){
        return clienteService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public ResponseEntity<String> guardarCliente(@RequestBody cliente cliente){
        clienteService.guardarCliente(cliente);
        return ResponseEntity.ok("Cliente agregado satisfactoriamente");
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id){
        clienteService.deleteCliente(id);
        return ResponseEntity.ok("Cliente eliminado exitosamente");
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarCliente(@PathVariable Long id, @RequestBody cliente clienteActualizado){
        clienteService.editarCliente(id, clienteActualizado);
        return ResponseEntity.ok("Cliente actualizado exitosamente");
    }
}
