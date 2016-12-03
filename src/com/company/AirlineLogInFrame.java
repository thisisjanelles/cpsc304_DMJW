package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by janelle on 2016-11-16.
 */
public class AirlineLogInFrame extends JFrame {
    private JFrame frame;
    private JPanel panel = new JPanel();

    private GridBagConstraints c = new GridBagConstraints();

    private JButton login;
    private JTextField codeT;

    private String aCode;
    private Airline airline;

    public AirlineLogInFrame(){
        setupPanel();
        frame = new JFrame("Airline Login");
        frame.setSize(400, 100);
        // Want the frame to connect to the  -- put the class name
        frame.setContentPane(panel);
        // Close the application if you close the frame
        // Do not include this if you X close the application when you close the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Frame.pack();
        frame.setVisible(true);
        loginListener();
    }

    private void setupPanel(){
        JLabel codeL = new JLabel("Airline Code");
        codeT = new JTextField();
        login = new JButton("Login");

        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(codeL);
        panel.add(codeT);
        panel.add(new JSeparator());
        panel.add(login);
    }

    private void getTextFields(){
        codeT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                aCode = codeT.getText();
            }
        });
    }

    private void loginListener(){
        getTextFields();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Execute the query to retrieve all customer information
                airline = new Airline();
                try {
                    airline.airlineLogin(aCode);
                    frame.dispose();
                    new AirlineMainInterface(aCode);
                } catch (Exception c) {
                    JOptionPane.showMessageDialog(null, "Invalid airline code. Please try again");
                }
            }
        });
    }
}