package com.company;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Donna on 8/11/2016.
 */
public class Flight {

    private ConnectionManager connectionManager;
    private ResultSet rs;

    public Flight(){
        connectionManager = new ConnectionManager();
    }

    public void searchFlights(String dep_airport, String arr_airport, String dep_Date) {
        String query = "SELECT * FROM availableflights where dep_airport = '" + dep_airport +
                "' AND arr_airport = '" + arr_airport + "' AND dep_Date = to_date('" + dep_Date + "','YYYY-MM-DD')";
        connectionManager.performQuery(query);
        rs = connectionManager.getResultSet();
        storeSearch(dep_airport, arr_airport,dep_Date);
    }

    public void storeSearch(String dep_airport, String arr_airport, String dep_Date){
        String q = "CREATE view searchResult as SELECT * FROM availableflights where dep_airport = '" + dep_airport +
                "' AND arr_airport = '" + arr_airport + "' AND dep_Date = to_date('" + dep_Date + "','YYYY-MM-DD')";
        connectionManager.performQuery(q);
    }

    public void targetFlight(String acode, String flight_num, String sclass){
        String query = "SELECT * FROM availableflights where flight_num = '" + flight_num +
                "' AND code = '" + acode + "' AND class_name = '" + sclass + "'";
        connectionManager.performQuery(query);
        rs = connectionManager.getResultSet();
    }

    public DefaultTableModel setTable(ResultSet rs){
        DefaultTableModel dtm = new DefaultTableModel();
        Vector data_rows = new Vector();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            dtm.setColumnIdentifiers(setColumnName());
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
        return dtm;
    }

    private Vector<String> setColumnName(){
        Vector<String> column = new Vector<String>();
        column.add("Airline");
        column.add("Flight Number");
        column.add("Departure Date");
        column.add("Departure Time");
        column.add("Arrival Date");
        column.add("Arrival Time");
        column.add("From");
        column.add("To");
        column.add("Class");
        column.add("Price");
        return column;
    }

    public void getSeatClass(String flightNum, String code){
        String query = "SELECT class_name from availableflights where flight_num = '" + flightNum
                + "' AND code = '" + code + "'";
        connectionManager.performQuery(query);
        rs = connectionManager.getResultSet();
    }

    public void addFlight(String code, String flight_num, String dep_date, String dep_time, String arr_date, String arr_time, String dep_airport, String arr_airport) throws Exception {
        String insertQuery = "INSERT INTO OperateFlights VALUES ('"
                + code + "', '" + flight_num + "', '" + dep_date + "', '" + dep_time + "', '" + arr_date + "', '" + arr_time + "', '" + dep_airport + "', '" + arr_airport + "')";
        connectionManager.performUpdate(insertQuery);
    }

    public void deleteFlight(String flight_num) {
        try {
            String deleteQuery = "DELETE FROM OperateFlights WHERE flight_num = '" + flight_num + "'";
            connectionManager.performUpdate(deleteQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortby(String dep_airport, String arr_airport, String dep_Date, String s){
        String q;
        if(s.equals("Lowest Price")){
            q = "SELECT * from searchResult where price IN  (select MIN(price) from searchResult)";
            /*
            q = "SELECT * from availableflights where price IN  (select MIN(price) from availableflights) AND "
            +  "dep_airport = '" + dep_airport +
            "' AND arr_airport = '" + arr_airport + "' AND dep_Date = to_date('" + dep_Date + "','YYYY-MM-DD')";
            */
        }
        else
            q = "SELECT * from searchResult where price IN  (select MAX (price) from searchResult)";
            /*q = "SELECT * from availableflights where price IN  (select MAX(price) from availableflights) AND "
                +  "dep_airport = '" + dep_airport +
                "' AND arr_airport = '" + arr_airport + "' AND dep_Date = to_date('" + dep_Date + "','YYYY-MM-DD')";
                */
        connectionManager.performQuery(q);
        rs = connectionManager.getResultSet();
    }

    public void dropSearchView(){
        String q = "Drop view searchResult";
        connectionManager.performQuery(q);
    }

    public ResultSet getResultSet(){
        return rs;
    }
}