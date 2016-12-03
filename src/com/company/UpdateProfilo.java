package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by Donna on 21/11/2016.
 */
public class UpdateProfilo extends JFrame {

    String cemail;
    private JPanel main = new JPanel();

    private JTextField emailT;
    private JTextField fnameT;
    private JTextField lnameT;
    private JTextField credT;
    private JTextField phoneT;
    private JButton update;

    private String cfname;
    private String clname;
    private String crn;
    private String phn;

    public UpdateProfilo(String email){
        super();
        cemail = email;
        setUp();
        setContentPane(main);
        setSize(300, 300);
        setVisible(true);
        UpdateListener();
    }

    private void setUp(){
        setTextFields();
        JLabel emailL = new JLabel("Email");
        JLabel fnameL= new JLabel("First Name");
        JLabel lnameL= new JLabel("Last Name");
        JLabel crL= new JLabel("Credit Card Number");
        JLabel phL= new JLabel("Phone Number");
        update = new JButton("Update");
        main.setLayout(new GridLayout(7, 2, 5, 5));
        main.add(emailL);
        main.add(emailT);
        main.add(fnameL);
        main.add(fnameT);
        main.add(lnameL);
        main.add(lnameT);
        main.add(crL);
        main.add(credT);
        main.add(phL);
        main.add(phoneT);
        main.add(new JSeparator());
        main.add(update);
    }

    private void setTextFields(){
        Customer c = new Customer();
        ResultSet rs = c.getCustomer(cemail);
        try {
            while (rs.next()) {
                cemail = rs.getString("email");
                cfname = rs.getString("cfname");
                clname = rs.getString("clname");
                crn = rs.getString("cred_num");
                phn = rs.getString("phone_num");

                emailT = new JTextField(rs.getString("email"));
                fnameT = new JTextField(rs.getString("cfname"));
                lnameT = new JTextField(rs.getString("clname"));
                credT = new JTextField(rs.getString("cred_num"));
                phoneT = new JTextField(rs.getString("phone_num"));
            }
        } catch (SQLException e) {
            System.out.println("Exception caught in UpdatePorfile");
            e.printStackTrace();
        }
    }

    private void getTextFields(){

        emailT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                cemail = emailT.getText();
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

        credT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                crn = credT.getText();
            }
        });

        phoneT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                phn = phoneT.getText();
            }
        });

    }

    private void UpdateListener() {
        getTextFields();
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer();
                try{
                    c.updateProfile(cemail, cfname, clname, crn, phn);
                }catch (SQLException q){
                    q.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Invalid Input");
                }
            }
        });
    }

}
