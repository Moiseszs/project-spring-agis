package com.agis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="vinculo")
public class Vinculo {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne(targetEntity = Matricula.class)
    @JoinColumn(name="matricula_codigo")
    private Matricula matricula;


    @ManyToOne
    @JoinColumn(name="disciplina_codigo")
    private Disciplina disciplina;

    private boolean cursada;

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setCursada(boolean cursada) {
        this.cursada = cursada;
    }

    public boolean getCursada(){
        return this.cursada;
    }
}
