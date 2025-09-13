package com.jeremymendez.Rautomotriz.repository;

import com.jeremymendez.Rautomotriz.model.productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface productosRepository extends JpaRepository<productos, Integer> {
}
