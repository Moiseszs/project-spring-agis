package com.agis.forms;

import java.util.List;

import com.agis.model.Disciplina;

public class VinculoForm {
    
    private String ra;
    private List<Integer> codigos;
   
    public void setCodigos(List<Integer> codigos) {
        this.codigos = codigos;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public List<Integer> getCodigos() {
        return codigos;
    }

    public String getRa() {
        return ra;
    }
}
