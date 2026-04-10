package br.com.topcar.model.storage;

import br.com.topcar.model.Peca;

public class Pneu extends Peca {
    private String dimensoes; // ex: 195/65R15
    private String indiceTraction; // ex: AA, A (aderência)

    public Pneu() {
    }

    public Pneu(String dimensoes, String indiceTraction) {
        this.dimensoes = dimensoes;
        this.indiceTraction = indiceTraction;
    }

    // Getters e Setters
    public String getDimensoes() { return dimensoes; }
    public void setDimensoes(String dimensoes) { this.dimensoes = dimensoes; }
    public String getIndiceTraction() { return indiceTraction; }
    public void setIndiceTraction(String indiceTraction) { this.indiceTraction = indiceTraction; }

    @Override
    public String toString() {
        return "Pneu{" +
                "dimensoes='" + dimensoes + '\'' +
                ", indiceTraction='" + indiceTraction + '\'' +
                '}';
    }
}
