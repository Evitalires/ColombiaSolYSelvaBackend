package com.ColombiaSolySelva.ColombiaSolYSelva.service;

import com.ColombiaSolySelva.ColombiaSolYSelva.model.cliente;
import com.ColombiaSolySelva.ColombiaSolYSelva.repository.IclienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class clienteService implements IclienteService{
    private final IclienteRepository clienteRepository;

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
            clienteExistente.setNombre_cliente(clienteActualizado.getNombre_cliente());
            clienteExistente.setApellido_Cliente(clienteActualizado.getApellido_Cliente());
            clienteExistente.setContrasena_Cliente(clienteActualizado.getContrasena_Cliente());
            clienteExistente.setTel_Cliente(clienteActualizado.getTel_Cliente());
            clienteExistente.setCorreo_Cliente(clienteActualizado.getCorreo_Cliente());
            clienteExistente.setDireccion_Cliente(clienteActualizado.getDireccion_Cliente());
            clienteExistente.setCiudad_Cliente(clienteActualizado.getCiudad_Cliente());

            // Guardo el cliente actualziado
            clienteRepository.save(clienteExistente);
        } else {
            throw new RuntimeException("Cliente no encontrado con el id: " + id);
        }
    }

}

