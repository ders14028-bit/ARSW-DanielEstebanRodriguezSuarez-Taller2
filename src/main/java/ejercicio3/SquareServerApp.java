package ejercicio3;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SquareServerApp {

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 35000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Square server listening on port " + port);

            while (true) {
                try (Socket client = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new java.io.InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                    String input = in.readLine();
                    if (input == null) {
                        continue;
                    }

                    try {
                        double value = Double.parseDouble(input.trim());
                        double result = value * value;
                        out.println(result);
                    } catch (NumberFormatException e) {
                        out.println("ERROR: Input is not a valid number");
                    }
                } catch (Exception clientError) {
                    System.err.println("Client handling error: " + clientError.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
