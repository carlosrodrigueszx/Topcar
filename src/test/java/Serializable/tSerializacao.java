package Serializable;

import br.com.topcar.io.responses.replyResponseSerializable;
import br.com.topcar.io.responses.requestResponseSerializable;
import br.com.topcar.sockets.connection.ConnectionObjeto;
import br.com.topcar.sockets.connection.ConnectionTexto;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class tSerializacao {
    public static void main(String[] args) {
        System.out.println("Iniciando testes da Questao 4...");

        try {
            System.out.println("=== Teste 1: TCP texto ===");
            try (ServerSocket server = new ServerSocket(0)) {
                int port = server.getLocalPort();

                Thread acceptThread = new Thread(() -> {
                    try {
                        Socket accepted = server.accept();
                        new ConnectionTexto(accepted);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                acceptThread.start();

                try (Socket client = new Socket("localhost", port);
                        DataOutputStream out = new DataOutputStream(client.getOutputStream());
                        DataInputStream in = new DataInputStream(client.getInputStream())) {

                    String enviado = "sistemas_distribuidos";
                    out.writeUTF(enviado);
                    out.flush();

                    String recebido = in.readUTF();
                    if (!"SISTEMAS_DISTRIBUIDOS".equals(recebido)) {
                        throw new IllegalStateException(
                                "Teste 1 falhou. Esperado: SISTEMAS_DISTRIBUIDOS, recebido: " + recebido);
                    }
                }
            }
            System.out.println("OK - Teste 1");

            System.out.println("=== Teste 2: TCP objeto ===");
            try (ServerSocket server = new ServerSocket(0)) {
                int port = server.getLocalPort();

                Thread acceptThread = new Thread(() -> {
                    try {
                        Socket accepted = server.accept();
                        new ConnectionObjeto(accepted);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                acceptThread.start();

                try (Socket client = new Socket("localhost", port);
                        ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

                    requestResponseSerializable req = new requestResponseSerializable(
                            "CONSULTAR",
                            "Farol Corolla",
                            "cliente-teste");

                    out.writeObject(req);
                    out.flush();

                    Object response = in.readObject();
                    if (!(response instanceof replyResponseSerializable)) {
                        throw new IllegalStateException("Teste 2 falhou. Resposta nao e replyResponseSerializable.");
                    }

                    replyResponseSerializable reply = (replyResponseSerializable) response;
                    if (!reply.isSuccess()) {
                        throw new IllegalStateException("Teste 2 falhou. Campo success veio false.");
                    }
                    if (reply.getData() == null) {
                        throw new IllegalStateException("Teste 2 falhou. Campo data veio nulo.");
                    }
                }
            }
            System.out.println("OK - Teste 2");

            System.out.println("Todos os testes passaram.");

        } catch (Exception e) {
            System.out.println("Erro nos testes da Questao 4: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
