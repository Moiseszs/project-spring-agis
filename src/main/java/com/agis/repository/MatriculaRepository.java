package com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agis.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    
}
