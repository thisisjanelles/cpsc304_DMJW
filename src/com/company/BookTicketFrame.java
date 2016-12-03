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
 * Created by Donna on 17/11/2016.
 */
public class BookTicketFrame extends JFrame {

    //Ticket inf
    String cemail;
    String flight_Num;
    String airlineCode;
    String confNum;
    String sclass;
    //Passenger inf
    String cgender;
    String pfirstName;
    String pLastName;
    String passNum;

    JPanel content;
    JTable flight_inf;

    public BookTicketFrame(String flightNum, String aCode, String classN, String email){
        super("Booking Flight");
        cemail = email;
        flight_Num = flightNum;
        airlineCode = aCode;
        sclass = classN;
        setupPanel();
        setContentPane(content);
        pack();
        setVisible(true);
    }

    private void setupPanel(){
        flight_infPanel();
        content.add(new BookTicketPanel());

    }

    private void flight_infPanel(){
        Flight f = new Flight();
        f.targetFlight(airlineCode, flight_Num, sclass);
        content = new JPanel();
        content.add(new FlightInfPanel(f.getResultSet())) ;

        /*
        DefaultTableModel d =  f.setTable(f.getResultSet());
        flight_inf = new JTable(d);
        content = new JPanel();
        content.add(new JScrollPane(flight_inf));
        */
    }

    private void setFlightInf(ResultSet rs){
        try {
            while (rs.next()){

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class FlightInfPanel extends JPanel{
        JLabel Airline;
        JLabel FlightNum;
        JLabel dep_d;
        JLabel dep_t;
        JLabel arr_d;
        JLabel arr_t;
        // JLabel

        public FlightInfPanel(ResultSet rs){
            //JLabel airline = new JLabel("Airline");
            try {
                while(rs.next()){
                    Airline a = new Airline();
                    String an = a.getAirlineName(rs.getString("code"));
                    JLabel airline = new JLabel("Airline :" + an);
                    JLabel fligh_num = new JLabel("Flight Number :  " + rs.getString("flight_num"));
                    JLabel dep_d = new JLabel("Departure Date : " + rs.getString("dep_date"));
                    JLabel dep_t = new JLabel("Departure Time :  " + rs.getString("dep_time"));
                    JLabel arr_d = new JLabel("Arrival Date :  " + rs.getString("arr_date"));
                    JLabel arr_t = new JLabel("Arrival Time :  " + rs.getString("arr_time"));
                    JLabel fm = new JLabel("From :  " + rs.getString("dep_airport"));
                    JLabel to = new JLabel("To :  " + rs.getString("arr_airport"));
                    JLabel classl = new JLabel("Class :  " + sclass);
                    JLabel p = new JLabel("Price :  " + rs.getString("price"));

                    setLayout(new GridLayout(6, 2, 10, 10));
                    add(airline);
                    add(fligh_num);
                    add(dep_d);
                    add(dep_t);
                    add(arr_d);
                    add(arr_t);
                    add(fm);
                    add(to);
                    add(classl);
                    add(p);
                }
                //  DefaultTableModel d =  f.setTable(f.getResultSet());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private class BookTicketPanel extends JPanel{
        JTextField firstNT;
        JTextField lastNT;
        JTextField passNT;
        JComboBox genderC;
        JButton book;

        public BookTicketPanel(){
            super();
            setUpPanel();
            bookListener();
        }

        public void setUpPanel(){
            JLabel email = new JLabel("Email");
            JTextArea emailT = new JTextArea(cemail);

            JLabel firstN = new JLabel("First Name");
            JLabel lastN = new JLabel("Last Name");
            JLabel gender = new JLabel("Gender");
            JLabel passN = new JLabel("Passport Number");
            passNT = new JTextField();
            firstNT = new JTextField();
            lastNT = new JTextField();
            genderC = new JComboBox();
            genderC.addItem("");
            genderC.addItem("F");
            genderC.addItem("M");
            book = new JButton("Continue Booking");


            setLayout(new GridLayout(5, 2, 10, 10));
            add(firstN);
            add(firstNT);
            add(lastN);
            add(lastNT);
            add(passN);
            add(passNT);
            add(gender);
            add(genderC);
            add(new JSeparator());
            add(book);
        }

        private void getPassengerinf(){
            firstNT.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }
                @Override
                public void focusLost(FocusEvent e) {
                    pfirstName = firstNT.getText().trim();
                }
            });

            lastNT.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }
                @Override
                public void focusLost(FocusEvent e) {
                    pLastName =lastNT.getText().trim();
                }
            });

            passNT.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    passNum = passNT.getText().trim();
                }
            });
            genderC.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cgender = genderC.getSelectedItem().toString();
                }
            });
        }

        private void bookListener(){
            getPassengerinf();
            book.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Ticket t = new Ticket();
                        confNum = t.conformNum();
                        t.bookTicket(confNum, cemail, flight_Num, airlineCode, sclass);
                        Passenger p = new Passenger();
                        p.addPassenger(passNum, cgender, pfirstName, pLastName, airlineCode, flight_Num);
                        JOptionPane.showMessageDialog(null, "Thank you! Your Confirmation Number : " + confNum);
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, "Invalid Input");
                    }

                    //email, flight_inf,
                    //A FRAME TO LIST OUT ALL THE INF
                    //add a new ticket to the database and
                    //produce a message dialog -- with confirmation number
                    //gotta make sure customer login can track this ticket inf

                }
            });
        }
    }

    public static void main(String[] args) {
        new BookTicketFrame("AC23", "AC", "Business", "alicealex@gmail.com");
    }
}
