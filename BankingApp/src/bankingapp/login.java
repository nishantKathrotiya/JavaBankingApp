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
import javax.swing.JPasswordField;
import javax.swing.JTextField;


class login {

    public login(Statement smt) {

        // Declaring Frist Jframe for Login
        JFrame f = new JFrame("Login");

        // 2 lables for ID and Password
        JLabel lableAccountNumber, lableAccountPassword;

        // setting title of lable and position of lable
        lableAccountNumber = new JLabel("Account Number:");
        lableAccountNumber.setBounds(50, 80, 150, 30);

        // setting title of lable and position of lable
        lableAccountPassword = new JLabel("Password:");
        lableAccountPassword.setBounds(50, 130, 150, 30);

        // adding both lable to JFrame at set position
        f.add(lableAccountNumber);
        f.add(lableAccountPassword);

        // declaring 1 textField and 1 passwordField
        JTextField textFieldAccountNumber;
        JPasswordField passwordLogin;

        // setting account textfield position
        textFieldAccountNumber = new JTextField("");
        textFieldAccountNumber.setBounds(150, 80, 150, 30);

        // setting Password field position
        passwordLogin = new JPasswordField(null, null, 0);
        passwordLogin.setBounds(150, 130, 150, 30);

        // adding both field into JFrame
        f.add(textFieldAccountNumber);
        f.add(passwordLogin);

        // Declaring Submit button with ActionPerformer

        /*
         * This submit button takes AccountNumber and Password from user and stores in
         * to string.
         * After storing Id and password it CallFunction isValid to check password match
         * with setted
         * with given AccountNumber
         * after that it redirect to option page
         */
        JButton c;
        c = new JButton(new AbstractAction("SUBMIT") {

            public void actionPerformed(ActionEvent e) {
                // SUBMIT Action
                char[] p;
                String loginAccount = "";
                String password = "";
                String qpass = "";
                String temp = "";
                try {

                    // storing AccountNumber
                    loginAccount = textFieldAccountNumber.getText();
                    p = passwordLogin.getPassword();
                    password = String.valueOf(p);
                    qpass = "select password from pcustomerpassword where Account_number ='" + loginAccount + "';";

                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Could not load your Details");
                }
                //getting password from databse
                try {
                    ResultSet rs1 = smt.executeQuery(qpass);

                    while (rs1.next()) {
                        temp = rs1.getString("PASSWORD");
                        BankingApp.acNum = loginAccount;
                    }
                } catch (Exception l) {
                    JOptionPane.showMessageDialog(f, "Could not find your password from database");
                }

                if (loginAccount.compareTo("") == 0) {
                    JOptionPane.showMessageDialog(f, "Enter Account Number");
                } else {
                    if (temp.compareTo("") == 0) {
                        JOptionPane.showMessageDialog(f, "Account Number not found");
                    } else {
                        if (password.compareTo(temp) == 0 && password.compareTo("") != 0 && loginAccount.compareTo("") != 0) {
                            try {
                                new option(smt);
                                f.setVisible(false);
                            } catch (Exception w) {
                                JOptionPane.showMessageDialog(f, "Server Error");
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Wrong password 1");
                        }
                    }
                }
            }
        });

        // another button openNewAccount with ActionPerformed function
        // it just call Signup JFrame and dispose current JFrame
        JButton b = new JButton(new AbstractAction("Open New Account") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action
                try {
                    new signup(smt);
                    f.setVisible(false);
                    // storing AccountNumber
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Sorry for inconvinance");
                }

            }
        });

        JButton forgotAccount = new JButton(new AbstractAction("Find Account") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action

                try {
                    new forgotAccount(smt);
                    f.setVisible(false);
                    // storing AccountNumber
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Sorry for inconvinance");

                }

            }
        });

        JButton forgotPassword = new JButton(new AbstractAction("Forgot Password") {

            public void actionPerformed(ActionEvent e) {
                // NEWACCOUNT Action

                try {
                    new forgotPassword(smt);
                    f.setVisible(false);

                    // storing AccountNumber
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(f, "Sorry for inconvinance");

                }

            }
        });

        // setting position of openNewAvvount button
        c.setBounds(50, 180, 150, 30);
        b.setBounds(215, 180, 150, 30);
        forgotAccount.setBounds(50, 220, 150, 30);
        forgotPassword.setBounds(215, 220, 150, 30);

        // adding both button into JFrame
        f.add(c);
        f.add(b);
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