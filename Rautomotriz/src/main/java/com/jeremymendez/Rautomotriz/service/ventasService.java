package com.jeremymendez.Rautomotriz.service;

import com.jeremymendez.Rautomotriz.model.ventas;
import java.util.List;

public interface ventasService {
    List<ventas> getAllVentas();
    ventas getVentaById(Integer id);
    ventas saveVenta(ventas venta);
    ventas updateVenta(Integer id, ventas venta);
    void deleteVenta(Integer id);
}
