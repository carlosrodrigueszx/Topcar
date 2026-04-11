import br.com.topcar.model.ItemPedido;
import br.com.topcar.model.Peca;
import br.com.topcar.model.Pedido;
import br.com.topcar.model.user.Cliente;
import br.com.topcar.model.utils.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class testeCompra {
    public static void main(String[] args) {

        Car car1 = new Car("Crossfox", LocalDate.now().getYear(), "Sport");
        Peca p1 = new Peca(1, "Amortecedor TheBest", BigDecimal.valueOf(200.0), LocalDate.now(), car1);

        ItemPedido itemPedido = new ItemPedido(p1, 2, BigDecimal.valueOf(15));
        // Itens que vou cadastrar no Pedido
        List<ItemPedido> listaItens = new ArrayList<>();
        listaItens.add(itemPedido);

        // Exemplo de pedido com a lista de itens que criamos
        Pedido pedido = new Pedido(1, listaItens);

        // Pedidos com os itens, vai ser cadastrado no Cliente
        List<Pedido> pedidos = new ArrayList<>();

        // Exemplo adicionado a lista de pedidos que será cadastrada
        pedidos.add(pedido);

        Cliente c1 = new Cliente("11122233345", "Matias", 28, pedidos);

        System.out.println(c1.toString());
    }
}
