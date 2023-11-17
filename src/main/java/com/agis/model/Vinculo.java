package com.agis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vinculo")
public class Vinculo {
    
    @Id
    private int codigoVinculo;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="aluno_ra"),
        @JoinColumn(name="codigo_curso")
    })
    private Matricula matricula;

    @ManyToOne
    @JoinColumn(name="codigo_disciplina")
    private Disciplina disciplina;

    public void setCodigoVinculo(int codigoVinculo) {
        this.codigoVinculo = codigoVinculo;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public int getCodigoVinculo() {
        return codigoVinculo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Matricula getMatricula() {
        return matricula;
    }
}
