package GUIclasses;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatBoxGui extends JFrame {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private JButton sendMessage;
    private JTextField messageBox;
    private JFrame newFrame;
    public JTextArea chatBox;

    public ChatBoxGui(Socket socket, DataInputStream in, DataOutputStream out) throws IOException {
        this.in = in;
        this.out = out;
        this.socket = socket;
        newFrame = new JFrame();
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

        messageBox.addActionListener(e->{
            sendMessage();
        });

        sendMessage.addActionListener(e->{
            sendMessage();
        });

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);


        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 700);
        newFrame.setVisible(true);
    }

    private void sendMessage() {
        if (messageBox.getText() != null && !messageBox.getText().trim().isEmpty()) {
            try {
                out.writeUTF(messageBox.getText());
                if (messageBox.getText().equals("/end")) {
                    in.close();
                    out.close();
                    socket.close();
                    newFrame.dispose();
                }
                messageBox.setText("");
                messageBox.grabFocus();
            } catch (IOException ignored) {
            }
        }
    }


}
