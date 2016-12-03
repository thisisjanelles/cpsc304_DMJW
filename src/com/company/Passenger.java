package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Donna on 8/11/2016.
 */
public class Passenger {
    ConnectionManager connectionManager;
    private String passNum;
    private String gender;
    private String firstname;
    private String lastname;
    private String code;
    private String flight_num;
    private String conf_num;

    public Passenger() {
        connectionManager = new ConnectionManager();
    }

    public void addPassenger(String passnum, String gender, String firstname, String lastname, String code, String flightnum) throws SQLException{
        String insertQuery = "INSERT INTO passenger (pass_num, gender, pfname, plname, code, flight_num) VALUES ('"
                + passnum + "','" + gender + "','"+ firstname + "', '" + lastname + "','" + code + "','" + flightnum + "')";
        connectionManager.performUpdate(insertQuery);
    }

    public static void main(String[] args) throws SQLException {
        Passenger p = new Passenger();
        p.deletePassenger("E00000001", "AC", "AC12");
        p.addPassenger("E00000001", "F", "Alice", "Alex", "AC", "AC12");
    }

    public void deletePassenger(String passnum, String code, String flightnum) throws SQLException{
        String deleteQuery = "DELETE FROM passenger WHERE pass_num = '" + passnum + "' AND flight_num = '" + flightnum + "' AND code = '" + code + "'";
        connectionManager.performUpdate(deleteQuery);
    }

    public boolean checkPassenger(String passnum) {
        String selectQuery = "SELECT " + "*" + " FROM passenger WHERE passnum = " + "'" + passnum + "'";
        boolean exists = connectionManager.checkExists(selectQuery);
        if (exists) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> printListOfPassenger(String criteria, String flightNum) throws Exception{
        String selectQuery = "SELECT " + criteria + " FROM passenger WHERE flight_num = '" + flightNum + "'";
        connectionManager.performQuery(selectQuery);
        ResultSet rs = connectionManager.getResultSet();

        ArrayList<String> passengerList = new ArrayList<String>();
        String passengerInfo = "";

        if (rs.next()) {
            do {
                if (criteria.contains("*")) {
                    passNum = rs.getString("pass_num");
                    gender = rs.getString("gender");
                    firstname = rs.getString("pfname");
                    lastname = rs.getString("plname");
                    code = rs.getString("code");
                    flight_num = rs.getString("flight_num");
                    conf_num = rs.getString("conf_num");

                    passengerInfo = passNum + " – " + gender + " – " + firstname + " – " + lastname + " – " + code + " – " + flight_num + " - " + conf_num + " - ";
                }

                if (criteria.contains("pass_num")) {
                    passNum = rs.getString("pass_num");
                    passengerInfo = passengerInfo + passNum + " - ";
                }

                if (criteria.contains("gender")) {
                    gender = rs.getString("gender");
                    passengerInfo = passengerInfo + gender + " - ";
                }

                if (criteria.contains("pfname")) {
                    firstname = rs.getString("pfname");
                    passengerInfo = passengerInfo + firstname + " - ";
                }

                if (criteria.contains("plname")) {
                    lastname = rs.getString("plname");
                    passengerInfo = passengerInfo + lastname + " - ";
                }

                if (criteria.contains("code")) {
                    code = rs.getString("code");
                    passengerInfo = passengerInfo + code + " - ";
                }

                if (criteria.contains("flight_num")) {
                    flight_num = rs.getString("flight_num");
                    passengerInfo = passengerInfo + flight_num + " - ";
                }

                if (criteria.contains("conf_num")) {
                    conf_num = rs.getString("conf_num");
                    passengerInfo = passengerInfo + conf_num + " - ";
                }

                passengerInfo = passengerInfo.substring(0, passengerInfo.length() - 3);

                passengerList.add(passengerInfo);
            } while (rs.next());
        } else {
            throw new Exception();
        }

        return passengerList;
    }
}