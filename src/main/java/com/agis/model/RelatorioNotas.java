package com.agis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RelatorioNotas {
    
    @Id
    private String ra;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name="nome")
    private String disciplinaNome;

    @Column(name = "media")
    private double media;


    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public double getMedia() {
        return media;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getRa() {
        return ra;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
}
