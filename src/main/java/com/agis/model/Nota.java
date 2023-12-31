package com.agis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "nota")
public class Nota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne
    @JoinColumn(name="vinculo_id")
    private Vinculo vinculo;
    
    @ManyToOne
    @JoinColumn(name="avaliacao_codigo")
    private Avaliacao avaliacao;

    private double nota;

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getNota() {
        return nota;
    }
    
    public Vinculo getVinculo() {
        return vinculo;
    }
}
