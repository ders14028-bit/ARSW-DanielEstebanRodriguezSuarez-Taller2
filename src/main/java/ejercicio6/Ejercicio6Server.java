package ejercicio6;

import java.net.*;
import java.util.Date;

public class Ejercicio6Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(4445);
        System.out.println("Servidor de hora UDP corriendo en puerto 4445...");

        while (true) {
            byte[] buf = new byte[256];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            String hora = new Date().toString();
            buf = hora.getBytes();
            packet = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
            socket.send(packet);
            System.out.println("Hora enviada: " + hora);
        }
    }
}
