package com.agis.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="frequencia")
public class Frequencia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne
    @JoinColumn(name="aula_codigo")
    private Aula aula;

    @ManyToOne
    @JoinColumn(name="vinculo_id")
    private Vinculo vinculo;

    @Column(name="horas_aula")
    private int horasAula;

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setHorasAula(int horasAula) {
        this.horasAula = horasAula;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }
}
