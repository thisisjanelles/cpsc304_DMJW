package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
public class SearchResultFrame extends JFrame {
    private JPanel panel = new JPanel();
    private JTable result;
    private JButton book;
    private JComboBox sclassList;
    JComboBox s;
    String sort;

    private JTextField flightNumT;
    private JTextField codeT;

    String deptAirport;
    String aAirport;
    String deptDate;

    String cemail;
    String flight_Num;
    String airlineCode;
    String sclass;
    BookTicketFrame b;

    public SearchResultFrame(String deptAirport, String aAirport, String deptDate, String email){
        super("Search Result");
        cemail = email;
        this.deptAirport = deptAirport;
        this.aAirport = aAirport;
        this.deptDate = deptDate;
        setResultTable(deptAirport, aAirport, deptDate);
        setupPanel();
        setContentPane(panel);
        pack();
        setVisible(true);
        bookListener();
        sortListner();
    }

    private void setupPanel(){
        JLabel flightNum = new JLabel("Flight Number");
        JLabel Code = new JLabel("Airline Code");
        JLabel sclass = new JLabel("Seat Class");
        sclassList = new JComboBox();
        sclassList.addItem("");
        flightNumT = new JTextField();
        codeT = new JTextField();
        book = new JButton("Book Now");
        panel.add(new JScrollPane(result));
        //panel.add(new JSeparator());

        JPanel p1 = new JPanel();
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(4, 2, 5, 5));
        p.add(Code);
        p.add(codeT);
        p.add(flightNum);
        p.add(flightNumT);
        p.add(sclass);
        p.add(sclassList);
        p.add(new JSeparator());
        p.add(book);

        p1.add(new SortPanel());
        p1.add(p);
        panel.add(p1);
    }

    private void setResultTable(String deptAirport, String aAirport, String deptDate){
        Flight flight = new Flight();
        flight.searchFlights(deptAirport, aAirport, deptDate);
        DefaultTableModel dmt = flight.setTable(flight.getResultSet());
        result = new JTable(){
            public boolean isCellEditable(int data, int columns){
                return false;
            }
        };
        result.setModel(dmt);
    }

    private void getFlightInf(){
        System.out.println("In get flight INf");
        flightNumT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flight_Num = flightNumT.getText();
                if (flight_Num != null && airlineCode != null) {
                    System.out.println("not null");
                    Flight f = new Flight();
                    f.getSeatClass(flight_Num, airlineCode);
                    setSeatClass(f.getResultSet());
                }
            }
        });
        codeT.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                airlineCode = codeT.getText();
                if (flight_Num != null && airlineCode != null) {
                    System.out.println("not null");
                    Flight f = new Flight();
                    f.getSeatClass(flight_Num, airlineCode);
                    setSeatClass(f.getResultSet());
                }
            }
        });
    }

    private void setSeatClass(ResultSet rs){
        try {
            while (rs.next()) {
                sclassList.addItem(rs.getString("class_name"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Flight does not Exist");
        }
    }

    private void bookListener(){
        getFlightInf();

        sclassList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sclass = sclassList.getSelectedItem().toString();
            }
        });
        book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cemail == null){
                    CustomerLogInFrame cf = new CustomerLogInFrame() {
                        @Override
                        public void afterLogin() {
                            b = new BookTicketFrame(flight_Num, airlineCode, sclass, cEmail);
                            dispose();
                        }
                    };
                    Flight f = new Flight();
                    f.dropSearchView();
                    dispose();
                }
                else{
                    b = new BookTicketFrame(flight_Num, airlineCode, sclass, cemail);
                    Flight f = new Flight();
                    f.dropSearchView();
                    dispose();
                }
            }
        });
    }

    public void sortListner(){
        s.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sort = s.getSelectedItem().toString();
                Flight flight = new Flight();
                flight.sortby(deptAirport, aAirport, deptDate, sort);
                JFrame f = new JFrame(sort);
                JPanel p = new JPanel();
                DefaultTableModel dmt = flight.setTable(flight.getResultSet());
                result = new JTable(){
                    public boolean isCellEditable(int data, int columns){
                        return false;
                    }
                };
                result.setModel(dmt);
                p.add(result);
                f.add(p);
                f.setVisible(true);
                f.pack();

            }
        });

    }

    private class SortPanel extends JPanel{

        public SortPanel(){
            setUp();
        }

        private void setUp() {
            JLabel sort = new JLabel("Sort by :  ");
            s = new JComboBox();
            s.addItem("");
            s.addItem("Lowest Price");
            s.addItem("Highest Price");
            add(sort);
            add(s);
        }
    }
}
