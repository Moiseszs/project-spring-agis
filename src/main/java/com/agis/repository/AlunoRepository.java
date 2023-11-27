package com.agis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agis.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, String>{
        
}
