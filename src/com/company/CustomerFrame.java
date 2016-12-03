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
 * Created by Donna on 15/11/2016.
 */
public class CustomerFrame extends JFrame{

    String cemail;
    String cname;
    ResultSet crs;

    private JButton tickets;
    private JButton edit;

    private JPanel panel = new JPanel();

    public CustomerFrame(String email){
        super("Welcome back to DMJW");
        cemail = email;
        getCustomerName();
        setupPanel();
        setContentPane(panel);
        actionListener();
        pack();
        setVisible(true);
    }

    private void setupPanel(){
        JPanel p = new JPanel();
        JLabel wel = new JLabel("Welcome Back,  " + cname + "!");
        wel.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        p.add(wel);
        panel.add(p);
        panel.add(new JSeparator());

        panel.add(new SearchPanel());
        panel.add(new JSeparator());
        tickets = new JButton("Tickets Booked");
        edit = new JButton("Edit Profile");
        panel.setLayout(new GridLayout(3, 2, 5, 5));
        panel.add(tickets);
        panel.add(edit);
    }

    private void getCustomerName(){
        Customer c = new Customer();
        crs = c.getCustomer(cemail);
        try {
            while (crs.next()) {
                cname = crs.getString("cfname");
            }
        }catch (SQLException e) {
                e.printStackTrace();
            }
    }

    private void actionListener(){

        tickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(cemail);
                new TicketHistoryFrame(cemail);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateProfilo(cemail);
            }
        });
    }

    private class SearchPanel extends JPanel {

        private JTextField deptAirportText;
        private JTextField arriAirport;
        private JTextField deptDateText;
        private String aAirport;
        private String deptAirport;
        private String deptDate;

        private JButton search;

        public SearchPanel(){
            super();
            setUpPanel();
            searchListener();
        }

        private void setUpPanel() {
            JLabel depDateLabel = new JLabel("Departure Date");
            JLabel arriAptLabel = new JLabel("Arrival Airport");
            JLabel deptAptLabel = new JLabel("Departure Airport");
            deptAirportText = new JTextField();
            arriAirport = new JTextField();
            deptDateText = new JTextField();
            search = new JButton("Search");
            setLayout(new GridLayout(4, 2, 10, 10));
            add(deptAptLabel);
            add(deptAirportText);
            add(arriAptLabel);
            add(arriAirport);
            add(depDateLabel);
            add(deptDateText);
            add(new JSeparator());
            add(search);
        }


        private void getSearchInf(){
            arriAirport.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                }

                @Override
                public void focusLost(FocusEvent e) {
                    aAirport = arriAirport.getText();
                }
            });

            deptDateText.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    deptDate = deptDateText.getText();
                }
            });

            deptAirportText.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                }

                @Override
                public void focusLost(FocusEvent e) {
                    deptAirport = deptAirportText.getText();
                }
            });
        }

        public void searchListener(){
            getSearchInf();
            search.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new SearchResultFrame(deptAirport, aAirport, deptDate, cemail );
                }
            });
        }
    }


    public static void main(String[] args) {
        new CustomerFrame("alicealex@gmail.com");
    }
}
