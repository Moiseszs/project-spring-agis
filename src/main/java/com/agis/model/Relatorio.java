package com.agis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Relatorio {
    
    @Id
    private String id;

    private String aluno;

    private String disciplina;

    @Column(name="quantidade_faltas")
    private int quantidadeFaltas;

    @Column(name="total_faltas")
    private int totalFaltas;

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantidadeFaltas(int quantidadeFaltas) {
        this.quantidadeFaltas = quantidadeFaltas;
    }

    public void setTotalFaltas(int totalFaltas) {
        this.totalFaltas = totalFaltas;
    }

    public String getAluno() {
        return aluno;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getId() {
        return id;
    }

    public int getQuantidadeFaltas() {
        return quantidadeFaltas;
    }

    public int getTotalFaltas() {
        return totalFaltas;
    }
}
