package com.agis.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="nota")
public class Nota {
    
    @Id
    private int codigoNota;

    @JoinColumn(name="codigo_vinculo")
    private Vinculo vinculo;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public void setCodigoNota(int codigoNota) {
        this.codigoNota = codigoNota;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public int getCodigoNota() {
        return codigoNota;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }
}
