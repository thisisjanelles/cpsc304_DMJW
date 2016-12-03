package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;

/**
 * Created by Minjia_Zhan on 11/21/2016.
 */
public class RecommendationFrame {
    private JFrame frame;
    private JPanel panel;
    private Recommendation rec;
    private String email;

    public RecommendationFrame(String email) {
        this.email = email;
        this.rec = new Recommendation();
    }

    public void setUpPage() throws SQLException {
        panel = new JPanel();
        frame = new JFrame("Our Recommendation for You");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(panel);

        DefaultTableModel tm = rec.generateRec(email);
        JTable table = new JTable(tm);
        panel.add(new JScrollPane(table));
        frame.getContentPane().add(panel);
    }
}
