package br.com.topcar.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.topcar.model.Peca;
import br.com.topcar.model.utils.Car;

public class PecaOutputStream extends OutputStream {

    private OutputStream op;
    private Peca[] pecas;
    private final ByteArrayOutputStream buffer;

    public PecaOutputStream() {
        this.buffer = new ByteArrayOutputStream();
    }

    public PecaOutputStream(OutputStream op, Peca[] pecas) {
        this.op = op;
        this.pecas = pecas;
        this.buffer = new ByteArrayOutputStream();
    }

    public void writeSystem() {

        PrintStream opLocal = new PrintStream(op);

        // envia quantidade de pessoas;
        int qtd_peca = pecas.length;
        opLocal.println("Quantidade de pessoas: " + qtd_peca);

        // envia os dados de um conjunto (array) de Pessoas
        for (Peca peca : this.pecas) {
            if (peca != null) {
                int id = peca.getId();
                int tamanhoNomePeca = peca.getNome().getBytes().length;
                String nome = peca.getNome();
                BigDecimal valor = peca.getValor();
                LocalDate data_fabricacao = peca.getData_fabricacao();
                Car carro = peca.getCarro();

                opLocal.println(" id: " + id + "\n" +
                        " tamanho_nome: " + tamanhoNomePeca + "\n" +
                        " nome da peça: " + nome + "\n" +
                        " valor: " + valor + "\n" +
                        " Data de fabricação : " + data_fabricacao + "\n" +
                        " Carro: " + carro + "\n");
            }
        }
    }

    public void writeFile() {
        // envia os dados de um conjunto (array) de Pessoas
    }

    public void writeTCP() {
        // envia os dados de um conjunto (array) de Pessoas
    }

    @Override
    public void write(int b) throws IOException {
        // TODO Auto-generated method stub
        buffer.write(b);
        // super.write(b);
    }

    @Override
    public String toString() {
        return "Ola, mundo! Estamos sobrescrevendo o método toString()!\n"
                + " PecasOutputStream [ \n"
                + " getClass()=" + getClass() + ",\n"
                + " hashCode()=" + hashCode() + ",\n"
                + " toString()=" + super.toString() + "]";
    }

}
