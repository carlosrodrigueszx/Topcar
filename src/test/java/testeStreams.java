import br.com.topcar.model.Peca;
import br.com.topcar.model.Pedido;
import br.com.topcar.model.user.Cliente;
import br.com.topcar.io.*;
import java.io.*;

public class testeStreams {
    public static void main(String[] args) {
        Peca[] pecas = {
            CargaDeDadosStorage.amortecedor1,
        };

        try 
        {

            FileOutputStream fos = new FileOutputStream("pecas.txt");
            // FileOutputStream fos = new FileOutputStream("pecas.ser");
            PecaOutputStream pos = new PecaOutputStream(fos, pecas);
            pos.writeSystem();
            pos.close();
            fos.close();

            
            FileInputStream fis = new FileInputStream("person.txt");
            PecaInputStream pis = new PecaInputStream(fis);
            // Peca deserializedPerson = (Peca) PecaInputStream.readObject();
            pos.close();
            fis.close();

            // System.out.println("Nome: " + deserializedPerson.getName());
            // System.out.println(": " + deserializedPerson.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        }

       
    }
}
