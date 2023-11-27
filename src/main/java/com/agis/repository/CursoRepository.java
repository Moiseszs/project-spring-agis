package com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agis.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer>{

    
}