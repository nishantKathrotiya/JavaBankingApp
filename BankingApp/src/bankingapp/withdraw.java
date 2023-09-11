/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



// Withdraw page
class Withdraw {

    Withdraw(Statement smt) {

        JFrame f = new JFrame("Withdraw");

        JLabel lableDepositAmount;
        JTextField textFieldDepositAmount;

        lableDepositAmount = new JLabel("Enter your Amount:");
        lableDepositAmount.setBounds(50, 80, 180, 30);
        textFieldDepositAmount = new JTextField("");
        textFieldDepositAmount.setBounds(180, 80, 150, 30);

        JButton c = new JButton(new AbstractAction("Withdraw") {

            public void actionPerformed(ActionEvent e) {
                // Deposit Action
                String ao = "";
                int temp = 0;
                int finalAmount;
                boolean valid = false;
                try {
                    ao = textFieldDepositAmount.getText();
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "Enter Amount Again Some error at Backend");
                }
                valid = ao.matches("\\d+");

                if (ao.compareTo("") == 0 || valid == false) {
                    JOptionPane.showMessageDialog(f, "Make sure you enterd only digits");

                } else {
                    int passAmount = Integer.parseInt(ao);
                    if (BankingApp.acNum.compareTo("") != 0) {

                        String getPastAmount = "Select amount from pbalance where account_number = '" + BankingApp.acNum + "';";
                        ResultSet rs1;
                        try {
                            rs1 = smt.executeQuery(getPastAmount);
                            while (rs1.next()) {
                                temp = rs1.getInt("amount");
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(f, "Could not fatch your balance\nTry again");
                        }
                        finalAmount = temp - passAmount;
                        String[] dandT = new String[2];
                        dandT = func.date();
                        //If function does not get current date for History than abort task
                        if (dandT[0] == "Not got date") {
                            JOptionPane.showMessageDialog(f, "Error at getting time please try after some time");
                            new option(smt);
                            f.setVisible(false);
                        } else {
                            String updateBalance = "update pbalance set amount=" + finalAmount + " where account_number = '" + BankingApp.acNum + "';";
                            String history = "insert into phistory values ('" + BankingApp.acNum + "',-" + ao + ",'DE','" + dandT[0] + "','" + dandT[1] + "');";

                            if ((temp - 500) < passAmount) {
                                JOptionPane.showMessageDialog(f, "Minimum Required Balance is 500");

                            } else {

                                try {
                                    smt.executeUpdate(history);
                                    smt.executeUpdate(updateBalance);
                                    JOptionPane.showMessageDialog(f, "Amount Add : " + passAmount + "\n total amount : " + finalAmount + ";");
                                    try {
                                        new option(smt);
                                        f.setVisible(false);
                                    } catch (Exception w) {
                                        JOptionPane.showMessageDialog(f, "Balance updated but could not redirected to Options");
                                    }

                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(f, "Error at update");
                                }
                            }
                        }
                    }
                }
            }
        });

        JButton b = new JButton(new AbstractAction("Go to Option") {

            public void actionPerformed(ActionEvent e) {
                // Option Action
                try {
                    new option(smt);
                    f.setVisible(false);
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "Error at navigation");
                }

            }
        });
        // setting position of button
        c.setBounds(50, 180, 150, 30);
        b.setBounds(210, 180, 150, 30);

        // adding all Elements into JFrame
        f.add(lableDepositAmount);
        f.add(textFieldDepositAmount);
        f.add(c);
        f.add(b);

        // setting Height.width and layout of frame
        f.setSize(470, 450);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    smt.close();
                    JOptionPane.showMessageDialog(f, "Thank YOU");// Print "BY" to the terminal
                    System.exit(0); // Terminate the program
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "Could not Disconnect you from server");
                }

            }
        });
    }
}

