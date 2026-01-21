package com.ColombiaSolySelva.ColombiaSolYSelva.controller;


import com.ColombiaSolySelva.ColombiaSolYSelva.JwtUtil;
import com.ColombiaSolySelva.ColombiaSolYSelva.dto.clienteDto;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.clienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class clienteController {
    @Autowired
    private clienteService clienteService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

        cliente.setContrasenaCliente(
                passwordEncoder.encode(cliente.getContrasenaCliente())
        );

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

    @PostMapping("/loginConDTO")
    public ResponseEntity<String> loginConDTO(@RequestBody clienteDto clienteDto) {
        String correo = clienteDto.getCorreoCliente();
        String contrasena = clienteDto.getContrasenaCliente();

        UserDetails userDetails = clienteService.loadUserByUsername(correo);
        if (userDetails != null && passwordEncoder.matches(contrasena, userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @GetMapping("/resource")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedResource() {
        return ResponseEntity.ok("Este es un recurso protegido!");
    }

    @GetMapping("/me")
    public ResponseEntity<cliente> obtenerUsuarioActual(Authentication authentication) {
        String correo = authentication.getName();
        cliente cliente = clienteService.buscarPorCorreo(correo);
        return ResponseEntity.ok(cliente);
    }
}

