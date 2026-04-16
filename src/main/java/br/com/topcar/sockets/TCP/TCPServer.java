package br.com.topcar.sockets.TCP;

import br.com.topcar.sockets.connection.ConnectionObjeto;
import br.com.topcar.sockets.connection.ConnectionTexto;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
                // c.run();
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
            }
        } catch (IOException e) {
            System.out.println("Listen socket (obj):" + e.getMessage());
        }
    }
}
