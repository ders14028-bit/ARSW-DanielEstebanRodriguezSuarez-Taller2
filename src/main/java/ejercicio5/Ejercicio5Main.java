package ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

public class Ejercicio5Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(35000);
        System.out.println("Listo para recibir en http://localhost:35000 ...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            OutputStream rawOut = clientSocket.getOutputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String requestLine = in.readLine();
            while (in.ready()) in.readLine();

            String filePath = requestLine.split(" ")[1];
            if (filePath.equals("/")) filePath = "/index.html";

            File file = new File("www" + filePath);

            if (file.exists()) {
                byte[] body = Files.readAllBytes(file.toPath());
                String header = "HTTP/1.1 200 OK\r\nContent-Type: " + getContentType(filePath) + "\r\nContent-Length: " + body.length + "\r\n\r\n";
                rawOut.write(header.getBytes());
                rawOut.write(body);
            } else {
                String body = "<html><body><h1>404 - No encontrado: " + filePath + "</h1></body></html>";
                String header = "HTTP/1.1 404 Not Found\r\nContent-Type: text/html\r\nContent-Length: " + body.length() + "\r\n\r\n";
                rawOut.write(header.getBytes());
                rawOut.write(body.getBytes());
            }

            rawOut.flush();
            clientSocket.close();
        }
    }

    private static String getContentType(String path) {
        if (path.endsWith(".html")) return "text/html";
        if (path.endsWith(".png"))  return "image/png";
        if (path.endsWith(".jpg"))  return "image/jpeg";
        return "application/octet-stream";
    }
}