package serverside.server;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class ClientHandler {

    private final MyServer myServer;
    private final Socket socket;
    private final DataInputStream dis;
    private final DataOutputStream dos;

    private String name;
    private boolean isAuthorized;
    private boolean isActive;

    private String fileName;

    public ClientHandler(MyServer myServer, Socket socket) throws IOException {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            Thread t1 = new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException | InterruptedException | SQLException | ClassNotFoundException ignored) {
                } finally {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(20000);
                    System.out.println(Instant.now());
                } catch (InterruptedException ignored) {
                }
                if (!isAuthorized) {
                    try {
                        closeConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.start();
            t2.start();
        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problems with ClientHandler");
        }
    }


    public void authentication() throws IOException, SQLException, ClassNotFoundException {
        int counter = 0;
        while (counter < 3) {
            String str = dis.readUTF();
            if (str.startsWith("/auth")) { //  /auth login password
                counter++;
                String [] arr = str.split("\\s");
                String nick = myServer
                        .getAuthService()
                        .getNickByLoginAndPassword(arr[1], arr[2]);
                fileName = myServer
                        .getAuthService()
                        .getFileNameByLoginAndPassword(arr[1], arr[2]);
                if (nick != null) {
                    if (!myServer.isNicknameBusy(nick)) {
                        isAuthorized = true;
                        sendMessage("User (" + nick + ") successfully logged in");
                        showFromFile();
                        name = nick;
                        myServer.broadcastMessage(name + " joined the chat");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMessage("Nick is busy");
                    }
                } else {
                    sendMessage("Wrong login and password");
                }
            } else {
                sendMessage("Your command needs start with /auth");
            }
        }
        sendMessage("/end");
    }

    public void readMessage() throws IOException, InterruptedException {
        if (isAuthorized) {
            new Thread(()->{
                t1: while (isAuthorized) {
                    Instant start = Instant.now();
                    while (!isActive) {
                        Instant end = Instant.now();
                        long dif = Duration.between(start,end).toMinutes();
                        if (dif == 3) {
                            try {
                                sendMessage("WARNING! You have been logged out due to inactivity");
                                isAuthorized = false;
                                break t1;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();


            while (isAuthorized) {
                isActive = false;
                String messageFromClient = dis.readUTF();
                System.out.println(name + " sent message " + messageFromClient);
                isActive = true;
                if (messageFromClient.startsWith("/")) {
                    if (messageFromClient.equals("/end")) {
                        sendMessage("/end");
                        return;
                    }
                    if (messageFromClient.startsWith("/w")) {
                        String[] arr1 = messageFromClient.split(" ", 3);
                        String nameReceiver = arr1[1];
                        String messageToDirect = name + ": " + arr1[2];
                        if (myServer.isNicknameBusy(nameReceiver)) {
                            myServer.directedMessage(messageToDirect, nameReceiver);
                            String messageToDirect1 = "to " + nameReceiver + " : " + arr1[2];
                            myServer.directedMessage(messageToDirect1, name);
                        } else {
                            myServer.directedMessage("The user with the nick " + nameReceiver + " is not available", name);
                        }
                    }
                    if (messageFromClient.startsWith("/list")) {
                        myServer.broadcastClientsList(this);
                    }

                    if (messageFromClient.startsWith("/changeNick")) {
                        char[] arr1 = messageFromClient.toCharArray();
                        String newNick = new String(arr1, 12,arr1.length-12);
                        myServer.changeNick(newNick,name);
                        myServer.broadcastMessage("The user with the nick " + name + " changed nick to " + newNick);
                        name = newNick;
                    }
                }
                else {
                    myServer.broadcastMessage(name + ": " + messageFromClient);
                }
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        dos.writeUTF(message);
        saveInFile(fileName, message);
        myServer.saveInFile100(message);
    }

    public void showFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chatserver.txt"))) {
            String str;
            while ((str = reader.readLine()) != null) {
                str = "(Archive) " + str;
                dos.writeUTF(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveInFile(String file, String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        if (isAuthorized) {
            myServer.unsubscribe(this);
            myServer.broadcastMessage(name + " left the chat");
        }
        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException ignored) {
        }
        System.out.println("Finish");
    }

    public String getName() {
        return name;
    }
}
