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

import java.util.List;
import java.util.Optional;

@Service
public class clienteService implements IclienteService, UserDetailsService {
    @Autowired
    private final IclienteRepository clienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public clienteService(IclienteRepository iclienteRepository) {
        this.clienteRepository = iclienteRepository;
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
            if (clienteActualizado.getContrasenaCliente() != null && !clienteActualizado.getContrasenaCliente().isEmpty()) {
                clienteExistente.setContrasenaCliente(passwordEncoder.encode(clienteActualizado.getContrasenaCliente()));
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
            throw new RuntimeException("El nombre de usuario ya existe");
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
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    public cliente buscarPorCorreo(String correo) {
        cliente cliente = clienteRepository.findBycorreoCliente(correo);
        if (cliente == null) {
            throw new UsernameNotFoundException("Cliente no encontrado");
        }
        return cliente;
    }
}



