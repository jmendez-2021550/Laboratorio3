package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.productos;
import java.util.List;

public interface productosService {
    List<productos> getAllProductos();
    productos getProductoById(Integer id);
    productos saveProducto(productos producto);
    productos updateProducto(Integer id, productos producto);
    void deleteProducto(Integer id);
}
