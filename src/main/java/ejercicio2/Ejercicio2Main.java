package ejercicio2;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ejercicio2Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter URL: ");
        String urlString = scanner.nextLine();
        
        try {
            URL url = new URL(urlString);
            
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream())
            );
            
            PrintWriter writer = new PrintWriter(
                new FileWriter("resultado.html")
            );
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.println(linea);
            }
            
            reader.close();
            writer.close();
            System.out.println(" File saved resultado.html");
            
        } catch (MalformedURLException e) {
            System.err.println("Bad Url: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        scanner.close();
    }
}