package com.company;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Created by wenwen on 2016-11-20.
 */
public class Login {

    private ConnectionManager connectionManager;
    ResultSet rs;

    JFrame f = new JFrame("User Login");
    JLabel l = new JLabel("User Name: ");
    JLabel l1 = new JLabel("Password: ");
    JTextField t = new JTextField(10);
    JTextField t1 = new JTextField(10);
    JButton b = new JButton("Login");


    public Login(){
        connectionManager = new ConnectionManager();
        frame();
    }

    public void frame(){

        f.setSize(600,400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        JPanel p = new JPanel();
        p.add(l);
        p.add(t);
        p.add(l1);
        p.add(t1);
        p.add(b);

        f.add(p);
        b.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                try{
                    String user = t.getText().trim();
                    String pass = t1.getText().trim();

                    String sql = "SELECT user, pass FROM Table2 WHERE user = '" + user+ "' and pass ="+pass+"'";
                    connectionManager.performQuery(sql);
                    rs = connectionManager.getResultSet();

                    int  count = 0;
                    while (rs.next()){
                        count  = count +1;
                    }

                    if(count==1){
                        JOptionPane.showMessageDialog(null, "User found in the database.");
                    }
                    else if (count>1){
                        JOptionPane.showMessageDialog(null, "Duplicate user, Access denied.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "User not found");
                    }
                }
                catch (Exception ex){

                }


            }
        });
    }

    public static void main(String[] args){

        new Login();

    }

}
