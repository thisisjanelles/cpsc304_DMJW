package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Donna on 15/11/2016.
 */
public class CustomerLogInFrame extends JFrame{
    private JPanel panel = new JPanel();

    private GridBagConstraints c = new GridBagConstraints();

    private JButton login;
    private JButton register;
    private JTextField emailT;
    private JPasswordField passwordField;

    public String cEmail;
    private String password;
    public Customer customer = new Customer();;


    public CustomerLogInFrame(){
        super("Customer Login");
        setSize(400, 100);
        setupPanel();
        setContentPane(panel);
        setVisible(true);
        loginListener();
        registerListener();
    }

    private void registerListener() {
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterFrame();
                dispose();
            }
        });
    }

    private void setupPanel(){
        JLabel emailL = new JLabel("Email");
        JLabel passwordL = new JLabel("Password");
        emailT = new JTextField();
        passwordField = new JPasswordField();
        login = new JButton("Login");
        register = new JButton("Register Now");

        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(emailL);
        panel.add(emailT);
        panel.add(passwordL);
        panel.add(passwordField);
        panel.add(register);
        panel.add(login);
    }

    private void getTextFields(){
        emailT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                cEmail = emailT.getText();
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                password = passwordField.getPassword().toString();
                System.out.println(passwordField.getPassword().toString());
                System.out.println(password);

            }
        });
    }

    public void loginListener(){
        getTextFields();
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Execute the query to reterieve all customer information
                //customer.customerLogin(cEmail, password);
                try {
                    customer.customerLogin(cEmail);
                    afterLogin();
                } catch (Exception c) {
                    JOptionPane.showMessageDialog(null, "Either password or email does not match. Please try again");
                }
            }
        });
    }

    public void afterLogin() {
        new CustomerFrame(cEmail);
        dispose();
    }
}
