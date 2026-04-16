package br.com.topcar.model.entity.historico;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private List<ItemPedido> itens = new ArrayList<>();

    void addItemPedido(ItemPedido item) {
        this.itens.add(item);
    }

    public Pedido() {
    }

    public Pedido(int id, List<ItemPedido> itens) {
        this.id = id;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", itens=" + itens +
                '}';
    }
}
