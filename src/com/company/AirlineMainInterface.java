package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

/**
 * Created by janelle on 2016-11-19.
 */
public class AirlineMainInterface {
//    private String code;
//    private String flight_num;
//    private Date dep_date;
//    private Date arr_date;
//    private String dep_time;
//    private String arr_time;
//    private String dep_airport;
//    private String arr_airport;
    JFrame frame;

    private JPanel panel;

    private JLabel operatedFlightsLabel;
    private JLabel operatedFlightsList;

    private JLabel addFlightLabel;
    private JLabel addFlightNumLabel;
    private JLabel addDepDateLabel;
    private JLabel addDepTimeLabel;
    private JLabel addArrDateLabel;
    private JLabel addArrTimeLabel;
    private JLabel addDepAirportLabel;
    private JLabel addArrAirportLabel;
    private JLabel addNumEconSeatsLabel;
    private JTextField addFlightNumberTF;
    private JTextField addDepDateTF;
    private JTextField addDepTimeTF;
    private JTextField addArrDateTF;
    private JTextField addArrTimeTF;
    private JTextField addDepAirportTF;
    private JTextField addArrAirportTF;
    private JButton addFlightButton;
    private JTextField addNumEconSeatsTF;

    private String flightNum;
    private String flightDepDate;
    private String flightDepTime;
    private String flightArrDate;
    private String flightArrTime;
    private String flightDepAirport;
    private String flightArrAirport;
    private String flightEconSeats;
    private String flightBusSeats;
    private String flightFirstSeats;
    private String econSeatPrice;
    private String busSeatPrice;
    private String firstSeatPrice;

    private JLabel deleteFlightLabel;
    private JLabel deleteFlightNumLabel;
    private JTextField deleteFlightNumTF;
    private JButton deleteFlightButton;

    private JLabel passengerListLabel;
    private JLabel passengerFlightNumLabel;
    private String criteriaString;
    private JTextField passengerFlightNumTF;
    private JButton getPassengerListButton;
    private JCheckBox passengerNumberCheckBox;
    private JCheckBox genderCheckBox;
    private JCheckBox firstNameCheckBox;
    private JCheckBox lastNameCheckBox;
    private JCheckBox airlineCodeCheckBox;
    private JCheckBox flightNumberCheckBox;
    private JCheckBox confirmationNumberCheckBox;
    private JLabel passengerList;
    private JButton logOutButton;

    private JLabel addNumBusSeatsLabel;
    private JTextField addNumBusSeatsTF;
    private JLabel addNumFirstSeatsLabel;
    private JTextField addNumFirstSeatsTF;
    private JLabel seatPriceLabel;
    private JTextField flightEconPriceTF;
    private JTextField flightBusPriceTF;
    private JTextField flightFirstPriceTF;
    private JButton avgPriceAnalysisButton;

    private Airline airline;
    private String aCode;

    public AirlineMainInterface(String airlineCode){
        airline = new Airline();
        panel.setPreferredSize(new Dimension(700, 900));
        setFrame();
        this.aCode = airlineCode;
        printFlightsList();
        addFlightsListener();
        deleteFlightsListener();
        getPassengerListListener();
        analysisListener();
        logOutListener();
    }

    private void setFrame() {
        frame = new JFrame("Airline Interface");
        frame.setContentPane(panel);
        // close the application if you close the frame
        // do not include this if you X close the application when you close the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void printFlightsList() {
        try {
            String flightString = "";
            ArrayList<String> flightList = airline.getOperatedFlights(this.aCode);
            for (int i = 0; i < flightList.size(); i++) {
                flightString = flightString + flightList.get(i) + "<br>";
            }
            String finalString = "<html>" + flightString + "</html>";
            operatedFlightsList.setText(finalString);
        } catch (Exception e) {
            operatedFlightsList.setText("No operated flights found.");
        }
    }

    private void analysisListener() {
        avgPriceAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new JFrame("Average price for ticket booked");
                JPanel p = new JPanel();
                Airline a = new Airline();
                p.add(a.avgPriceforEachAirline());
                f.setContentPane(p);
                f.setVisible(true);
                f.pack();
            }
        });
    }

    private void logOutListener() {
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AirlineLogInFrame();
            }
        });
    }

    private void addFlightsListener() {
        getAddFlightTextFields();
        addFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Flight flightToAdd = new Flight();
                    AvailableSeats as = new AvailableSeats();
                    flightToAdd.addFlight(aCode, flightNum, flightDepDate, flightDepTime, flightArrDate, flightArrTime, flightDepAirport, flightArrAirport);

                    int econ = Integer.parseInt(flightEconSeats);
                    int bus = Integer.parseInt(flightBusSeats);
                    int first = Integer.parseInt(flightFirstSeats);
                    int total = econ + bus + first;
                    String totalNum = Integer.toString(total);

                    as.addEntry(aCode, flightNum, "Economy", flightEconSeats, econSeatPrice, totalNum);
                    as.addEntry(aCode, flightNum, "Business", flightBusSeats, busSeatPrice, totalNum);
                    as.addEntry(aCode, flightNum, "First", flightFirstSeats, firstSeatPrice, totalNum);
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, "Unable to add flight, check fields for errors.");
                }

                JOptionPane.showMessageDialog(null, "Flight successfully added.");
                printFlightsList();
            }

        });
    }

    private void deleteFlightsListener() {
        getDeleteFlightTextFields();
        deleteFlightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Flight flightToDelete = new Flight();
                flightToDelete.deleteFlight(flightNum);
                JOptionPane.showMessageDialog(null, "Flight deleted.");
                printFlightsList();
            }

        });
    }

    private void getPassengerListListener() {
        passengerFlightNumTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightNum = passengerFlightNumTF.getText();
            }
        });

        getPassengerListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Passenger passenger = new Passenger();

                criteriaString = "";

                if (passengerNumberCheckBox.isSelected()) {
                    criteriaString = criteriaString + "pass_num" + ",";
                }

                if (genderCheckBox.isSelected()) {
                    criteriaString = criteriaString + "gender" + ",";
                }

                if (firstNameCheckBox.isSelected()) {
                    criteriaString = criteriaString + "pfname" + ",";
                }

                if (lastNameCheckBox.isSelected()) {
                    criteriaString = criteriaString + "plname" + ",";
                }

                if (airlineCodeCheckBox.isSelected()) {
                    criteriaString = criteriaString + "code" + ",";
                }

                if (flightNumberCheckBox.isSelected()) {
                    criteriaString = criteriaString + "flight_num" + ",";
                }

                if (confirmationNumberCheckBox.isSelected()) {
                    criteriaString = criteriaString + "conf_num" + ",";
                }

                if (criteriaString.equals("")) {
                    criteriaString = "* ";
                }

                criteriaString = criteriaString.substring(0, criteriaString.length() - 1);

                try {
                    String passengerString = "";
                    ArrayList<String> passengers = passenger.printListOfPassenger(criteriaString, flightNum);
                    for (int i = 0; i < passengers.size(); i++) {
                        passengerString = passengerString + passengers.get(i) + "<br>";
                    }
                    String finalString = "<html>" + passengerString + "</html>";
                    passengerList.setText(finalString);
                } catch (Exception c) {
                    passengerList.setText("No passengers found.");
                }
            }

        });
    }

    private void getAddFlightTextFields() {
        addFlightNumberTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightNum = addFlightNumberTF.getText();
            }
        });

        addDepDateTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightDepDate = addDepDateTF.getText();
            }
        });

        addDepTimeTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightDepTime = addDepTimeTF.getText();
            }
        });

        addArrDateTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightArrDate = addArrDateTF.getText();
            }
        });

        addArrTimeTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightArrTime = addArrTimeTF.getText();
            }
        });

        addDepAirportTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightDepAirport = addDepAirportTF.getText();
            }
        });

        addArrAirportTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightArrAirport = addArrAirportTF.getText();
            }
        });

        addNumEconSeatsTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightEconSeats = addNumEconSeatsTF.getText();
            }
        });

        addNumBusSeatsTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightBusSeats = addNumBusSeatsTF.getText();
            }
        });

        addNumFirstSeatsTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightFirstSeats = addNumFirstSeatsTF.getText();
            }
        });

        flightEconPriceTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                econSeatPrice = flightEconPriceTF.getText();
            }
        });

        flightBusPriceTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                busSeatPrice = flightBusPriceTF.getText();
            }
        });

        flightFirstPriceTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                firstSeatPrice = flightFirstPriceTF.getText();
            }
        });
    }

    private void getDeleteFlightTextFields() {
        deleteFlightNumTF.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                flightNum = deleteFlightNumTF.getText();
            }
        });
    }
}