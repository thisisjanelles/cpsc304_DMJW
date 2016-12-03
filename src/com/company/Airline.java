package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Donna on 8/11/2016.
 */
public class Airline {
    private ConnectionManager connectionManager;
    private String code;
    private String name;
    private String flight_num;
    private Date dep_date;
    private Date arr_date;
    private String dep_time;
    private String arr_time;
    private String dep_airport;
    private String arr_airport;

    ArrayList<Flight> flights;

    public Airline() {
        connectionManager = new ConnectionManager();
    }

    public void airlineLogin(String aCode) throws Exception {
        String query = "SELECT * FROM Airline WHERE code = '" + aCode + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();

        if (rs.next()) {
            do {
                code = rs.getString("code");
                name = rs.getString("name");
            } while (rs.next());
        } else {
            throw new Exception();
        }
    }

    public ArrayList<String> getOperatedFlights(String aCode) throws Exception {
        String query = "SELECT * FROM OperateFlights WHERE code = '" + aCode + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();

        ArrayList<String> flightList = new ArrayList<String>();

        if (rs.next()) {
            do {
                flight_num = rs.getString("flight_num");
                dep_date = rs.getDate("dep_date");
                dep_time = rs.getString("dep_time");
                arr_date = rs.getDate("arr_date");
                arr_time = rs.getString("arr_time");
                dep_airport = rs.getString("dep_airport");
                arr_airport = rs.getString("arr_airport");

                flightList.add(flight_num + " – " + dep_date + " – " + dep_time + " – " + arr_date + " – " + arr_time + " – " + dep_airport + " - " + arr_airport);
            } while (rs.next());
        } else {
            throw new Exception();
        }

        return flightList;
    }

    public String getAirlineName(String acode) throws SQLException{
        String result = "";
        String query = "SELECT name from Airline where code = '" + acode + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();
        while(rs.next()) {
            result = rs.getString("name");
        }
        return result;
    }

    public JTable avgPriceforEachAirline(){
        String query = "Select name, avg(price) from airline a, hasAvailableseats h where a.code = h.code " +
                "AND h.code IN (Select code from book_ticket) group by name";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();
        DefaultTableModel dtm = new DefaultTableModel();
        Vector<String> columnNames = new Vector<String>();
        Vector data_rows = new Vector();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            dtm.setColumnIdentifiers(columnNames);
            // data of the table
            while(rs.next()){
                for (int j = 1; j<= metaData.getColumnCount(); j++){
                    data_rows.addElement(rs.getString(j));
                }
                dtm.addRow(data_rows);
                data_rows = new Vector();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JTable result = new JTable(){
            public boolean isCellEditable(int data, int columns){
                return false;
            }
        };
        result.setModel(dtm);
        return result;
    }

    public String getCode(){
        return code;
    }
    public String getAirlineName(){
        return name;
    }
}
