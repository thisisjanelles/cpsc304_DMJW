package com.company;

import java.sql.*;

public class Main {

    // Before running, SSH into school network:
    // ssh -l username -L localhost:1522:dbhost.ugrad.cs.ubc.ca:1522 remote.ugrad.cs.ubc.ca

    public static void main(String[] args) {
        try {
            // Register JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Make connection
            System.out.println("Connecting...");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_z9g9", "a44583136");
            System.out.println("Connection successful");

            // Query
            System.out.println("Creating statement...");
            Statement statement = connection.createStatement();

            ResultSet resultSet;
//            resultSet = statement.executeQuery("SELECT flight_num FROM hasavailableseats WHERE price = 2000");
            resultSet = statement.executeQuery("select * from customer where cfname = 'Bob'");

            // Get data from result set
//            while (resultSet.next()) {
//                String flightNumber = resultSet.getString("flight_num");
//                System.out.println(flightNumber);
//            }

            int count = 1;
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
//
            while (resultSet.next()) {
                while (count < columnsNumber) {
                    String col = resultSet.getString(count);
                    System.out.println(col);
                    count++;
                }
//                count++;
//                String row = resultSet.getString(count);
//                System.out.println(row);
            }
//
//            Customer testCust = new Customer("Donna", "Minjia", "12345678", "1234567891", "donna@gmail.com");
//            testCust.insertCustomer();
//
//            Member testMember = new Member(testCust.getFirstName(), testCust.getLastName(),
//                    testCust.getCreditCardNumber(), testCust.getPhoneNumber(), testCust.getEmail());
//            testMember.getPoints();
//            testMember.insertCustomer();
//            while (resultSet.next()) {
//                Customer testCustomer = new Customer();
//                System.out.println(testCustomer.getInstance(resultSet).getFirstName());
//                System.out.println(testCustomer.getInstance(resultSet).getLastName());
//                System.out.println(testCustomer.getInstance(resultSet).getCreditCardNumber());
//                System.out.println(testCustomer.getInstance(resultSet).getPhoneNumber());
//                System.out.println(testCustomer.getInstance(resultSet).getEmail());
//            }

            System.out.println("Query complete");

            connection.close();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
