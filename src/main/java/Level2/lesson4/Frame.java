package Level2.lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;

public class Frame implements ActionListener{

    JButton sendMessage;
    JTextField messageBox;
    JTextArea chatBox;
    Map<String, String> database = new HashMap<String, String>();


    Frame() {

        database.put("hello", "Oh, hello to you human");
        database.put("how are you", "I am good");
        database.put("what are you doing", "I am answering stupid question");
        database.put("what is your name", "My name is ChatBot, made by Alisa");
        database.put("bye", "Ok, byeee");

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

        messageBox.addActionListener(this);
        sendMessage.addActionListener(this);

        southPanel.add(messageBox, left);
        southPanel.add(sendMessage, right);

        mainPanel.add(BorderLayout.SOUTH, southPanel);

        newFrame.add(mainPanel);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setSize(500, 700);
        newFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (e.getActionCommand().equals("Send Message")) {
            System.out.println("Button clicked");
        }
        else {
            System.out.println("Enter clicked");
        }
        if (messageBox.getText().length() != 0) {
            chatBox.append(formatter.format(date) + " < YOU >: " + messageBox.getText() + "\n");
            responseRobot();
            messageBox.setText("");
            messageBox.requestFocusInWindow();
        }
        else {
            System.out.println("Null");
        }
    }

    public void responseRobot() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        for (String entry: database.keySet()) {
            if (messageBox.getText().toLowerCase().contains(entry)) {
                chatBox.append(formatter.format(date) + " < ROBOT >: " + database.get(entry) + "\n");
            }
        }

    }
}

