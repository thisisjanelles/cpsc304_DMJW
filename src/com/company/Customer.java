package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Donna on 15/11/2016.
 */
public class Customer {

    private ConnectionManager connectionManager;
    private String cEmail;
    private String cfname;
    private String clname;
    private String cred_num;
    private String phone_num;

    public Customer() {
        connectionManager = new ConnectionManager();
    }

    //public void customerLogin(String email, String password){
    public void customerLogin(String email) throws Exception {
        String query = "SELECT * FROM Customer WHERE email = '" + email + "'";
        connectionManager.performQuery(query);
        ResultSet rs = connectionManager.getResultSet();
        //String query = "SELECT * FROM Customer WHERE email = '" + email + "' AND password = '" + password + "'";
        try {
            if (!rs.next()){
                throw new Exception();
            }
            while (rs.next()) {
                cEmail = rs.getString("email");
                cfname = rs.getString("cfname");
                clname = rs.getString("clname");
                cred_num = rs.getString("cred_num");
                phone_num = rs.getString("phone_num");
                //Testing
                System.out.println("Customer Logged in = " + cEmail + cfname + clname + cred_num + phone_num);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCustomer(String cEmail){
        String query = "SELECT * FROM Customer where email = '" + cEmail + "'";
        connectionManager.performQuery(query);
        return connectionManager.getResultSet();
    }

    public String getCfname(){
        return cfname;
    }
    public String getClname(){
        return clname;
    }
    public String getPhone_num(){
        return phone_num;
    }
    public String getCred_num(){
        return cred_num;
    }
    public String getcEmail(){
        return cEmail;
    }

    public void customerRegister(String email,String cfname, String clname, String crnum, String phnum) throws SQLException {
        String query = "INSERT INTO customer (cfname, clname, cred_num, phone_num, email) VALUES ("+"'" +cfname + "'"+","+"'"+clname+"'"+","+"'"+crnum+"'"+","+"'"+phnum+"'"+","+"'"+email+ "')";
        connectionManager.performUpdate(query);
        System.out.println(query);
    }

    public void updateProfile(String email,String cfname, String clname, String crnum, String phnum) throws SQLException{
        String query = "UPDATE customer SET cfname = '" + cfname + "', clname = '" + clname +"', cred_num = '" + crnum + "', phone_num = '" + phnum + "' WHERE email = '"
                + email+ "'";
        connectionManager.performUpdate(query);
        System.out.println(query);
    }
}
