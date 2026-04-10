package br.com.topcar.model;

import br.com.topcar.service.interfaces.strategy.iPromoStrategy;

import java.math.BigDecimal;

public class ItemPedido implements iPromoStrategy {
    private Peca peca;
    private int quantidade;
    private BigDecimal valorItem;
    private BigDecimal desconto;

    public ItemPedido() {
    }

    public ItemPedido(Peca peca, int quantidade) {
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorItem = peca.getValorTotal().multiply(BigDecimal.valueOf(quantidade));
        this.desconto = BigDecimal.ZERO;
    }

    public ItemPedido(Peca peca, int quantidade, BigDecimal desconto) {
        this.peca = peca;
        this.quantidade = quantidade;
        this.valorItem = aplicarDesconto(desconto);
    }

    @Override
    public BigDecimal aplicarDesconto(BigDecimal percentual) {
        BigDecimal valorDescontado = this.peca.getValorTotal().multiply(percentual);
        return this.peca.getValorTotal().subtract(valorDescontado);
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorItem() {
        return valorItem;
    }

    public void setValorItem(BigDecimal valorPedido) {
        this.valorItem = valorPedido;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "peca=" + peca +
                ", quantidade=" + quantidade +
                ", valorItem=" + valorItem +
                ", desconto=" + desconto +
                '}';
    }
}
