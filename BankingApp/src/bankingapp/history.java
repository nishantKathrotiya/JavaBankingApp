/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapp;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


class history extends JFrame{
    
    history(Statement smt)  {
        JFrame f = new JFrame("Transection");
        int rows = 0,count=0;
        ResultSet rs1 = null;
        
        try {
            rs1 = smt.executeQuery("select * from phistory where account_number = " + BankingApp.acNum + ";");
            rs1.last();
            rows = rs1.getRow();
            rs1.beforeFirst();
        } catch (Exception w) {

        }

        String[][] data = new String[rows][5];
        try{
             while(rs1.next()){
            
            data[count][0] = rs1.getString("account_number");
            data[count][1] = String.valueOf(rs1.getInt("amount"));
            data[count][2] = rs1.getString("transaction_type");
            data[count][3] = String.valueOf(rs1.getDate("t_date"));
            data[count][4] = rs1.getString("t_time");
            count++;
        }
        }catch(Exception w){
            
        }
       
        String[] column = {"Account number", "Amount" ,"Transection_type" , "Date" ,"Time"};

        JTable jt = new JTable(data, column);
        jt.setBounds(80, 80, 100, 100);
        JScrollPane sp = new JScrollPane(jt);
        f.add(sp);
        f.setSize(700, 150);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
