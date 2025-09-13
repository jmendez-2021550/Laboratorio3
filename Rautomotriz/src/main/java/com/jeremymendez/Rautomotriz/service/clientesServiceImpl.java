package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.clientes;
import com.jeremymendez.Rautomotriz.repository.clientesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class clientesServiceImpl implements clientesService {

    private final clientesRepository clientesRepository;

    public clientesServiceImpl(clientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @Override
    public List<clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    @Override
    public clientes getClienteById(Integer id) {
        return clientesRepository.findById(id).orElse(null);
    }

    @Override
    public clientes saveCliente(clientes cliente) {
        return clientesRepository.save(cliente);
    }

    @Override
    public clientes updateCliente(Integer id, clientes cliente) {
        clientes existingCliente = clientesRepository.findById(id).orElse(null);
        if (existingCliente != null) {
            existingCliente.setNombre(cliente.getNombre());
            existingCliente.setApellido(cliente.getApellido());
            existingCliente.setTelefono(cliente.getTelefono());
            existingCliente.setEmail(cliente.getEmail());
            return clientesRepository.save(existingCliente);
        }
        return null;
    }

    @Override
    public void deleteCliente(Integer id) {
        clientesRepository.deleteById(id);
    }
}
