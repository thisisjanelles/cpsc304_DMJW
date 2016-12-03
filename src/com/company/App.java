package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


/**
 * Created by Donna on 8/11/2016.
 * With AppForm
 */
public class App {

    JFrame frame;

    private JLabel depDateLabel;
    private JLabel arriAptLabel;
    private JLabel deptAptLabel;
    private JLabel cloginL;
    private JLabel airlineL;

    private JButton search;
    public  JPanel SearchPanel;
    private JTextField DeptAirportText;
    private JTextField deptDateText;

    private JTextField arriAirport;
    private JButton airlinelogin;
    private JButton cLoginB;

   // private JFormattedTextField formattedTextField1;

    String aAirport;
    String deptAirport;
    String deptDate;

    public App() {
        setFrame();
        searchFlightListener();
        cloginListener();
        airLoginListerner();
    }

    public void setFrame(){
        /*
        try {
            MaskFormatter format = new MaskFormatter("####-##-##");
            formattedTextField1 =new JFormattedTextField(format);
            //SearchPanel.add(formattedTextField1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
        frame = new JFrame("Welcome to DMJW");
        frame.setContentPane(SearchPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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

        DeptAirportText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                deptAirport = DeptAirportText.getText();
            }
        });
    }

    public void searchFlightListener(){
        getSearchInf();
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchResultFrame(deptAirport, aAirport, deptDate, null);
                frame.dispose();
            }
        });
    }

    public void cloginListener(){
        cLoginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerLogInFrame();
            }
        });
    }

    public void airLoginListerner(){
        airlinelogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AirlineLogInFrame();
            }
        });
    }

    // Testing
    public static void main(String[] args) {
        new App();
    }

}
