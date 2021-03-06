package serverside.server;

import serverside.AuthService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MyServer {
    private final int PORT = 8080;

    private List<ClientHandler> clients;
    private AuthService authService;
    private String fileName;
    private String fileTemp;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            fileName = "chatserver.txt";
            fileTemp = "chatserverTemp.txt";
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<ClientHandler>();
            while (true) {
                System.out.println("Server waiting for connection...");
                Socket socket = server.accept();
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

    public synchronized void broadcastMessage(String message) throws IOException {
        for (ClientHandler c: clients) {
            c.sendMessage(message);
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

    public synchronized void saveInFile100(String message) throws IOException {
        saveInFile(message); // first saving the message line to the file
        trimFileTo100(); // then trim the file to have the last 100 lines
    }

    public synchronized void saveInFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void trimFileTo100() throws IOException {
        File file = new File(fileName);
        File fileNew = new File(fileTemp);
        Scanner fileScanner = new Scanner(file);
        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(file)), Charset.defaultCharset());
        if (lines.size() > 100) fileScanner.nextLine(); // max size of the number of lines available is 100
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNew))) {
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileScanner.close();
        file.delete();

        fileNew.renameTo(file);
    }

}
