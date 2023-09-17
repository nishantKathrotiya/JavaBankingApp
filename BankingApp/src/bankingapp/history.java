/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapp;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.io.File;
import java.io.FileWriter;

class history extends JFrame {

    history(Statement smt) {
        JFrame f = new JFrame("Transection");
        
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device;
        device = graphics.getDefaultScreenDevice();
        
        int rows = 0, count = 0;
        String[] column = {"Account number", "Amount", "Transection_type", "Date", "Time"};
        JLabel done = new JLabel("File Created Succefully...");
        
        done.setBounds(50,650,150,30);
        done.setVisible(false);
        ResultSet rs1 = null;

        try {
            rs1 = smt.executeQuery("select * from phistory where account_number = " + BankingApp.acNum + ";");
            rs1.last();
            rows = rs1.getRow();
            rs1.beforeFirst();
        } catch (Exception w) {

        }

        String[][] data = new String[rows][5];
        try {
            while (rs1.next()) {
                data[count][0] = rs1.getString("account_number");
                data[count][1] = String.valueOf(rs1.getInt("amount"));
                data[count][2] = rs1.getString("transaction_type");
                data[count][3] = String.valueOf(rs1.getDate("t_date"));
                data[count][4] = rs1.getString("t_time");
                count++;
            }
        } catch (Exception w) {

        }
        JTable jt = new JTable(data, column);
         
       JButton buttoption = new JButton(new AbstractAction("Go To Option") {
            public void actionPerformed(ActionEvent e) {
                new option(smt);
                f.setVisible(false);
            }
        });
        
       
        JButton buttprint = new JButton(new AbstractAction("Save to File") {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter tx = new FileWriter("Statement of " + BankingApp.acNum + ".txt");
                    tx.write(column[0] + "\t" + "\t");
                    tx.write(column[1]  + "\t");
                    tx.write(column[2] + "\t");
                    tx.write(column[3] + "\t\t");
                    tx.write(column[4] + "\t");
                    tx.write("\n");
                    
                    for (int i = 0; i < data.length; i++) {
                        tx.write(data[i][0] + "\t");
                        tx.write(data[i][1] + "\t" + "\t");
                        tx.write(data[i][2] + "\t\t");
                        tx.write(data[i][3] + "\t");
                        tx.write(data[i][4]  + "\t");
                        tx.write("\n");
                    }
                    tx.close();
                    //JOptionPane.showMessageDialog(f, "File Succefully Created");
                    done.setVisible(true);
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "Sorry for Inconvinance File not created");
                }
            }
        });

        buttoption.setBounds(50,700,150,30);
        buttprint.setBounds(250,700,150,30);
        
        JScrollPane sp = new JScrollPane(jt);
        f.add(done);
        f.add(buttoption);
        f.add(buttprint);
        f.add(sp);
        
        device.setFullScreenWindow(f);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
