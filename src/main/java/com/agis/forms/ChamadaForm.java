package com.agis.forms;

import java.time.LocalDate;
import java.util.List;

public class ChamadaForm {
    
    private List<Integer> presencas;
    private int codigoDisciplina;
    private LocalDate dataOcorrida;

    public List<Integer> getPresencas() {
        return presencas;
    }

    public void setPresencas(List<Integer> presencas) {
        this.presencas = presencas;
    }

    public int getCodigoDisciplina() {
        return codigoDisciplina;
    }

    public void setCodigoDisciplina(int codigoDisciplina) {
        this.codigoDisciplina = codigoDisciplina;
    }

    public void setDataOcorrida(LocalDate dataOcorrida) {
        this.dataOcorrida = dataOcorrida;
    }

    public LocalDate getDataOcorrida() {
        return dataOcorrida;
    }
}
