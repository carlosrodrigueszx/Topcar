package br.com.topcar.io;

import java.io.IOException;
import br.com.topcar.model.utils.Car;
import br.com.topcar.model.utils.LocalDateUtil;
import br.com.topcar.model.Peca;
import java.io.InputStream;
import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PecaInputStream extends InputStream {

    private Peca[] pecas;
    private InputStream ins;

    public PecaInputStream(Peca[] pecas, InputStream ins) {
        this.pecas = pecas;
        this.ins = ins;
    }

    public Peca[] readSystem() {

        Scanner input = new Scanner(ins);
        System.out.println("informe o id da peça");

        int id = input.nextInt();
        System.out.println("informe o nome da peça");

        String nome = input.nextLine();
        System.out.println("informe o valor da peça");

        BigDecimal valor = input.nextBigDecimal();
        System.out.println("informe o a data de fabricação (dd/mm/yyyy)");

        String data_string = input.nextLine();
        LocalDate data = LocalDateUtil.parse(data_string);

        System.out.println("Digite os parametros do carro (nome,ano,modelo)");

        System.out.println("Nome do carro:\n");
        String nomecar = input.nextLine();

        System.out.println("Ano de fabricação em inteiro (somente o ano)\n");
        int anocar = input.nextInt();

        System.out.println("Modelo do carro:\n");
        String modelocar = input.nextLine();

        Car carro = new Car(nomecar, anocar, modelocar);

        pecas[0] = new Peca(id, nome, valor, data, carro);

        input.close();

        return pecas;

    }

    public Peca[] ReadFile() {

        return pecas;

    }

    public Peca[] readTPC() {

        return pecas;

    }

    @Override
    public int read() throws IOException {
        return 0;
    }

}
