/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bankingapp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BankingApp {
    
  public static String acNum = "";

    public static void main(String args[]) throws ClassNotFoundException {
        JFrame f = new JFrame("Error");
        try {
            //loading Drivers
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to the database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://aws.connect.psdb.cloud/javaminorproject?sslMode=VERIFY_IDENTITY",
                    "e0tqxzvnmwob1z8zsp6q",
                    "pscale_pw_FL83lIXEwj7d3M26VQ04TLdQmaNx7MRu3OrLHEy04tz");
            // Create a statement to execute the query
            Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            new login(smt);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(f, "Network coonection Error");
            System.exit(0);
        }
    } 
}

class func {
    static String[] date() {
        String[] transaction = new String[2];
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter da = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter ti = DateTimeFormatter.ofPattern("HH:mm:ss");
            transaction[0] = currentDateTime.format(da);
            transaction[1] = currentDateTime.format(ti);
            return transaction;
        } catch (Exception w) {
            transaction[0] = "Not got date";
            return transaction;
        }
    }
}
