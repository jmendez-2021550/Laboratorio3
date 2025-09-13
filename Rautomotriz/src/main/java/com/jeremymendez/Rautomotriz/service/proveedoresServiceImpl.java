package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.proveedores;
import com.jeremymendez.Rautomotriz.repository.proveedoresRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class proveedoresServiceImpl implements proveedoresService {

    private final proveedoresRepository proveedoresRepository;

    public proveedoresServiceImpl(proveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }

    @Override
    public List<proveedores> getAllProveedores() {
        return proveedoresRepository.findAll();
    }

    @Override
    public proveedores getProveedorById(Integer id) {
        return proveedoresRepository.findById(id).orElse(null);
    }

    @Override
    public proveedores saveProveedor(proveedores proveedor) {
        return proveedoresRepository.save(proveedor);
    }

    @Override
    public proveedores updateProveedor(Integer id, proveedores proveedor) {
        proveedores existingProveedor = proveedoresRepository.findById(id).orElse(null);
        if (existingProveedor != null) {
            existingProveedor.setNombre(proveedor.getNombre());
            existingProveedor.setTelefono(proveedor.getTelefono());
            existingProveedor.setEmail(proveedor.getEmail());
            existingProveedor.setDireccion(proveedor.getDireccion());
            return proveedoresRepository.save(existingProveedor);
        }
        return null;
    }

    @Override
    public void deleteProveedor(Integer id) {
        proveedoresRepository.deleteById(id);
    }
}
