package br.com.topcar.sockets;
import br.com.topcar.io.replyResponseSerializable;
import br.com.topcar.io.requestResponseSerializable;
import br.com.topcar.sockets.ConnectionObjeto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;

public class TCPServer {
    public static void main(String[] args) {
        if (args != null && args.length > 0 && "obj".equalsIgnoreCase(args[0])) {
            iniciarServidorObjetos();
            return;
        }

        iniciarServidorTexto();
    }

    private static void iniciarServidorTexto() {
        try {
            System.out.println("Servidor TCP texto iniciado");
            int serverPort = 7897;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println(clientSocket.getInetAddress());
                System.out.println("conexao texto estabelecida");
                ConnectionTexto c = new ConnectionTexto(clientSocket);
//              c.run();
            }
        } catch (IOException e) {
            System.out.println("Listen socket:" + e.getMessage());
        }
    }

    private static void iniciarServidorObjetos() {
        try {
            System.out.println("Servidor TCP objeto iniciado");
            int serverPort = 7900;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                System.out.println(clientSocket.getInetAddress());
                System.out.println("conexao objeto estabelecida");
                ConnectionObjeto c = new ConnectionObjeto(clientSocket);
//              c.run();
            }
        } catch (IOException e) {
            System.out.println("Listen socket (obj):" + e.getMessage());
        }
    }
}

public class ConnectionTexto extends Thread {
    private final DataInputStream in;
    private final DataOutputStream out;
    private final Socket clientSocket;

    public ConnectionTexto(Socket aClientSocket) throws IOException {
        clientSocket = aClientSocket;
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        this.start();
    }

    public void run() {
        try {
            String data = in.readUTF();
            System.out.println(data);
            out.writeUTF(data.toUpperCase());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline:" + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */
            }
        }
    }
}

public class ConnectionObjeto extends Thread {
    private final Socket clientSocket;

    public ConnectionObjeto(Socket aClientSocket) {
        clientSocket = aClientSocket;
        this.start();
    }

    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            requestResponseSerializable request = (requestResponseSerializable) in.readObject();
            System.out.println("Request objeto recebida: " + request);

            replyResponseSerializable reply = new replyResponseSerializable(
                    true,
                    "Request serializada processada com sucesso",
                    LocalDate.now());

            out.writeObject(reply);
            out.flush();
        } catch (EOFException e) {
            System.out.println("EOF (obj):" + e.getMessage());
        } catch (IOException e) {
            System.out.println("readline (obj):" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found (obj):" + e.getMessage());

        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                /* close failed */
            }
        }

    }
}
