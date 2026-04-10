package br.com.topcar.model;

import br.com.topcar.model.utils.Car;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Peca {
    private int id;
    private String nome;
    private BigDecimal valorTotal;
    private LocalDate data_fabricacao;
    private Car carro;

    public Peca() {
    }

    public Peca(int id, String nome, BigDecimal valorTotal, LocalDate data_fabricacao, Car carro) {
        this.id = id;
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.data_fabricacao = data_fabricacao;
        this.carro = carro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData_fabricacao() {
        return data_fabricacao;
    }

    public void setData_fabricacao(LocalDate data_fabricacao) {
        this.data_fabricacao = data_fabricacao;
    }

    public Car getCarro() {
        return carro;
    }

    public void setCarro(Car carro) {
        this.carro = carro;
    }

    @Override
    public String toString() {
        return "Peca{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valorTotal=" + valorTotal +
                ", data_fabricacao=" + data_fabricacao +
                ", carro=" + carro +
                '}';
    }
}
