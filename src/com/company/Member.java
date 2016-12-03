package com.company;

/**
 * Created by janelle on 2016-11-08.
 */
public class Member extends Customer {
    private ConnectionManager connectionManager;
    private String email;
    private int points = 0;

    public Member() {
        connectionManager = new ConnectionManager();
    }

    // THIS IS JUST FOR TESTING, WON'T BE CALLED FROM APP
    public static void main(String[] args) {
        Member testMember = new Member();
        testMember.addPoints(10);
        testMember.insertNewMember("donna@gmail.com");
    }

    /**
     * Add a new member
     * @param email --> checks if the given email already exists in the Customer table
     */
    private void insertNewMember(String email) {
        try {
            this.customerLogin(email);
            String insertQuery = "INSERT INTO member VALUES ('"
                    + email + "'," + this.points + ")";
            connectionManager.performUpdate(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (exists) {
//            String insertQuery = "INSERT INTO member VALUES ('"
//                    + email + "'," + this.points + ")";
//            System.out.println(insertQuery);
//            connectionManager.performUpdate(insertQuery);
//        }
    }

    private void addPoints(int points) {
        this.points = points;
    }
//    private String email;
//    private int points;
//
//    public Member(String email, int points) {
////        super(firstName, lastName, credNum, phoneNum, email);
//        this.email = getEmail();
//        this.points = 0;
//    }
//    public int getPoints() {
//        System.out.println(this.points);
//        return this.points;
//    }
//
//    public void insertCustomer() {
//        String insertQuery = "INSERT INTO member VALUES ("
//                + this.getEmail() + "','" + this.points + "')";
//        System.out.println(insertQuery);
////        oraManager.execute(insertQuery);
//        // oraManager.disconnect();
//    }
}
