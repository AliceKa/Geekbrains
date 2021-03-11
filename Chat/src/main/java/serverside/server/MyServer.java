package serverside.server;

import serverside.AuthService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class MyServer {
    private final int PORT = 8080;

    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<ClientHandler>();
            while (true) {
                System.out.println("Server waiting for connection...");
                Socket socket = server.accept();
                System.out.println(socket.getInetAddress().getCanonicalHostName()); // for lesson 8
                System.out.println("Client connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Server disconnected");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler c: clients) {
            c.sendMessage(message);
        }
    }

    public synchronized void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public synchronized void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    public boolean isNicknameBusy(String nick) {
        for (ClientHandler c:clients) {
            if (c.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }
    public synchronized void directedMessage(String message, String clientName) throws IOException {
        for (ClientHandler c: clients) {
            if (c.getName().equals(clientName)) {
                c.sendMessage(message);
            }
        }
    }

    public synchronized void broadcastClientsList(ClientHandler client) throws IOException {
        StringBuilder sb = new StringBuilder("");
        for (ClientHandler c:clients) {
            if (!c.equals(client)) {
                sb.append(c.getName()).append(" ");
            }
        }
        client.sendMessage(sb.toString());
    }

    public synchronized void changeNick(String newNick, String name) {
        authService.changeNick(newNick, name);
    }
}
