package ejercicio1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ejercicio1Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a URL: ");
        String input = scanner.nextLine();

        try {
            URL url = new URL(input);

            System.out.println("Protocol:  " + url.getProtocol());
            System.out.println("Authority: " + url.getAuthority());
            System.out.println("Host:      " + url.getHost());
            System.out.println("Port:      " + url.getPort());
            System.out.println("Path:      " + url.getPath());
            System.out.println("Query:     " + url.getQuery());
            System.out.println("File:      " + url.getFile());
            System.out.println("Ref:       " + url.getRef());

        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}