package serverside.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHandler {

    public static final Logger logger = LogManager.getLogger(ClientHandler.class);
    private final MyServer myServer;
    private final Socket socket;
    private final DataInputStream dis;
    private final DataOutputStream dos;

    private String name;
    private boolean isAuthorized;
    private boolean isActive;
    private boolean timeout;

    private String fileName;

    public ClientHandler(MyServer myServer, Socket socket) throws IOException {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        authentication();
                        readMessage();
                    } catch (IOException | InterruptedException | SQLException | ClassNotFoundException ignored) {
                    } finally {
                        try {
                            //System.out.println("End Thread 1");
                            logger.info("[Connection] Socket shut down!");
                            if (!socket.isClosed()) closeConnection();
                            isAuthorized = false;
                            executorService.shutdownNow();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });


            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(20000);
                    } catch (InterruptedException ignored) {
                    }

                    if (!isAuthorized) {
                        try {
                            sendMessage("/end");
                        } catch (IOException ignored) {
                        }
                        //System.out.println("End Thread 2");
                        logger.info("[Connection] Authorization Time out!");
                        timeout = true;
                    }
                }
            });


            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    t1: while (true) {
                        Instant start = Instant.now();
                        while (!isActive && isAuthorized) {
                            Instant end = Instant.now();
                            long dif = Duration.between(start, end).toMinutes();
                            if (dif == 3) {
                                try {
                                    logger.info("[Connection] Activity Time out!");
                                    sendMessage("/end");
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                break t1;
                            }
                        }
                        if (timeout) {
                            try {
                                if (!socket.isClosed()) closeConnection();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break t1;
                        }
                    }
                    //System.out.println("End Thread 3");
                }
            });

        } catch (IOException e) {
            closeConnection();
            throw new RuntimeException("Problems with ClientHandler");
        }
    }


    public void authentication() throws IOException, SQLException, ClassNotFoundException {
        int counter = 0;
        while (counter < 3) {
            //System.out.println("Try to authorize number " + counter);
            String str = dis.readUTF();
            if (str.startsWith("/auth")) { //  /auth login password
                counter++;
                String [] arr = str.split("\\s");
                try {
                    String nick = myServer
                            .getAuthService()
                            .getNickByLoginAndPassword(arr[1], arr[2]);
                    fileName = myServer
                            .getAuthService()
                            .getFileNameByLoginAndPassword(arr[1], arr[2]);
                    if (nick != null) {
                        if (!myServer.isNicknameBusy(nick)) {
                            isAuthorized = true;
                            logger.info("[Connection] User " + nick + " successfully logged in");
                            sendMessage("User (" + nick + ") successfully logged in");
                            showFromFile();
                            name = nick;
                            myServer.broadcastMessage(name + " joined the chat");
                            myServer.subscribe(this);
                            return;
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            } else {
                sendMessage("Your command needs start with /auth");
            }
        }
        sendMessage("/end");
    }

    public void readMessage() throws IOException, InterruptedException {
        while (isAuthorized) {
            isActive = false;
            String messageFromClient = dis.readUTF();
            //System.out.println(name + " sent message " + messageFromClient);
            logger.info("[Connected] " + name + " sent message " + messageFromClient);
            isActive = true;
            if (messageFromClient.startsWith("/")) {
                if (messageFromClient.equals("/end")) {
                    logger.info("[Connected] " + name + " disrupts the connection");
                    sendMessage("/end");
                    return;
                }
                if (messageFromClient.startsWith("/w")) {
                    String[] arr1 = messageFromClient.split(" ", 3);
                    String nameReceiver = arr1[1];
                    String messageToDirect = name + ": " + arr1[2];
                    logger.info("[Connected] " + name + "tries to send a message to " + nameReceiver);
                    if (myServer.isNicknameBusy(nameReceiver)) {
                        myServer.directedMessage(messageToDirect, nameReceiver);
                        String messageToDirect1 = "to " + nameReceiver + " : " + arr1[2];
                        myServer.directedMessage(messageToDirect1, name);
                    } else {
                        myServer.directedMessage("The user with the nick " + nameReceiver + " is not available", name);
                    }
                }
                if (messageFromClient.startsWith("/list")) {
                    logger.info("[Connected] " + name + " requests a list of users connected");
                    myServer.broadcastClientsList(this);
                }

                if (messageFromClient.startsWith("/changeNick")) {
                    char[] arr1 = messageFromClient.toCharArray();
                    String newNick = new String(arr1, 12,arr1.length-12);
                    logger.info("[Connected] " + name + " changes the nick to " + newNick);
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

    public void sendMessage(String message) throws IOException, SocketException {
        dos.writeUTF(message);
        if (fileName != null) {
            saveInFile(fileName, message);
            myServer.saveInFile100(message);
        }
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
            //System.out.println("Closing socket!");
            dis.close();
            dos.close();
            socket.close();
            myServer.descreaseCount();
            //System.out.println("Number of clients connected to server is " + myServer.getCount());
        } catch (IOException ignored) {
        }
    }

    public String getName() {
        return name;
    }
}
