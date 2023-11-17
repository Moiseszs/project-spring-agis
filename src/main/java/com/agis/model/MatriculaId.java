package com.agis.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Embeddable
public class MatriculaId implements Serializable {
    
    @OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="aluno_ra", referencedColumnName = "ra")
	private Aluno aluno;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="codigo_curso", referencedColumnName = "codigo")
	private Curso curso;
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }
}
