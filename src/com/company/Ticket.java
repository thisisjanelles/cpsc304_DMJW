package com.company;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

/**
 * Created by Minjia_Zhan on 11/8/2016.
 */
public class Ticket {

    ConnectionManager connectionManager;
    private final int min = 10000;
    private final int max = 99999;

    //Ticket details
    private String class_name;
    private String flight_num;
    private String code;
    private int total_num;

    public Ticket() {
        connectionManager = new ConnectionManager();
    }


    public void bookTicket(String confNum, String email, String flightNum, String code, String class_name) throws SQLException {
        String insertQuery = "INSERT INTO book_ticket (email, conf_num, flight_num, class_name, code) " +
                "VALUES ('" + email + "', '" + confNum + "', '" + flightNum + "', '" + class_name + "','" + code + "')";
        connectionManager.performUpdate(insertQuery);
    }

    public void cancelTicket(String confNum, String email) throws SQLException{
        String query = "DELETE FROM book_ticket WHERE conf_Num = '" + confNum + "' AND email = '" + email + "'";
        connectionManager.performUpdate(query);
    }

    public boolean checkTicket(String confNum, String email) {
        String selectQuery = "SELECT " + "*" + " FROM book_ticket WHERE confNum = '" + confNum + "' AND email = '" + email + "'";
        return connectionManager.checkExists(selectQuery);
    }

    private void getAvailableSeat(String confNum, String email){
        String query = "SELECT * FROM book_ticket WHERE conf_num = '" +confNum + "' AND email = '" + email + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();
        try {
            while(rs.next()){
                flight_num = rs.getString("flight_num");
                class_name = rs.getString("class_name");
                code = rs.getString("code");
                total_num = rs.getInt("total_num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateAvailableSeat(String confNum, String email) throws SQLException{
        getAvailableSeat(confNum, email);
        String query = "UPDATE HasAvailableSeats SET total_num = '" + (total_num - 1) + "WHERE flight_num = '" + flight_num +
                "' AND class_name = '" + class_name + "' AND code = '" + code;
        // Integer.toString(total_num - 1)
        connectionManager.performUpdate(query);
    }

    public String conformNum(){
        Random r = new Random();
        int conf = r.nextInt((max - min) + 1) + min;
        return Integer.toString(conf);
    }

    public DefaultTableModel viewHistory(String email) {
        String Q = "SELECT * FROM Book_Ticket WHERE email = '" + email + "'";
        connectionManager.performQuery(Q);
        ResultSet rs = connectionManager.getResultSet();
        Vector<String> columnNames = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            // data of the table
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, columnNames);
    }
}