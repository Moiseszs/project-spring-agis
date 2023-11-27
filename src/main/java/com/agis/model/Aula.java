package com.agis.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="aula")
public class Aula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @Column(name="data_ocorrida")
    private LocalDate dataOcorrida;

    @ManyToOne
    @JoinColumn(name="disciplina_codigo")
    private Disciplina disciplina;

    @OneToMany(mappedBy = "aula")
    private List<Frequencia> frequencias;

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public void setDataOcorrida(LocalDate dataOcorrida) {
        this.dataOcorrida = dataOcorrida;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getCodigo() {
        return codigo;
    }

    public LocalDate getDataOcorrida() {
        return dataOcorrida;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }
}
