package ejercicio7;

import java.rmi.registry.*;
import java.util.Scanner;
import java.util.UUID;

public class Ejercicio7Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("IP del servidor: ");
        String ip = scanner.nextLine();

        System.out.print("Puerto: ");
        int port = Integer.parseInt(scanner.nextLine());

        System.out.print("Tu nombre: ");
        String nombre = scanner.nextLine();

        Registry registry = LocateRegistry.getRegistry(ip, port);
        ChatService chat = (ChatService) registry.lookup("ChatService");

        String clientId = nombre + "-" + UUID.randomUUID();

        System.out.println("Conectado. Escribe mensajes (Ctrl+C para salir):");

        
        Thread receptor = new Thread(() -> {
            while (true) {
                try {
                    String msg = chat.receiveMessage(clientId);
                    if (msg != null) System.out.println(msg);
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.err.println("Error recibiendo: " + e.getMessage());
                }
            }
        });
        receptor.setDaemon(true);
        receptor.start();

       
        while (true) {
            String input = scanner.nextLine();
            chat.sendMessage(nombre + ": " + input);
        }
    }
}