package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;

/**
 * Created by Minjia_Zhan on 11/19/2016.
 */
public class TicketHistoryFrame extends JFrame{
    private JPanel panel;
    private String email;

    public TicketHistoryFrame(String email) {
        super("Tickets Booked");
        this.email = email;
        setUpPage();
        setVisible(true);
        pack();
    }

    public void setUpPage() {
        panel = new JPanel();

        Ticket t = new Ticket();
        DefaultTableModel tm = t.viewHistory(email);
        JTable table = new JTable(tm);
        panel.add(new JScrollPane(table));

        JPanel p = new JPanel();
        // p.setLayout(new GridLayout(2, 1, 5, 5));
        p.add(new CancelTicketPanel());
        p.add(new JSeparator());
        p.add(new UpgradeClass(email));
        panel.add(p);
        add(panel);
    }

    private class CancelTicketPanel extends JPanel{

        private JButton cancelButton;
        private JTextField confNum;
        private String conf_num;
        private Ticket ticket;

        public CancelTicketPanel(){
            setupPanel();
            cancelListener();
        }

        private void setupPanel(){
            setLayout(new GridLayout(2, 2, 5, 5));
            JLabel cn = new JLabel("Confirmation Number");
            confNum = new JTextField();
            cancelButton = new JButton("Cancel Ticket");
            add(cn);
            add(confNum);
            add(new JSeparator());
            add(cancelButton);
        }

        private void getTextFields(){
            confNum.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    conf_num = confNum.getText().trim();
                }
            });
        }

        private void cancelListener() {
            getTextFields();
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ticket = new Ticket();
                    try {
                        ticket.cancelTicket(conf_num, email);
                        JOptionPane.showMessageDialog(null, "Ticket Canceled");
                        dispose();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
    }
}