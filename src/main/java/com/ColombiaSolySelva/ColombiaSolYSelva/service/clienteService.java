package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IclienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class clienteService implements IclienteService, UserDetailsService {

    private final IclienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;


    public clienteService(IclienteRepository iclienteRepository, PasswordEncoder passwordEncoder) {
        this.clienteRepository = iclienteRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public void guardarCliente(cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public void editarCliente(Long id, cliente clienteActualizado) {
        cliente clienteExistente = clienteRepository.findById(id).orElse(null);

        if (clienteExistente != null) {
            // Actualizar campos en el mismo orden que el formulario
            clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
            clienteExistente.setApellidoCliente(clienteActualizado.getApellidoCliente());
            clienteExistente.setDireccionCliente(clienteActualizado.getDireccionCliente());
            clienteExistente.setCiudadCliente(clienteActualizado.getCiudadCliente());
            clienteExistente.setTelCliente(clienteActualizado.getTelCliente());

            // Solo actualizar contraseña si se envía un nuevo valor
            if (clienteActualizado.getContrasenaCliente() != null
                    && !clienteActualizado.getContrasenaCliente().isEmpty()) {
                clienteExistente
                        .setContrasenaCliente(passwordEncoder.encode(clienteActualizado.getContrasenaCliente()));
            }

            clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente no encontrado con el id: " + id);
        }
    }

    public cliente registerCliente(cliente cliente) {
        // Validar campos obligatorios
        if (cliente.getNombreCliente() == null || cliente.getApellidoCliente() == null ||
                cliente.getDireccionCliente() == null || cliente.getCiudadCliente() == null ||
                cliente.getTelCliente() == null || cliente.getCorreoCliente() == null ||
                cliente.getContrasenaCliente() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Verificar si el usuario ya existe
        if (clienteRepository.findBycorreoCliente(cliente.getCorreoCliente()) != null) {
            throw new RuntimeException("Este correo electrónico ya está registrado");
        }

        cliente newUser = new cliente();
        newUser.setNombreCliente(cliente.getNombreCliente());
        newUser.setApellidoCliente(cliente.getApellidoCliente());
        newUser.setDireccionCliente(cliente.getDireccionCliente());
        newUser.setCiudadCliente(cliente.getCiudadCliente());
        newUser.setTelCliente(cliente.getTelCliente());
        newUser.setCorreoCliente(cliente.getCorreoCliente());
        newUser.setContrasenaCliente(passwordEncoder.encode(cliente.getContrasenaCliente()));

        return clienteRepository.save(newUser);
    }

    // Metodo de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByUsername(String correoCliente) throws UsernameNotFoundException {
        if (correoCliente == null || correoCliente.trim().isEmpty()) {
            throw new UsernameNotFoundException("El correo no puede estar vacío.");
        }
        cliente cliente = clienteRepository.findBycorreoCliente(correoCliente);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(
                cliente.getCorreoCliente(),
                cliente.getContrasenaCliente(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public cliente buscarPorCorreo(String correo) {
        cliente cliente = clienteRepository.findBycorreoCliente(correo);
        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente no encontrado");
        }
        return cliente;
    }




    //para edicion de archivos foto perfil -> proyecto backend y bd

    public String guardarFotoPerfil(Long id, MultipartFile foto) throws IOException {
        String carpeta = "uploads/imgPerfiles/";
        String nombreArchivo = "perfil_" + id + "_" + foto.getOriginalFilename();
        Path ruta = Paths.get(carpeta + nombreArchivo);
        Files.write(ruta, foto.getBytes());
        return nombreArchivo;
    }

    public void actualizarImagenCliente(Long id, String nombreArchivo) {
        cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setImagenCliente(nombreArchivo);
        clienteRepository.save(cliente);
    }

    public String obtenerNombreFoto(Long id) {
        cliente cliente = clienteRepository.findById(id).orElseThrow();
        return cliente.getImagenCliente(); // devuelve el nombre del archivo guardado en BD
    }



    public void eliminarFotoPerfil(Long id) throws IOException {
        cliente cliente = clienteRepository.findById(id).orElseThrow();
        if(cliente.getImagenCliente() != null){
            Path ruta = Paths.get("uploads/imgPerfiles/" + cliente.getImagenCliente());
            Files.deleteIfExists(ruta);
            cliente.setImagenCliente(null);
            clienteRepository.save(cliente);
        }
    }



    // Nuevo metodo que busca al cliente por correo (lo trae del JWT)
    public void cambiarContrasenaPorCorreo(String correo, String actual, String nueva, String confirmar) {
        // Buscar cliente en la BD por correo
        cliente c = clienteRepository.findBycorreoCliente(correo);
        if (c == null) {
            throw new RuntimeException("Cliente no encontrado");
        }

        // Validar contraseña actual contra el hash en BD
        if (!passwordEncoder.matches(actual, c.getContrasenaCliente())) {
            throw new RuntimeException("La contraseña actual es incorrecta");
        }

        // Validar que nueva y confirmar coincidan
        if (!nueva.equals(confirmar)) {
            throw new RuntimeException("La nueva contraseña no coincide con la confirmación");
        }

        // Guardar nueva contraseña cifrada en el mismo campo contrasenaCliente
        c.setContrasenaCliente(passwordEncoder.encode(nueva));
        clienteRepository.save(c);
    }

}
