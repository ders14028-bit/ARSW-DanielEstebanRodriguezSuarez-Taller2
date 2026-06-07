package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio4Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 35001);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        Scanner scanner = new Scanner(System.in);

        System.out.println("Conectado. Ingresa un número o cambia función con fun:sin / fun:cos / fun:tan");
        while (true) {
            System.out.print("Entrada: ");
            String input = scanner.nextLine();
            out.println(input);
            System.out.println("Respuesta: " + in.readLine());
        }
    }
}