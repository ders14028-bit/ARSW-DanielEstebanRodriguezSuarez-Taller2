package ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio3Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("Servidor esperando en puerto 35000...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream())
        );

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            try {
                double numero = Double.parseDouble(inputLine);
                double cuadrado = numero * numero;
                System.out.println("Recibido: " + numero + " -> Cuadrado: " + cuadrado);
                out.println(cuadrado);
            } catch (NumberFormatException e) {
                out.println("Error: ingresa un número válido");
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
