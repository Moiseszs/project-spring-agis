package com.agis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agis.model.RelatorioNotas;

public interface RelatorioNotasRepository extends JpaRepository<RelatorioNotas, String>{
    
    @Query(value = "SELECT * FROM relatorioNotas", nativeQuery = true)
    public List<RelatorioNotas> findAll();
}
