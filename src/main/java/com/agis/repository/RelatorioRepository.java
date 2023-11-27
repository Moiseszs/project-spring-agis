package com.agis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agis.model.Relatorio;

public interface RelatorioRepository extends JpaRepository<Relatorio, String>{
    
    @Query(value = "SELECT * FROM relatorio_frequencia()", nativeQuery = true)
    public List<Relatorio> findAll();
}
