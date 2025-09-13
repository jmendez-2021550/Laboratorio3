package com.jeremymendez.Rautomotriz.repository;

import com.jeremymendez.Rautomotriz.model.clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clientesRepository extends JpaRepository<clientes, Integer> {
}
