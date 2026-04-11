package br.com.topcar.io;

import java.io.BufferedReader;
import java.io.IOException;
import br.com.topcar.model.utils.Car;
import br.com.topcar.model.utils.LocalDateUtil;
import br.com.topcar.model.Peca;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PecaInputStream extends InputStream {

    private Peca[] pecas;
    private InputStream ins;

    public PecaInputStream(InputStream ins) {
        this.ins = ins;
    }

    public Peca[] readSystem() throws IOException {
        
        BufferedReader leitor = new BufferedReader(new InputStreamReader(ins)); 
        
        int qtd_pecas = Integer.parseInt(leitor.readLine());

        Peca[] pecas = new Peca[qtd_pecas];



        for (int i=0; i < qtd_pecas; i++){

            int id = Integer.parseInt(leitor.readLine());
            
            int tamanhoNomePeca = Integer.parseInt(leitor.readLine());
            String nome = leitor.readLine();

            int tamanhoValor = Integer.parseInt(leitor.readLine());
            BigDecimal valor = new BigDecimal(leitor.readLine());
            
            int tamanhoData = Integer.parseInt(leitor.readLine());
            LocalDate data_fabricacao = LocalDate.parse(leitor.readLine());
            
            String nomecar = leitor.readLine();
            int anocar = Integer.parseInt(leitor.readLine());
            String modelocar = leitor.readLine();

            Car carro = new Car(nomecar,anocar,modelocar);
            pecas[i] = new Peca(id,nome,valor,data_fabricacao,carro);


        }
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
