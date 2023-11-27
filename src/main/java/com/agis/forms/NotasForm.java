package com.agis.forms;

import java.util.List;

public class NotasForm {
    
    private List<Double> avaliacao0;
    private List<Double> avaliacao1;
    private List<Double> avaliacao2;
    private int codigoDisciplina;

    public List<Double> getAvaliacao0() {
        return avaliacao0;
    }

    public List<Double> getAvaliacao1() {
        return avaliacao1;
    }

    public List<Double> getAvaliacao2() {
        return avaliacao2;
    }

    public void setAvaliacao0(List<Double> avaliacao0) {
        this.avaliacao0 = avaliacao0;
    }

    public void setAvaliacao1(List<Double> avaliacao1) {
        this.avaliacao1 = avaliacao1;
    }
    
    public void setAvaliacao2(List<Double> avaliacao2) {
        this.avaliacao2 = avaliacao2;
    }

    public int getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(int codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }
}
