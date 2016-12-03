package com.company;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Created by Minjia_Zhan on 11/21/2016.
 */
public class Recommendation {
    //private Customer customer= new Customer();
    //private Ticket ticket = new Ticket();
    private ConnectionManager connectionManager;

    public Recommendation() {
        connectionManager = new ConnectionManager();
    }


    public DefaultTableModel generateRec(String email) throws SQLException {
        String query = "SELECT flight_num FROM Book_Ticket WHERE email = '" + email + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();
        String fln = rs.getString("flight_num");

        String query1 = "SELECT dep_airport FROM OperateFlights WHERE flight_num ='" + fln + "'";
        connectionManager.performQuery(query1);
        ResultSet rs1 = connectionManager.getResultSet();
        String depa = rs1.getString("dep_airport");

        String query2 = "SELECT flight_num,dep_airport,arr_airport FROM OperateFlights WHERE dep_airport='" + depa + "'";
        connectionManager.performQuery(query2);
        ResultSet rs2 = connectionManager.getResultSet();
        ResultSetMetaData metaData = rs2.getMetaData();

        Vector<String> column = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        try {
            if (!rs2.next()) {
                System.out.println("No recommendation");
            }

            int columnCount = metaData.getColumnCount();
            //Vector<String> column = new Vector<String>();
            column.add("Flight Number");
            column.add("Departure Airport");
            column.add("Arrival Airport");

            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs2.getObject(i));
                }
                data.add(vector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new DefaultTableModel(data, column);
    }
}
