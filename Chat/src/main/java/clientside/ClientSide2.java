package clientside;

import GUIclasses.ChatBoxGui;
import GUIclasses.LoginFrame;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSide2 extends JFrame{
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8080;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private GUIclasses.LoginFrame LoginFrame;
    boolean isAuthorized = false;


    public ClientSide2() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            LoginFrame = new LoginFrame(in, out);
            isAuthorized = false;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String messageFromServer = in.readUTF();
                            if (messageFromServer.startsWith("User")) {
                                isAuthorized = true;
                                LoginFrame.frame.dispose();
                                ChatBoxGui frame = new ChatBoxGui(socket, in, out);
                                frame.chatBox.append(messageFromServer + "\n");
                                while (true) {
                                    String strFromServer = in.readUTF();
                                    frame.chatBox.append(strFromServer + "\n");
                                    if (strFromServer.equals("/end")) {
                                        break;
                                    }
                                }
                            }
                            if (messageFromServer.equals("/end")) {
                                LoginFrame.frame.dispose();
                                break;
                            }
                        }
                    } catch (IOException ignored) {
                    }
                }
            });

            t.setDaemon(true);
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public void onAuthClick() {
        try {
            out.writeUTF("/auth " + " " + loginField.getText() + " "+ passwordField.getText());
            loginField.setText("");
            passwordField.setText("");
        } catch (IOException ignored) {
        }
    }

     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientSide2();
            }
        });
    }

}
