package com.company;

import java.sql.*;

/**
 * Created by janelle on 2016-11-09.
 */
public class ConnectionManager {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public ConnectionManager() {
        try {
            // Register JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

            // Make connection
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_z9g9", "a44583136");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void performQuery(String queryString){ //throws SQLException{
       try {
            // Query
            statement = connection.createStatement();
            // Execute query
            resultSet = statement.executeQuery(queryString);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    public void performUpdate(String updateString) throws SQLException{
            statement = connection.createStatement();
            // Execute update
            statement.executeUpdate(updateString);
    }

    public boolean checkExists(String queryString) {
        try {
            // Query
            System.out.println("Creating statement... CheckExists");
            statement = connection.createStatement();

            // Execute query
            ResultSet resultSet;
            resultSet = statement.executeQuery(queryString);

            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
