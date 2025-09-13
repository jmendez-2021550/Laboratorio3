package com.jeremymendez.Rautomotriz.Controller;

import com.jeremymendez.Rautomotriz.model.productos;
import com.jeremymendez.Rautomotriz.service.productosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class productosController {

    private final productosService productosService;

    public productosController(productosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public List<productos> getAllProductos() {
        return productosService.getAllProductos();
    }

    @GetMapping("/{id}")
    public productos getProductoById(@PathVariable Integer id) {
        return productosService.getProductoById(id);
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody productos producto) {
        // Validación de campos vacíos
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo descripción está vacío");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("El campo precio es inválido");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            return ResponseEntity.badRequest().body("El campo stock es inválido");
        }

        // Validación de duplicados
        List<productos> productosList = productosService.getAllProductos();
        for (productos p : productosList) {
            if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                return ResponseEntity.badRequest().body("El nombre del producto '" + producto.getNombre() + "' ya está en uso");
            }
        }

        productos savedProducto = productosService.saveProducto(producto);
        return ResponseEntity.ok(savedProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Integer id, @RequestBody productos producto) {
        // Validación de campos vacíos
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo nombre está vacío");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("El campo descripción está vacío");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            return ResponseEntity.badRequest().body("El campo precio es inválido");
        }
        if (producto.getStock() == null || producto.getStock() < 0) {
            return ResponseEntity.badRequest().body("El campo stock es inválido");
        }

        // Validación de duplicados (excluyendo el producto actual)
        List<productos> productosList = productosService.getAllProductos();
        for (productos p : productosList) {
            if (!p.getIdProducto().equals(id)) { // Excluir el producto actual
                if (p.getNombre().equalsIgnoreCase(producto.getNombre())) {
                    return ResponseEntity.badRequest().body("El nombre del producto '" + producto.getNombre() + "' ya está en uso");
                }
            }
        }

        productos updatedProducto = productosService.updateProducto(id, producto);
        return ResponseEntity.ok(updatedProducto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Integer id) {
        productosService.deleteProducto(id);
    }
}
