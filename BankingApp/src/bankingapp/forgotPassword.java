/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bankingapp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


class forgotPassword {

    forgotPassword(Statement smt) {

        // Declaring Frist Jframe for Login
        JFrame f = new JFrame("Find Password");

        // 2 lables for ID and Password
        JLabel lableAccountNumber, lableAadhaarNumber;

        // setting title of lable and position of lable
        lableAccountNumber = new JLabel("Account Number:");
        lableAccountNumber.setBounds(50, 80, 150, 30);

        // setting title of lable and position of lable
        lableAadhaarNumber = new JLabel("Aadhaar Number:");
        lableAadhaarNumber.setBounds(50, 130, 150, 30);

        // adding both lable to JFrame at set position
        f.add(lableAccountNumber);
        f.add(lableAadhaarNumber);

        // declaring 1 textField and 1 passwordField
        JTextField textAccountNumber, textAadhaarNumber;

        // setting account textfield position
        textAccountNumber = new JTextField("");
        textAccountNumber.setBounds(150, 80, 215, 30);

        // setting Password field position
        textAadhaarNumber = new JTextField("");
        textAadhaarNumber.setBounds(150, 130, 215, 30);

        // adding both field into JFrame
        f.add(textAccountNumber);
        f.add(textAadhaarNumber);

        // Declaring Submit button with ActionPerformer

        /*
         * This submit button takes AccountNumber and Password from user and stores in
         * to string.
         * After storing Id and password it CallFunction isValid to check password match
         * with setted
         * with given AccountNumber
         * after that it redirect to option page
         */
        JButton forgotAccount;
        forgotAccount = new JButton(new AbstractAction("Submit") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action
                String aadhar = "";
                String accountNumber = "";
                try {
                    aadhar = textAadhaarNumber.getText();
                    accountNumber = textAccountNumber.getText();
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "could not load your Acdont number");
                }

                String accQry = "Select PASSWORD from pcustomerpassword where account_number = '" + accountNumber + "';";
                String auth = "select aadhaar_number from pcustomer where account_number = '" + accountNumber + "';";
                String tableAadhaar = "";
                String pwd = "";
                if (accountNumber.matches("\\d+") == false) {
                    JOptionPane.showMessageDialog(f, "Only Digits are allowed as Account Number");
                } else {
                    if (aadhar.matches("\\d+") == false || aadhar.length() != 12) {
                        JOptionPane.showMessageDialog(f, "Only 12 Digits are allowed as Adhar Number");
                    } else {
                        try {
                            ResultSet rs1 = smt.executeQuery(auth);
                            while (rs1.next()) {
                                tableAadhaar = rs1.getString("AADHAAR_NUMBER");
                            }
                        } catch (Exception we) {
                            JOptionPane.showMessageDialog(f, "Error at Findig Aadhar number");
                        }

                        //new part
                        //valid : tableAadhaar.compareTo(aadhar) == 0
                        //EXp : pwd.compareTo("") == 0
                        if (tableAadhaar.compareTo("") != 0) {

                            try {
                                ResultSet rs2 = smt.executeQuery(accQry);
                                while (rs2.next()) {
                                    pwd = rs2.getString("password");
                                }
                                if (pwd.compareTo("") == 0) {
                                    JOptionPane.showMessageDialog(f, "Account Number not found");
                                } else {
                                    if (tableAadhaar.compareTo(aadhar) == 0) {
                                        JOptionPane.showMessageDialog(f, "Your Password : " + pwd);
                                        try {
                                            new login(smt);
                                            f.setVisible(false);
                                        } catch (Exception w) {
                                            JOptionPane.showMessageDialog(f, "Error at Auto redirect you can navigate manually to the login page");
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(f, "Aadhar number does not match");
                                    }
                                }
                            } catch (Exception m) {
                                JOptionPane.showMessageDialog(f, "Error at Getting your password");
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Account number not found");
                        }
                    }
                }
            } // storing AccountNumber
        });

        JButton forgotPassword = new JButton(new AbstractAction("Login") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action

                try {
                    new login(smt);
                    f.setVisible(false);
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Error at navigation");
                }
            }
        });
        // setting position of openNewAvvount button
        forgotAccount.setBounds(50, 180, 150, 30);
        forgotPassword.setBounds(215, 180, 150, 30);

        // adding both button into JFrame
        f.add(forgotAccount);
        f.add(forgotPassword);

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