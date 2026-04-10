package br.com.topcar.model.storage;

import br.com.topcar.model.Peca;

public class Farol extends Peca {
    private String tipo; // ex: alto, baixo, milha
    private boolean led; // Indicador se é LED

    // Getters e Setters
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isLed() { return led; }
    public void setLed(boolean led) { this.led = led; }

    @Override
    public String toString() {
        return "Farol{" +
                "tipo='" + tipo + '\'' +
                ", led=" + led +
                '}';
    }
}
