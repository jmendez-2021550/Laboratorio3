package com.jeremymendez.Rautomotriz.Controller;

import com.jeremymendez.Rautomotriz.model.proveedores;
import com.jeremymendez.Rautomotriz.service.proveedoresService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class proveedoresController {

    private final proveedoresService proveedoresService;

    public proveedoresController(proveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping
    public List<proveedores> getAllProveedores() {
        return proveedoresService.getAllProveedores();
    }

    @GetMapping("/{id}")
    public proveedores getProveedorById(@PathVariable Integer id) {
        return proveedoresService.getProveedorById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProveedor(@RequestBody proveedores proveedor) {
        // Validación de campos vacíos
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (proveedor.getTelefono() == null || proveedor.getTelefono().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo teléfono está vacío");
        }
        if (proveedor.getEmail() == null || proveedor.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email está vacío");
        }
        if (proveedor.getDireccion() == null || proveedor.getDireccion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo dirección está vacío");
        }

        // Validación de formato de correo
        String email = proveedor.getEmail().toLowerCase();
        if (!(email.endsWith("@gmail.com") || email.endsWith("@kinal.edu.gt") || email.endsWith("@yahoo.com"))) {
            return ResponseEntity.badRequest().body("Correo inválido, los correos permitidos son gmail, kinal.edu.gt y yahoo.com");
        }

        // Validación de duplicados
        List<proveedores> proveedoresList = proveedoresService.getAllProveedores();
        for (proveedores p : proveedoresList) {
            if (p.getNombre().equalsIgnoreCase(proveedor.getNombre())) {
                return ResponseEntity.badRequest().body("El nombre del proveedor '" + proveedor.getNombre() + "' ya está en uso");
            }
            if (p.getEmail().equalsIgnoreCase(proveedor.getEmail())) {
                return ResponseEntity.badRequest().body("El email '" + proveedor.getEmail() + "' ya está en uso");
            }
        }

        proveedores savedProveedor = proveedoresService.saveProveedor(proveedor);
        return ResponseEntity.ok(savedProveedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable Integer id, @RequestBody proveedores proveedor) {
        // Validación de campos vacíos
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (proveedor.getTelefono() == null || proveedor.getTelefono().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo teléfono está vacío");
        }
        if (proveedor.getEmail() == null || proveedor.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo email está vacío");
        }
        if (proveedor.getDireccion() == null || proveedor.getDireccion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo dirección está vacío");
        }

        // Validación de formato de correo
        String email = proveedor.getEmail().toLowerCase();
        if (!(email.endsWith("@gmail.com") || email.endsWith("@kinal.edu.gt") || email.endsWith("@yahoo.com"))) {
            return ResponseEntity.badRequest().body("Correo inválido, los correos permitidos son gmail, kinal.edu.gt y yahoo.com");
        }

        // Validación de duplicados (excluyendo el proveedor actual)
        List<proveedores> proveedoresList = proveedoresService.getAllProveedores();
        for (proveedores p : proveedoresList) {
            if (!p.getIdProveedor().equals(id)) { // Excluir el proveedor actual
                if (p.getNombre().equalsIgnoreCase(proveedor.getNombre())) {
                    return ResponseEntity.badRequest().body("El nombre del proveedor '" + proveedor.getNombre() + "' ya está en uso");
                }
                if (p.getEmail().equalsIgnoreCase(proveedor.getEmail())) {
                    return ResponseEntity.badRequest().body("El email '" + proveedor.getEmail() + "' ya está en uso");
                }
            }
        }

        proveedores updatedProveedor = proveedoresService.updateProveedor(id, proveedor);
        return ResponseEntity.ok(updatedProveedor);
    }

    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable Integer id) {
        proveedoresService.deleteProveedor(id);
    }
}
