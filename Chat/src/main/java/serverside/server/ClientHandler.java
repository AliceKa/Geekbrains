package serverside.server;

import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Time;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

public class ClientHandler {

    private MyServer myServer;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private String name;
    private boolean isAuthorized;
    private boolean isActive;

    public ClientHandler(MyServer myServer, Socket socket) throws IOException {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread 1");
                    try {
                        System.out.println(Instant.now());
                        authentication();
                        readMessage();
                    } catch (IOException | InterruptedException ignored) {
                    } finally {
                        try {
                            closeConnection();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread 2");
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
                }
            });

            t1.start();
            t2.start();




        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problems with ClientHandler");
        }
    }


    public void authentication() throws IOException {
        int counter = 0;
        while (counter < 3) {
            String str = dis.readUTF();
            if (str.startsWith("/auth")) { //  /auth login password
                counter++;
                String [] arr = str.split("\\s");
                String nick = myServer
                        .getAuthService()
                        .getNickByLoginAndPassword(arr[1], arr[2]);
                if (nick != null) {
                    if (!myServer.isNicknameBusy(nick)) {
                        isAuthorized = true;
                        sendMessage("User " + nick + " successfully logged in");
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
        return;
    }

    public void readMessage() throws IOException, InterruptedException {
        if (isAuthorized) {
            while (true) {
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
                        char[] arr1 = messageFromClient.toCharArray();
                        String nameReceiver = new String(arr1, 3,5);
                        String messageToDirect = name + ": " + new String(arr1,9,arr1.length - 9);
                        if (myServer.isNicknameBusy(nameReceiver)) {
                            myServer.directedMessage(messageToDirect, nameReceiver);
                            String messageToDirect1 = "to " + nameReceiver + " : " + new String(arr1,9,arr1.length - 9);
                            myServer.directedMessage(messageToDirect1, name);
                        } else {
                            myServer.directedMessage("The user with the nick " + nameReceiver + " is not available", name);
                        }
                    }
                    if (messageFromClient.startsWith("/list")) {
                        myServer.broadcastClientsList(this);
                    }
                }
                else {
                    myServer.broadcastMessage(name + ": " + messageFromClient);
                }
                timerClose();
            }
        } else {
            return;
        }

    }

    public void timerClose() throws InterruptedException, IOException {
        System.out.println("Entered to timer");
        Thread.sleep(10000);
        if (!isActive) {
            System.out.println("End");
            closeConnection();
        }
    }
    public void sendMessage(String message) throws IOException {
        dos.writeUTF(message);
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
