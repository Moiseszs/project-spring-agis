package com.agis;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agis.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
        
}
