package Level2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSide extends JFrame implements ActionListener{
    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8080;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private JButton sendMessage;
    private JTextField messageBox;
    private JTextArea chatBox;

    public ClientSide() {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            closeConnection();
                            break;
                        }
                        chatBox.append(strFromServer + "\n");
                    }
                } catch (IOException ignored) {
                }
            }

            private void dispose() {
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {
        if (messageBox.getText() != null && !messageBox.getText().trim().isEmpty()) {
            try {
                out.writeUTF(messageBox.getText());
                chatBox.append(messageBox.getText() + "\n");
                messageBox.setText("");
                messageBox.grabFocus();
            } catch (IOException ignored) {
            }
        }
    }

    public void prepareGUI() {


        JFrame newFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        JPanel southPanel = new JPanel();
        chatBox = new JTextArea();
        sendMessage = new JButton("Send Message");
        messageBox = new JTextField(30);


        mainPanel.setLayout(new BorderLayout());

        southPanel.setBackground(Color.lightGray);
        southPanel.setLayout(new GridBagLayout());

        chatBox.setEditable(false);
        chatBox.setFont(new Font("Serif", Font.PLAIN, 15));
        chatBox.setLineWrap(true);
        mainPanel.add(new JScrollPane(chatBox), BorderLayout.CENTER);

        GridBagConstraints left = new GridBagConstraints();
        left.anchor = GridBagConstraints.LINE_START;
        left.fill = GridBagConstraints.HORIZONTAL;
        left.weightx = 512.0D;
        left.weighty = 1.0D;
        GridBagConstraints right = new GridBagConstraints();
        right.insets = new Insets(0, 10, 0, 0);
        right.anchor = GridBagConstraints.LINE_END;
        right.fill = GridBagConstraints.NONE;
        right.weightx = 1.0D;
        right.weighty = 1.0D;

        messageBox.addActionListener( this);
        sendMessage.addActionListener(this);

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 700);
        newFrame.setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    out.writeUTF("/end");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        sendMessage();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientSide();
            }
        });
    }

}
