package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by Donna on 20/11/2016.
 */
public class SearchPanel extends JPanel {

    private JTextField deptAirportText;
    private JTextField arriAirport;
    private JTextField deptDateText;
    //private JFormattedTextField deptDateText;
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
               // new SearchResultFrame(deptAirport, aAirport, deptDate, );
            }
        });
    }
}
