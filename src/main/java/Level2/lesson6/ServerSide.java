package Level2.lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is awake, waiting for the client...");
            socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
                            out.writeUTF(bf.readLine());
                        } catch (IOException ignored) {
                        }
                    }
                }
            });

            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                out.writeUTF("/end");
                                return;
                            }
                            out.writeUTF("Echo: " + str);
                        } catch (IOException ignored) {
                        }
                    }
                }
            });
            t1.setDaemon(true);
            t1.start();
            t2.start();

        } catch (IOException ignored) {
            System.out.println("Connection broken");;
        }
    }
}