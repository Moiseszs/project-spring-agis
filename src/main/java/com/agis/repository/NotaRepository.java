package com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agis.model.Nota;

public interface NotaRepository extends JpaRepository<Nota, Integer>{
    
}
