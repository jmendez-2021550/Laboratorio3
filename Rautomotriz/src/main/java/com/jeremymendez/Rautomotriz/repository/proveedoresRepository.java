package com.jeremymendez.Rautomotriz.repository;

import com.jeremymendez.Rautomotriz.model.proveedores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface proveedoresRepository extends JpaRepository<proveedores, Integer> {
}
