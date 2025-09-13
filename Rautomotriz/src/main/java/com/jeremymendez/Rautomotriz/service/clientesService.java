package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.clientes;
import java.util.List;

public interface clientesService {
    List<clientes> getAllClientes();
    clientes getClienteById(Integer id);
    clientes saveCliente(clientes cliente);
    clientes updateCliente(Integer id, clientes cliente);
    void deleteCliente(Integer id);
}
