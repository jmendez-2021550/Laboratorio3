package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.proveedores;
import java.util.List;

public interface proveedoresService {
    List<proveedores> getAllProveedores();
    proveedores getProveedorById(Integer id);
    proveedores saveProveedor(proveedores proveedor);
    proveedores updateProveedor(Integer id, proveedores proveedor);
    void deleteProveedor(Integer id);
}
