package com.company;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


/**
 * Created by Minjia_Zhan on 11/19/2016.
 */
public class TicketHistory {

    private ConnectionManager connectionManager;

    public TicketHistory() {
        connectionManager = new ConnectionManager();
    }

    public DefaultTableModel viewHistory(String email) throws SQLException {
        String Q = "SELECT * FROM Book_Ticket WHERE email = '" + email + "'";
        connectionManager.performQuery(Q);
        ResultSet rs = connectionManager.getResultSet();
        ResultSetMetaData metaData = rs.getMetaData();

        Vector<String> columnNames = new Vector<String>();
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        try {
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

