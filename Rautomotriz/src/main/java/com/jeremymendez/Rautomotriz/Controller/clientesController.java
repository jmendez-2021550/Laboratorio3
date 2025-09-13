package com.jeremymendez.Rautomotriz.Controller;

import com.jeremymendez.Rautomotriz.model.clientes;
import com.jeremymendez.Rautomotriz.service.clientesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class clientesController {

    private final clientesService clientesService;

    public clientesController(clientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    public List<clientes> getAllClientes() {
        return clientesService.getAllClientes();
    }

    @GetMapping("/{id}")
    public clientes getClienteById(@PathVariable Integer id) {
        return clientesService.getClienteById(id);
    }

    @PostMapping
    public ResponseEntity<?> createCliente(@RequestBody clientes cliente) {
        // Validación de campos vacíos
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo apellido está vacío");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo teléfono está vacío");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email está vacío");
        }

        // Validación de formato de correo
        String email = cliente.getEmail().toLowerCase();
        if (!(email.endsWith("@gmail.com") || email.endsWith("@kinal.edu.gt") || email.endsWith("@yahoo.com"))) {
            return ResponseEntity.badRequest().body("Correo inválido, los correos permitidos son gmail, kinal.edu.gt y yahoo.com");
        }

        // Validación de duplicados (individualmente)
        List<clientes> clientesList = clientesService.getAllClientes();
        for (clientes c : clientesList) {
            if (c.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                return ResponseEntity.badRequest().body("El nombre '" + cliente.getNombre() + "' ya está en uso");
            }
            if (c.getApellido().equalsIgnoreCase(cliente.getApellido())) {
                return ResponseEntity.badRequest().body("El apellido '" + cliente.getApellido() + "' ya está en uso");
            }
            if (c.getTelefono().equals(cliente.getTelefono())) {
                return ResponseEntity.badRequest().body("El teléfono '" + cliente.getTelefono() + "' ya está en uso");
            }
            if (c.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                return ResponseEntity.badRequest().body("El email '" + cliente.getEmail() + "' ya está en uso");
            }
        }

        clientes savedCliente = clientesService.saveCliente(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCliente(@PathVariable Integer id, @RequestBody clientes cliente) {
        // Validación de campos vacíos
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (cliente.getApellido() == null || cliente.getApellido().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo apellido está vacío");
        }
        if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo teléfono está vacío");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email está vacío");
        }

        // Validación de formato de correo
        String email = cliente.getEmail().toLowerCase();
        if (!(email.endsWith("@gmail.com") || email.endsWith("@kinal.edu.gt") || email.endsWith("@yahoo.com"))) {
            return ResponseEntity.badRequest().body("Correo inválido, los correos permitidos son gmail, kinal.edu.gt y yahoo.com");
        }

        // Validación de duplicados (excluyendo el cliente actual)
        List<clientes> clientesList = clientesService.getAllClientes();
        for (clientes c : clientesList) {
            if (!c.getIdCliente().equals(id)) { // Excluir el cliente actual
                if (c.getNombre().equalsIgnoreCase(cliente.getNombre())) {
                    return ResponseEntity.badRequest().body("El nombre '" + cliente.getNombre() + "' ya está en uso");
                }
                if (c.getApellido().equalsIgnoreCase(cliente.getApellido())) {
                    return ResponseEntity.badRequest().body("El apellido '" + cliente.getApellido() + "' ya está en uso");
                }
                if (c.getTelefono().equals(cliente.getTelefono())) {
                    return ResponseEntity.badRequest().body("El teléfono '" + cliente.getTelefono() + "' ya está en uso");
                }
                if (c.getEmail().equalsIgnoreCase(cliente.getEmail())) {
                    return ResponseEntity.badRequest().body("El email '" + cliente.getEmail() + "' ya está en uso");
                }
            }
        }

        clientes updatedCliente = clientesService.updateCliente(id, cliente);
        return ResponseEntity.ok(updatedCliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Integer id) {
        clientesService.deleteCliente(id);
    }
}
