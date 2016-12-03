package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Minjia_Zhan on 11/20/2016.
 */
public class UpgradeClass extends JPanel {
    private JButton upgrade;
    private JComboBox classType;
    private JTextField confNumT;
    String confNum;
    String classes;

    public UpgradeClass(String confN){
        super();
        confNum = confN;
        setupPanel();
        upgradeListener();
    }

    private void setupPanel(){

        JLabel cn = new JLabel("Confirmation Number");
        JLabel ct=new JLabel("Class Type");
        classType = new JComboBox();
        classType.addItem("");
        confNumT = new JTextField();
        upgrade = new JButton("Upgrade Now");

        setLayout(new GridLayout(3, 2, 5, 5));
        add(cn);
        add(confNumT);
        add(ct);
        add(classType);

        add(new JSeparator());
        add(upgrade);
    }


    private void getFlightInf(){
        System.out.println("In get flight INf");
        confNumT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                confNum = confNumT.getText();
                AvailableSeats as = new AvailableSeats();
                try {
                    as.getflight(confNum);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                setClass(as.getResultSet());
            }

        });
    }

    private void setClass(ResultSet rs){
        try {
            while (rs.next()) {
                System.out.println("got result");
                classType.addItem(rs.getString("class_name"));
            }
        } catch (SQLException e) {
            System.out.println("Empty result?!");
            e.printStackTrace();
        }
    }

    private void upgradeListener(){
        getFlightInf();

        classType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classes = classType.getSelectedItem().toString();
            }
        });
        upgrade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AvailableSeats as = new AvailableSeats();
                try {
                    as.upgradeSeats(confNum, classes);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {
        new UpgradeClass("0000000001");
    }
}

