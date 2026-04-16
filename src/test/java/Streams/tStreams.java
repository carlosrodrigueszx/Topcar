package Streams;

import Load.CargaDeDadosStorage;
import br.com.topcar.io.streams.PecaInputStream;
import br.com.topcar.io.streams.PecaOutputStream;
import br.com.topcar.model.entity.Peca;

import java.io.*;

public class tStreams {
    public static void main(String[] args) {
        Peca[] pecas = { CargaDeDadosStorage.amortecedor1 };

        try {
            FileOutputStream fos = new FileOutputStream("pecas.txt");
            PecaOutputStream pos = new PecaOutputStream(fos, pecas, pecas.length);
            pos.writeSystem();
            pos.close();
            fos.close();

            FileInputStream fis = new FileInputStream("pecas.txt");
            PecaInputStream pis = new PecaInputStream(fis);
            Peca[] lidas = pis.readSystem();
            pis.close();
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
