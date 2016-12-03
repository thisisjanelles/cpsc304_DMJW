package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Minjia_Zhan on 11/21/2016.
 */
public class RegisterFrame extends JFrame{
    private JPanel panel = new JPanel();

    private GridBagConstraints c = new GridBagConstraints();

    //private JButton login;
    private JButton register;
    private JTextField emailT;
    private JTextField fnameT;
    private JTextField lnameT;
    private JTextField crT;
    private JTextField phT;
    private JPasswordField passwordField;

    public String cEmail;
    private String cfname;
    private String clname;
    private String crn;
    private String phn;
    private String password;
    public Customer customer = new Customer();;

    public RegisterFrame(){
        super("Registration form");
        setSize(400, 300);
        setupPanel();
        setContentPane(panel);
        setVisible(true);
        registerListener();
    }

    private void setupPanel(){
        JLabel emailL = new JLabel("Email");
        JLabel passwordL = new JLabel("Password");
        JLabel fnameL= new JLabel("First Name");
        JLabel lnameL= new JLabel("Last Name");
        JLabel crL= new JLabel("Credit Card Number");
        JLabel phL= new JLabel("Phone Number");

        emailT = new JTextField();
        fnameT = new JTextField();
        lnameT = new JTextField();
        crT = new JTextField();
        phT = new JTextField();
        passwordField = new JPasswordField();
        register = new JButton("Register Now");

        panel.setLayout(new GridLayout(7, 2, 5, 5));
        panel.add(emailL);
        panel.add(emailT);
        panel.add(passwordL);
        panel.add(passwordField);
        panel.add(fnameL);
        panel.add(fnameT);
        panel.add(lnameL);
        panel.add(lnameT);
        panel.add(crL);
        panel.add(crT);
        panel.add(phL);
        panel.add(phT);
        panel.add(new JSeparator());
        panel.add(register);
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

        fnameT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                cfname = fnameT.getText();
            }
        });

        lnameT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                clname = lnameT.getText();
            }
        });

        crT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                crn = crT.getText();
            }
        });

        phT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                phn = phT.getText();
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

    public void registerListener(){
        getTextFields();
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customer.customerRegister(cEmail,cfname,clname,crn,phn);
                    afterLogin();
                } catch (Exception c) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            }
        });
    }

    public void afterLogin() {
        new CustomerFrame(cEmail);
        dispose();
    }

    public Customer getCustomer(){
        return customer;
    }

    //Testing
    public static void main(String[] args) {
        new RegisterFrame();
    }


}
