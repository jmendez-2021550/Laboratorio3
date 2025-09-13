package com.jeremymendez.Rautomotriz.Controller;

import com.jeremymendez.Rautomotriz.model.ventas;
import com.jeremymendez.Rautomotriz.service.ventasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class ventasController {

    private final ventasService ventasService;

    public ventasController(ventasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public List<ventas> getAllVentas() {
        return ventasService.getAllVentas();
    }

    @GetMapping("/{id}")
    public ventas getVentaById(@PathVariable Integer id) {
        return ventasService.getVentaById(id);
    }

    @PostMapping
    public ResponseEntity<?> createVenta(@RequestBody ventas venta) {
        // Validación de campos obligatorios
        if (venta.getCliente() == null) {
            return ResponseEntity.badRequest().body("El cliente es obligatorio");
        }
        if (venta.getProducto() == null) {
            return ResponseEntity.badRequest().body("El producto es obligatorio");
        }
        if (venta.getCantidad() == null || venta.getCantidad() <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor que cero");
        }
        if (venta.getFechaVenta() == null) {
            return ResponseEntity.badRequest().body("La fecha de venta es obligatoria");
        }
        if (venta.getTotal() == null || venta.getTotal() <= 0) {
            return ResponseEntity.badRequest().body("El total debe ser mayor que cero");
        }

        ventas savedVenta = ventasService.saveVenta(venta);
        return ResponseEntity.ok(savedVenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVenta(@PathVariable Integer id, @RequestBody ventas venta) {
        // Validación de campos obligatorios
        if (venta.getCliente() == null) {
            return ResponseEntity.badRequest().body("El cliente es obligatorio");
        }
        if (venta.getProducto() == null) {
            return ResponseEntity.badRequest().body("El producto es obligatorio");
        }
        if (venta.getCantidad() == null || venta.getCantidad() <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor que cero");
        }
        if (venta.getFechaVenta() == null) {
            return ResponseEntity.badRequest().body("La fecha de venta es obligatoria");
        }
        if (venta.getTotal() == null || venta.getTotal() <= 0) {
            return ResponseEntity.badRequest().body("El total debe ser mayor que cero");
        }

        ventas updatedVenta = ventasService.updateVenta(id, venta);
        return ResponseEntity.ok(updatedVenta);
    }

    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Integer id) {
        ventasService.deleteVenta(id);
    }
}
