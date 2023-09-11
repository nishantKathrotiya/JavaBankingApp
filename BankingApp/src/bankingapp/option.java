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

class option {

    option(Statement smt) {
        JFrame opt = new JFrame("OPTION");
        JLabel wel = new JLabel("Welcome, Choose one of the Option");
        wel.setBounds(130, 40, 240, 40);

        JButton buttDeposit = new JButton(new AbstractAction("Deposit") {

            public void actionPerformed(ActionEvent e) {
                new Deposit(smt);
                opt.setVisible(false);
            }
        });

        JButton buttWithdraw = new JButton(new AbstractAction("Withdraw") {

            public void actionPerformed(ActionEvent e) {

                new Withdraw(smt);
                opt.setVisible(false);

            }
        });

        JButton buttBalance = new JButton(new AbstractAction("Check Balance") {

            public void actionPerformed(ActionEvent e) {

                int amou = 0;
                String getPastAmount = "Select amount from pbalance where account_number = '" + BankingApp.acNum + "';";
                ResultSet rs1;
                try {
                    rs1 = smt.executeQuery(getPastAmount);
                    while (rs1.next()) {
                        amou = rs1.getInt("amount");
                        JOptionPane.showMessageDialog(opt, "Account Balance : " + amou);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(opt, "Could not fatch your balance\nTry again");
                }

            }
        });
        
         JButton buttHistory = new JButton(new AbstractAction("Transections") {

            public void actionPerformed(ActionEvent e) {

                new history(smt);
                opt.setVisible(false);

            }
        });

        JButton buttLogout = new JButton(new AbstractAction("Log out") {

            public void actionPerformed(ActionEvent e) {
                try {
                    BankingApp.acNum = "";
                    JOptionPane.showMessageDialog(opt, "You Logged Out");
                    new login(smt);
                    opt.setVisible(false);
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(opt, "Sorry for inconvinance");

                }

            }
        });

        buttDeposit.setBounds(115, 100, 240, 40);
        buttWithdraw.setBounds(115, 160, 240, 40);
        buttBalance.setBounds(115, 220, 240, 40);
        buttHistory.setBounds(115, 280, 240, 40);
        buttLogout.setBounds(115, 340, 240, 40);
        

        opt.add(buttDeposit);
        opt.add(buttWithdraw);
        opt.add(buttBalance);
        opt.add(buttLogout);
        opt.add(buttHistory);
        
        opt.add(wel);
        opt.setSize(470, 450);
        opt.setLayout(null);
        opt.setVisible(true);
        opt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        opt.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    smt.close();
                    JOptionPane.showMessageDialog(opt, "Thank YOU");// Print "BY" to the terminal
                    System.exit(0); // Terminate the program
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(opt, "Could not Disconnect you from server");
                }

            }
        });

    }
}