package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IclienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            //Actualizar los campos del cliente existente
            clienteExistente.setNombreCliente(clienteActualizado.getNombreCliente());
            clienteExistente.setApellidoCliente(clienteActualizado.getApellidoCliente());
            clienteExistente.setContrasenaCliente(clienteActualizado.getContrasenaCliente());
            clienteExistente.setTelCliente(clienteActualizado.getTelCliente());
            clienteExistente.setCorreoCliente(clienteActualizado.getCorreoCliente());
            clienteExistente.setDireccionCliente(clienteActualizado.getDireccionCliente());
            clienteExistente.setCiudadCliente(clienteActualizado.getCiudadCliente());

            // Guardo el cliente actualziado
            clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente no encontrado con el id: " + id);
        }
    }

    // Validar campos obligatorios
        public cliente registerCliente(cliente cliente) {
        // Validar campos obligatorios
        if (cliente.getCorreoCliente() == null || cliente.getContrasenaCliente() == null ||
                cliente.getNombreCliente() == null || cliente.getApellidoCliente() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Verificar si el usuario ya existe
        if (clienteRepository.findBycorreoCliente(cliente.getCorreoCliente()) != null) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        cliente newUser = new cliente();
        newUser.setCorreoCliente(cliente.getCorreoCliente());
        newUser.setContrasenaCliente(passwordEncoder.encode(cliente.getContrasenaCliente()));
        newUser.setNombreCliente(cliente.getNombreCliente());
        newUser.setApellidoCliente(cliente.getApellidoCliente());

        return clienteRepository.save(newUser);
    }

    // Métdo de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByUsername(String correoCliente) throws UsernameNotFoundException {
        cliente cliente = clienteRepository.findBycorreoCliente(correoCliente);
        if (cliente == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(cliente.getCorreoCliente(), cliente.getContrasenaCliente(), new ArrayList<>());
    }
}



