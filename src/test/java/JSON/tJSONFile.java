package JSON;

import Load.CargaDeDadosStorage;
import br.com.topcar.io.json.PecaJsonFileRepository;
import br.com.topcar.model.entity.Peca;

import java.nio.file.Path;

public class tJSONFile {
    public static void main(String[] args) {
        try {
            Peca[] pecasOriginais = {
                    CargaDeDadosStorage.amortecedor1,
                    CargaDeDadosStorage.bateria1,
                    CargaDeDadosStorage.farol1
            };

            PecaJsonFileRepository repo = new PecaJsonFileRepository();
            Path arquivo = Path.of("pecas.json");

            System.out.println("=== Escrevendo JSON ===");
            repo.salvar(arquivo, pecasOriginais);
            System.out.println("Arquivo gerado: " + arquivo.toAbsolutePath());

            System.out.println("=== Lendo JSON ===");
            Peca[] pecasLidas = repo.carregar(arquivo);

            System.out.println("Quantidade lida: " + pecasLidas.length);
            for (Peca p : pecasLidas) {
                System.out.println("Lida: " + p);
            }
        } catch (Exception e) {
            System.out.println("Erro no teste JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
