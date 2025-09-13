package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.ventas;
import com.jeremymendez.Rautomotriz.repository.ventasRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ventasServiceImpl implements ventasService {

    private final ventasRepository ventasRepository;

    public ventasServiceImpl(ventasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<ventas> getAllVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public ventas getVentaById(Integer id) {
        return ventasRepository.findById(id).orElse(null);
    }

    @Override
    public ventas saveVenta(ventas venta) {
        return ventasRepository.save(venta);
    }

    @Override
    public ventas updateVenta(Integer id, ventas venta) {
        ventas existingVenta = ventasRepository.findById(id).orElse(null);
        if (existingVenta != null) {
            existingVenta.setCliente(venta.getCliente());
            existingVenta.setProducto(venta.getProducto());
            existingVenta.setCantidad(venta.getCantidad());
            existingVenta.setFechaVenta(venta.getFechaVenta());
            existingVenta.setTotal(venta.getTotal());
            return ventasRepository.save(existingVenta);
        }
        return null;
    }

    @Override
    public void deleteVenta(Integer id) {
        ventasRepository.deleteById(id);
    }
}
