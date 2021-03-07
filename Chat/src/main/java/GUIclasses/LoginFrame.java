package GUIclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LoginFrame extends JFrame implements ActionListener {

    private JLabel l1, l2, l3;
    public Frame frame;
    protected JTextField tf1;
    protected JButton btn1;
    protected JPasswordField p1;
    protected DataInputStream in;
    protected DataOutputStream out;

    public LoginFrame(DataInputStream in, DataOutputStream out) throws IOException {
        this.in = in;
        this.out = out;
        frame = new JFrame("Login Form");
        l1 = new JLabel("Login Form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));

        l2 = new JLabel("Username");
        l3 = new JLabel("Password");
        tf1 = new JTextField();
        p1 = new JPasswordField();
        btn1 = new JButton("Login");

        btn1.addActionListener(this);

        l1.setBounds(190, 30, 400, 30);
        l2.setBounds(80, 70, 200, 30);
        l3.setBounds(80, 110, 200, 30);
        tf1.setBounds(160, 70, 200, 30);
        p1.setBounds(160, 110, 200, 30);
        btn1.setBounds(190, 160, 100, 30);

        frame.add(l1);
        frame.add(l2);
        frame.add(tf1);
        frame.add(l3);
        frame.add(p1);
        frame.add(btn1);

        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    /*
    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = tf1.getText();
        System.out.println(uname);
        String pass =  new String(p1.getPassword());
        System.out.println(pass);
        if(uname.equals("Alisa") && pass.equals("123"))
        {
            frame.dispose();
            Welcome wel = new Welcome();
            wel.setVisible(true);
            JLabel label = new JLabel("Welcome:"+uname);
            wel.getContentPane().add(label);
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Incorrect login or password",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

     */

    @Override
    public void actionPerformed(ActionEvent e) {
        String uname = tf1.getText();
        String pass =  new String(p1.getPassword());
        String messageToServer = "/auth " + uname + " " + pass;
        try {
            out.writeUTF(messageToServer);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
