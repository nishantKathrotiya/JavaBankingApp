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


class forgotAccount {

    forgotAccount(Statement smt) {

        // Declaring Frist Jframe for Login
        JFrame f = new JFrame("Find Account");

        // 2 lables for ID and Password
        JLabel lableMobileNumber, lableAadhaarNumber;

        // setting title of lable and position of lable
        lableAadhaarNumber = new JLabel("Aadhaar Number:");
        lableAadhaarNumber.setBounds(50, 80, 150, 30);

        // setting title of lable and position of lable
        lableMobileNumber = new JLabel("Mobile Number:");
        lableMobileNumber.setBounds(50, 130, 150, 30);

        // adding both lable to JFrame at set position
        f.add(lableAadhaarNumber);
        f.add(lableMobileNumber);

        // declaring 1 textField and 1 passwordField
        JTextField textMobileNumber, textAadhaarNumber;

        // setting account textfield position
        textAadhaarNumber = new JTextField("");
        textAadhaarNumber.setBounds(150, 80, 215, 30);

        // setting Password field position
        textMobileNumber = new JTextField("");
        textMobileNumber.setBounds(150, 130, 215, 30);

        // adding both field into JFrame
        f.add(textAadhaarNumber);
        f.add(textMobileNumber);

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
                String Aadhar = "";
                String number = "";
                String getAcc = "";
                ResultSet rs1;
                try {
                    Aadhar = textAadhaarNumber.getText();
                    number = textMobileNumber.getText();
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(f, "Your Information has not Stored");
                }
                if (Aadhar.compareTo("\\+d") == 0 || Aadhar.length() != 12) {
                    JOptionPane.showMessageDialog(f, "only 12 digits are allowed as aadhar number");
                } else {
                    if (number.compareTo("\\+d") == 0 || number.length() != 10) {
                        JOptionPane.showMessageDialog(f, "only 10 digits are allowed as mobile Number");
                    } else {
                        String accQry = "Select account_number from pcustomer where mobile_number = '" + number + "' and aadhaar_number = '" + Aadhar + "';";
                        try {
                            rs1 = smt.executeQuery(accQry);
                            while (rs1.next()) {
                                getAcc = rs1.getString("Account_number");
                            }
                        } catch (Exception w) {
                            JOptionPane.showMessageDialog(f, "Error At database");
                        }
                        if (getAcc.compareTo("") == 0) {
                            JOptionPane.showMessageDialog(f, "Not Found!!!\nif you sure than reopen");
                        } else {
                            JOptionPane.showMessageDialog(f, "Account Number " + getAcc);
                            try {
                                new login(smt);
                                f.setVisible(false);
                            } catch (Exception l) {
                                JOptionPane.showMessageDialog(f, "Error auto redirect");
                            }
                        }
                    }
                }
            }
        });

        JButton forgotPassword = new JButton(new AbstractAction("Login") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action

                try {
                    new login(smt);
                    f.setVisible(false);
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Error at Navigation");
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
