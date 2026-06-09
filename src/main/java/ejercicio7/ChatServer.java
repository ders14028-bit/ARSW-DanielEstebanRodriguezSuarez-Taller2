package ejercicio7;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.util.*;

public class ChatServer extends UnicastRemoteObject implements ChatService {
    
    private Map<String, Queue<String>> clientQueues = new HashMap<>();

    public ChatServer() throws RemoteException {}

    @Override
    public void sendMessage(String message) throws RemoteException {
        System.out.println("Mensaje recibido: " + message);
        
        for (Queue<String> queue : clientQueues.values()) {
            queue.add(message);
        }
    }

    @Override
    public String receiveMessage(String clientId) throws RemoteException {
        clientQueues.putIfAbsent(clientId, new LinkedList<>());
        return clientQueues.get(clientId).poll();
    }


    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.createRegistry(23000);
        registry.rebind("ChatService", new ChatServer());
        System.out.println("Servidor de chat listo en puerto 23000...");
        synchronized (ChatServer.class) {
            ChatServer.class.wait();
        }
    }

}

