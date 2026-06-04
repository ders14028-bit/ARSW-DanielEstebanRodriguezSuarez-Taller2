package ejercicio5;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class SimpleHttpServerApp {

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("HTTP server listening on http://localhost:" + port);

            while (true) {
                try (Socket client = serverSocket.accept()) {
                    handleRequest(client);
                } catch (Exception requestError) {
                    System.err.println("Request error: " + requestError.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }

    private static void handleRequest(Socket client) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
        BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());

        String requestLine = in.readLine();
        if (requestLine == null || requestLine.isBlank()) {
            return;
        }

        String[] parts = requestLine.split(" ");
        if (parts.length < 2 || !"GET".equals(parts[0])) {
            writeResponse(out, "405 Method Not Allowed", "text/plain", "Only GET is supported".getBytes(StandardCharsets.UTF_8));
            return;
        }

        String rawPath = parts[1];
        String path = rawPath.equals("/") ? "/index.html" : rawPath;

        if ("/index.html".equals(path)) {
            String html = """
                    <!DOCTYPE html>
                    <html>
                    <head><meta charset=\"UTF-8\"><title>Simple Java HTTP Server</title></head>
                    <body>
                      <h1>Simple Java HTTP Server</h1>
                      <p>This page is served from Java SE sockets.</p>
                      <p>Image test:</p>
                      <img src=\"/sample.png\" alt=\"sample image\" />
                      <p>Place a file named sample.png next to where you run this app.</p>
                    </body>
                    </html>
                    """;
            writeResponse(out, "200 OK", "text/html; charset=UTF-8", html.getBytes(StandardCharsets.UTF_8));
            return;
        }

        File file = new File("." + path).getCanonicalFile();
        if (!file.exists() || !file.isFile()) {
            writeResponse(out, "404 Not Found", "text/plain", "Not found".getBytes(StandardCharsets.UTF_8));
            return;
        }

        String contentType = guessContentType(file.getName());
        byte[] body = Files.readAllBytes(file.toPath());
        writeResponse(out, "200 OK", contentType, body);
    }

    private static String guessContentType(String name) {
        String lower = name.toLowerCase();
        if (lower.endsWith(".png")) {
            return "image/png";
        }
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) {
            return "image/jpeg";
        }
        if (lower.endsWith(".gif")) {
            return "image/gif";
        }
        if (lower.endsWith(".html")) {
            return "text/html; charset=UTF-8";
        }
        return "application/octet-stream";
    }

    private static void writeResponse(BufferedOutputStream out, String status, String contentType, byte[] body) throws Exception {
        String headers = "HTTP/1.1 " + status + "\r\n"
                + "Content-Type: " + contentType + "\r\n"
                + "Content-Length: " + body.length + "\r\n"
                + "Connection: close\r\n\r\n";
        out.write(headers.getBytes(StandardCharsets.UTF_8));
        out.write(body);
        out.flush();
    }
}
