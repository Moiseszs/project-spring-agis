package com.agis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agis.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    
    @Query(value = "SELECT * FROM lista_disciplinas_disponiveis(?1)", nativeQuery = true)
    public List<Disciplina> findDisciplinasDisponiveis(String ra);
}
