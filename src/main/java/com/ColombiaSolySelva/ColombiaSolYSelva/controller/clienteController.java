package com.ColombiaSolySelva.ColombiaSolYSelva.controller;


import com.ColombiaSolySelva.ColombiaSolYSelva.dto.CambiarContrasenaDTO;
import org.springframework.core.io.FileSystemResource;

import com.ColombiaSolySelva.ColombiaSolYSelva.JwtUtil;
import com.ColombiaSolySelva.ColombiaSolYSelva.dto.clienteDto;
import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.service.clienteService;
import org.springframework.core.io.Resource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;

@RestController
@RequestMapping("/cliente")
public class clienteController {

    // Declaración del logger
    private static final Logger logger = LoggerFactory.getLogger(clienteController.class);




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
    public ResponseEntity<?> crearCliente(@RequestBody cliente cliente){
        try {
            cliente registrado = clienteService.registerCliente(cliente);
            return ResponseEntity.ok(registrado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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





    // Subir foto
    @PostMapping("/foto/{id}")
    public ResponseEntity<?> subirFoto(
            @PathVariable Long id,
            @RequestParam("foto") MultipartFile foto) {

        try {
            // Guardar archivo en uploads/imgPerfiles
            String nombreArchivo = clienteService.guardarFotoPerfil(id, foto);

            // Actualizar BD con el nombre
            clienteService.actualizarImagenCliente(id, nombreArchivo);

            // Retornar URL del endpoint GET
            return ResponseEntity.ok(Map.of("urlFoto", "/cliente/foto/" + id));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la foto");
        }
    }

    // Obtener foto
    @GetMapping("/foto/{id}")
    public ResponseEntity<Resource> obtenerFoto(@PathVariable Long id) {
        try {
            // Recuperar nombre del archivo desde la BD
            String nombreArchivo = clienteService.obtenerNombreFoto(id);
            if (nombreArchivo == null) {
                return ResponseEntity.notFound().build();
            }

            // Construir la ruta de imgPerfiles
            Path path = Paths.get("src/main/resources/imgPerfiles/" + nombreArchivo);
            Resource resource = new FileSystemResource(path);

            // Detectar tipo de archivo según extensión
            String extension = com.google.common.io.Files.getFileExtension(nombreArchivo);
            MediaType mediaType = extension.equalsIgnoreCase("png") ? MediaType.IMAGE_PNG : MediaType.IMAGE_JPEG;

            return ResponseEntity.ok()         // Devuelve un HTTP 200 OK (respuesta exitosa)
                    .contentType(mediaType)    // Indica el tipo de contenido (ej. image/png o image/jpeg)
                    .body(resource);           // Envía el archivo (la imagen) como cuerpo de la respuesta


        } catch (Exception e) {
            logger.error("Error al obtener la foto de cliente con id {}: {}", id, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    // Eliminar foto
    @DeleteMapping("/foto/{id}")
    public ResponseEntity<?> eliminarFoto(@PathVariable Long id) {
        try {
            clienteService.eliminarFotoPerfil(id);
            return ResponseEntity.ok("Foto eliminada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la foto");
        }
    }



    // Endpoint para cambiar contraseña usando el usuario autenticado (no depende de id en la URL)
    @PutMapping("/cambiar-contrasena")
    public ResponseEntity<?> cambiarContrasena(
            Authentication authentication,           // Spring Security inyecta el usuario autenticado desde el JWT
            @RequestBody CambiarContrasenaDTO dto) { // Recibe el JSON con actual, nueva y confirmar
        try {
            // El correo del usuario autenticado viene del token JWT
            String correo = authentication.getName();

            // Llamamos al service pasando el correo y las contraseñas
            clienteService.cambiarContrasenaPorCorreo(
                    correo,
                    dto.getActual(),
                    dto.getNueva(),
                    dto.getConfirmar()
            );

            // Si todo sale okay, devuelve 200 OK
            return ResponseEntity.ok("Contraseña actualizada correctamente");
        } catch (RuntimeException e) {
            // Si hay error (contraseña actual incorrecta, confirmación distinta, etc.)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

