package com.agis.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "matricula")
public class Matricula {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
    @ManyToOne
    @JoinColumn(name="aluno_ra")
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name="curso_codigo")
    private Curso curso;

    @OneToMany(mappedBy = "matricula")
    private List<Vinculo> vinculos;

    public int getCodigo() {
        return codigo;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public List<Vinculo> getVinculos() {
        return vinculos;
    }

    public void setVinculos(List<Vinculo> vinculos) {
        this.vinculos = vinculos;
    }
}
