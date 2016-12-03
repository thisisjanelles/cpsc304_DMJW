package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

/**
 * Created by Minjia_Zhan on 11/20/2016.
 */
public class UpgradeClassPanel extends JPanel {
    private JTextField confN;
    private JComboBox classN;
    private AvailableSeats as;
    private String confNum;

    private String cclass;

    public UpgradeClassPanel(String confNum) throws SQLException {
        super();
        this.as = new AvailableSeats();
        this.confNum = confNum;
        setUpPanel();
        classListener();
    }

    public void setUpPanel() throws SQLException {
        //JLabel firstN = new JLabel("First Name");
        //JLabel lastN = new JLabel("Last Name");
        JLabel classT = new JLabel("Class Type");
        JLabel conf = new JLabel("Confirmation Number");
        confN = new JTextField();
        classN = new JComboBox();
//        DefaultComboBoxModel model = as.currentClass(confNum);
//        JComboBox box = new JComboBox(model);

        setLayout(new GridLayout(4, 2, 10, 10));
        //add(firstN);
        add(conf);
        add(confN);
        add(classT);
        add(classN);
//        add(box);
    }


    public String classListener(){
        classN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cclass = classN.getSelectedItem().toString();
                try {
                    as.upgradeSeats(confNum,cclass);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        return cclass;
    }
}
