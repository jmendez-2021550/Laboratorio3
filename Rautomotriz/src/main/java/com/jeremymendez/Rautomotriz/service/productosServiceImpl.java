package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.productos;
import com.jeremymendez.Rautomotriz.repository.productosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productosServiceImpl implements productosService {

    private final productosRepository productosRepository;

    public productosServiceImpl(productosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }

    @Override
    public List<productos> getAllProductos() {
        return productosRepository.findAll();
    }

    @Override
    public productos getProductoById(Integer id) {
        return productosRepository.findById(id).orElse(null);
    }

    @Override
    public productos saveProducto(productos producto) {
        return productosRepository.save(producto);
    }

    @Override
    public productos updateProducto(Integer id, productos producto) {
        productos existingProducto = productosRepository.findById(id).orElse(null);
        if (existingProducto != null) {
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setDescripcion(producto.getDescripcion());
            existingProducto.setPrecio(producto.getPrecio());
            existingProducto.setStock(producto.getStock());
            existingProducto.setProveedor(producto.getProveedor());
            return productosRepository.save(existingProducto);
        }
        return null;
    }

    @Override
    public void deleteProducto(Integer id) {
        productosRepository.deleteById(id);
    }
}
