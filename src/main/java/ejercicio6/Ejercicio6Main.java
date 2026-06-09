package ejercicio6;

import java.net.*;

public class Ejercicio6Main {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(3000); 
        InetAddress address = InetAddress.getByName("127.0.0.1");

        String ultimaHora = "Sin hora aún";

        while (true) {
            try {
                
                byte[] buf = new byte[256];
                DatagramPacket request = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(request);

                DatagramPacket response = new DatagramPacket(buf, buf.length);
                socket.receive(response);
                ultimaHora = new String(response.getData(), 0, response.getLength());
                System.out.println("Hora del servidor: " + ultimaHora);

            } catch (Exception e) {
                
                System.out.println("Servidor no disponible. Última hora: " + ultimaHora);
            }

            Thread.sleep(5000); 
        }
    }
}
