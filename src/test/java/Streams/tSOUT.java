package Streams;

import Load.CargaDeDadosStorage;
import br.com.topcar.io.streams.PecaInputStream;
import br.com.topcar.io.streams.PecaOutputStream;
import br.com.topcar.model.entity.Peca;

import java.io.*;

public class tSOUT {
    public static void main(String[] args) {
        Peca[] pecas = { CargaDeDadosStorage.amortecedor1 };

        try {
            System.out.println("=== Saída padrão ===");
            PecaOutputStream posSOUT = new PecaOutputStream(System.out, pecas, pecas.length);
            posSOUT.writeSystem();
            posSOUT.close();

            System.out.println("=== Escrevendo arquivo ===");
            FileOutputStream fos = new FileOutputStream("pecas.txt");
            PecaOutputStream posFILE = new PecaOutputStream(fos, pecas, pecas.length);
            posFILE.writeSystem();
            posFILE.close();
            fos.close();

            System.out.println("=== Lendo arquivo ===");
            FileInputStream fis = new FileInputStream("pecas.txt");
            PecaInputStream pis = new PecaInputStream(fis);
            Peca[] lidas = pis.readSystem();
            pis.close();
            fis.close();

            for (Peca p : lidas) {
                System.out.println("Lida: " + p);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
