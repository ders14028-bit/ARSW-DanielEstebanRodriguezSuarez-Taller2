package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio4Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35001);
        System.out.println("Servidor trigonométrico esperando en puerto 35001...");

        Socket clientSocket = serverSocket.accept();
        System.out.println("Cliente conectado.");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream())
        );

        String funcion = "cos"; 

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.startsWith("fun:")) {
                funcion = inputLine.substring(4);
                System.out.println("Función cambiada a: " + funcion);
                out.println("Función cambiada a: " + funcion);
            } else {
                try {
                    double numero = Double.parseDouble(inputLine);
                    double resultado;

                    switch (funcion) {
                        case "sin" -> resultado = Math.sin(numero);
                        case "tan" -> resultado = Math.tan(numero);
                        default   -> resultado = Math.cos(numero);
                    }

                    System.out.println(funcion + "(" + numero + ") = " + resultado);
                    out.println(resultado);
                } catch (NumberFormatException e) {
                    out.println("Error: ingresa un número válido o 'fun:sin', 'fun:cos', 'fun:tan'");
                }
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
