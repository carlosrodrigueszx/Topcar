package br.com.topcar.model.storage;

import br.com.topcar.model.Peca;

public class Bateria extends Peca {
    private int cca; // Corrente de arranque a frio
    private String tipoQuimica; // ex: chumbo-ácido, íon de lítio

    public Bateria() {
    }

    public Bateria(int cca, String tipoQuimica) {
        this.cca = cca;
        this.tipoQuimica = tipoQuimica;
    }

    // Getters e Setters
    public int getCca() { return cca; }
    public void setCca(int cca) { this.cca = cca; }
    public String getTipoQuimica() { return tipoQuimica; }
    public void setTipoQuimica(String tipoQuimica) { this.tipoQuimica = tipoQuimica; }

    @Override
    public String toString() {
        return "Bateria{" +
                "cca=" + cca +
                ", tipoQuimica='" + tipoQuimica + '\'' +
                '}';
    }
}
