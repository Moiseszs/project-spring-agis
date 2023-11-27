package com.agis.forms;

import com.agis.model.Aluno;

public class MatriculaForm {

    private Aluno aluno;
    private int codigoCurso;

    public Aluno getAluno() {
        return aluno;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setAluno(Aluno aluno) throws Exception {
        this.aluno = aluno;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }
}
