package ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Ejercicio3Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 35000);

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        Scanner scanner = new Scanner(System.in);

        System.out.println("Conectado al servidor. Ingresa números (Ctrl+C para salir):");
        while (true) {
            System.out.print("Número: ");
            String input = scanner.nextLine();
            out.println(input);
            System.out.println("Cuadrado: " + in.readLine());
        }
    }
}
