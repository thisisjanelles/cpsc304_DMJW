package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Minjia_Zhan on 11/13/2016.
 */
public class AvailableSeats {
    ConnectionManager connectionManager;
    private ResultSet rs;

    public AvailableSeats() {
        connectionManager = new ConnectionManager();
    }


    public boolean checkSeats(String code, String flightNum, String className) {
        String selectQuery = "SELECT total_num from HasAvailableSeats WHERE code = " + "'" + code + "AND flightNum = " + flightNum + "AND className = " + className + "'";
        boolean exists = connectionManager.checkExists(selectQuery);
        return true;
    }

    public void addEntry(String aCode, String flightNum, String className, String seatNum, String seatPrice, String totalNum) throws Exception {
        String query = "INSERT INTO customer VALUES ('" +aCode + "', '"+flightNum+"','"+className+"','"+seatNum+"','"+seatPrice+"," + totalNum + "')";
        connectionManager.performQuery(query);
    }

    public void getClassType(String flight_num, String code){
        String query="SELECT class_name from HasAvailableSeats WHERE flight_num = '"+ flight_num +"' AND code = '"
                + code + "'";
        System.out.println(query);
        connectionManager.performQuery(query);
        rs =  connectionManager.getResultSet();
    }

    public void getflight(String confNum) throws Exception{
        String query = "SELECT flight_num, code FROM Book_Ticket where conf_num = '" + confNum+"'";
        connectionManager.performQuery(query);
        ResultSet rs=connectionManager.getResultSet();
        System.out.println(query);
        if(!connectionManager.getResultSet().next()){
            System.out.println("exception in getflight");
            throw new Exception();
        }
        getClassType(rs.getString("flight_num"), rs.getString("code"));
    }

    public void upgradeSeats(String confNum, String classN) throws SQLException {
        String q = "UPDATE Book_ticket SET class_name = '" + classN + "' WHERE conf_num = '" + confNum + "'";
        connectionManager.performUpdate(q);
    }

    public void cancelSeats(String confN) throws SQLException {
        String selectQ = "SELECT flight_num, class_name,code from Book_Ticket WHERE conf_num =" + "'" + confN + "'";
        connectionManager.performQuery(selectQ);
        ResultSet rs = connectionManager.getResultSet();
        String fn = rs.getString("flight_num");
        String cn = rs.getString("class_name");
        String cd = rs.getString("code");
        try {
            String selectQuery = "SELECT total_num from HasAvailableSeats WHERE code = " + "'" + cd + "AND flight_num = " + fn + "AND class_name = " + cn + "'";
            connectionManager.performQuery(selectQuery);
            ResultSet rs2 = connectionManager.getResultSet();
            int tn = Integer.parseInt(rs.getString("total_num"));
            String updateQuery = "UPDATE HasAvailableSeats SET total_num =" + "'" + Integer.toString(tn + 1) + "'" + "," + "class_name =" + "'" + cn + "'" + "WHERE code = " + "'" + cd + "'" + "AND flight_num = " + "'" + fn + "'";
            connectionManager.performUpdate(updateQuery);
        } catch (SQLException e) {
        }
    }
    public ResultSet getResultSet(){
        return rs;
    }
}

