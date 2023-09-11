package bankingapp;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextField;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;


class signup extends JFrame {

    JDatePickerImpl datePicker;

    public signup(Statement smt) {
        JFrame newacc = new JFrame("SignUp");
        JLabel labelSurname, labelName, labelFather, labelGENDER, labelDOB;
        JLabel labbelMobileNumber, labelAdhaar, labelEmail, labelPassword, labelReenter;

        labelSurname = new JLabel("Surname:");
        labelSurname.setBounds(50, 50, 100, 30);
        labelName = new JLabel("Name:");
        labelName.setBounds(50, 100, 100, 30);
        labelFather = new JLabel("Father's Name:");
        labelFather.setBounds(50, 150, 100, 30);
        labelGENDER = new JLabel("GENDER:");
        labelGENDER.setBounds(50, 200, 100, 30);
        labelDOB = new JLabel("DOB:");
        labelDOB.setBounds(50, 260, 100, 30);

        labbelMobileNumber = new JLabel("Mobile No. :");
        labbelMobileNumber.setBounds(50, 300, 100, 30);
        labelAdhaar = new JLabel("Adharcard No. :");
        labelAdhaar.setBounds(50, 350, 100, 30);
        labelEmail = new JLabel("Email Id :");
        labelEmail.setBounds(50, 400, 100, 30);
        labelPassword = new JLabel("Set your password : ");
        labelPassword.setBounds(50, 450, 150, 30);
        labelReenter = new JLabel("Enter your password Again : ");
        labelReenter.setBounds(50, 520, 150, 30);

        newacc.add(labelSurname);
        newacc.add(labelName);
        newacc.add(labelFather);
        newacc.add(labelGENDER);
        newacc.add(labelDOB);
        newacc.add(labbelMobileNumber);
        newacc.add(labelAdhaar);
        newacc.add(labelEmail);
        newacc.add(labelPassword);
        newacc.add(labelReenter);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model);
        datePicker = new JDatePickerImpl(datePanel);

        datePicker.setBounds(150, 260, 200, 40);
        newacc.add(datePicker);

        JRadioButton rb1, rb2;
        rb1 = new JRadioButton("Male");
        rb1.setBounds(150, 200, 100, 30);
        rb2 = new JRadioButton("Female");
        rb2.setBounds(150, 230, 100, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        newacc.add(rb1);
        newacc.add(rb2);

        JTextField textFieldSure, textFieldName, textFieldFather;
        JTextField textFieldMobile, textFieldAdhar, textFieldEmail;
        JPasswordField setPassField, reenterPassField;

        textFieldSure = new JTextField("");
        textFieldSure.setBounds(150, 50, 200, 30);
        textFieldName = new JTextField("");
        textFieldName.setBounds(150, 100, 200, 30);
        textFieldFather = new JTextField("");
        textFieldFather.setBounds(150, 150, 200, 30);

        textFieldMobile = new JTextField("");
        textFieldMobile.setBounds(150, 300, 200, 30);
        textFieldAdhar = new JTextField("");
        textFieldAdhar.setBounds(150, 350, 200, 30);
        textFieldEmail = new JTextField("");
        textFieldEmail.setBounds(150, 400, 200, 30);
        setPassField = new JPasswordField("");
        setPassField.setBounds(50, 490, 200, 30);
        reenterPassField = new JPasswordField("");
        reenterPassField.setBounds(50, 550, 200, 30);

        newacc.add(textFieldSure);
        newacc.add(textFieldName);
        newacc.add(textFieldFather);
        newacc.add(textFieldMobile);
        newacc.add(textFieldAdhar);
        newacc.add(textFieldEmail);
        newacc.add(setPassField);
        newacc.add(reenterPassField);

        JButton buttSignup;
        buttSignup = new JButton(new AbstractAction("SUBMIT") {

            public void actionPerformed(ActionEvent e) {

                String dob = "";

                try {
                    String sureName = "";
                    String name = "";
                    String father = "";
                    String number = "";
                    String adhar = "";
                    String email = "";
                    String pass = "";
                    String reenterPass = "";
                    String gender = "";
                    String accountNumber = "";

                    char p[];
                    char rp[];
                    sureName = textFieldSure.getText();
                    name = textFieldName.getText();
                    father = textFieldFather.getText();
                    number = textFieldMobile.getText();
                    adhar = textFieldAdhar.getText();
                    email = textFieldEmail.getText();
                    p = setPassField.getPassword();
                    rp = reenterPassField.getPassword();
                    String password = String.valueOf(p);
                    String repassword = String.valueOf(rp);

                    //taking DOB
                    UtilDateModel selectedModel = (UtilDateModel) datePicker.getModel();
                    java.util.Date selectedDate = selectedModel.getValue();

                    // Calculate age based on selected date
                    Calendar birthdate = Calendar.getInstance();
                    birthdate.setTime(selectedDate);
                    Calendar currentDate = Calendar.getInstance();

                    int age = currentDate.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR);
                    int months = currentDate.get(Calendar.MONTH) - birthdate.get(Calendar.MONTH);
                    int days = currentDate.get(Calendar.DAY_OF_MONTH) - birthdate.get(Calendar.DAY_OF_MONTH);

                    int year = birthdate.get(Calendar.YEAR);
                    int mon = birthdate.get(Calendar.MONTH);
                    int da = birthdate.get(Calendar.DAY_OF_MONTH);

                    if (months < 0 || (months == 0 && days < 0)) {
                        age--;
                    }
                    //End of DOB

                    //assigning Selected Gender to variable
                    if (rb1.isSelected() == true) {
                        gender = "Male";
                    } else {
                        if (rb2.isSelected() == true) {
                            gender = "Male";
                        }
                    }
                    //First checking Age after that Whole Fields
                    if (age >= 18) {
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        //JOptionPane.showMessageDialog(newacc, "Selected Date: " + dateFormat.format(selectedDate));
                        dob = year + "-" + mon + "-" + da;

                        // logic for all the field are properly added or not
                        if (name.compareTo("") == 0 || father.compareTo("") == 0 || sureName.compareTo("") == 0
                                || email.compareTo("") == 0 || adhar.compareTo("") == 0 || number.compareTo("") == 0) {
                            JOptionPane.showMessageDialog(newacc, "Fill All the Details");
                        } else {

                            if (gender == "") {
                                JOptionPane.showMessageDialog(newacc, "Select Gender");
                            } else {
                                boolean num = false;

                                if ((num = number.matches("\\d+")) == false || number.length() != 10) {
                                    JOptionPane.showMessageDialog(newacc, "Enter valid Mobile Number");
                                } else {

                                    boolean adh = false;

                                    if ((adh = adhar.matches("\\d+")) == false || adhar.length() != 12) {
                                        JOptionPane.showMessageDialog(newacc, "Enter valid Adhar number");
                                    } else {

                                        if (password.compareTo(repassword) != 0) {
                                            JOptionPane.showMessageDialog(newacc, "Password Does not match");
                                        } else {
                                            // what to do when all the details are valid
                                            //Genrate Account Number Using Current Date and time
                                            try {
                                                LocalDateTime currentDateTime = LocalDateTime.now();
                                                long millisecondsSinceEpoch = System.currentTimeMillis();

                                                // Step 2: Format the local date and time as a string
                                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                                String currentDateTimeAsString = currentDateTime.format(formatter);

                                                // Step 3: Remove non-numeric characters from the string
                                                currentDateTimeAsString = currentDateTimeAsString.replaceAll("[^0-9]", "");

                                                int la = (int) (millisecondsSinceEpoch % 1000);
                                                accountNumber = currentDateTimeAsString + la;

                                            } catch (Exception a) {
                                                JOptionPane.showMessageDialog(newacc, "Error in Genrating Account Number");
                                            }
                                            //Uploading Data to Database
                                            try {
                                                String signUp = "insert into pcustomer(account_number,surename,name,father_name,gender,dob,mobile_number,aadhaar_number,email_id)" + "values('" + accountNumber + "','" + sureName + "','" + name + "','" + father + "','" + gender + "','" + dob + "','" + number + "','" + adhar + "','" + email + "');";
                                                String qpass = "insert into pcustomerpassword(account_number,password)" + "values('" + accountNumber + "','" + password + "');";
                                                String pbal = "insert into pbalance(account_number,amount)" + "values('" + accountNumber + "','0');";
//                                                    
                                                smt.executeUpdate(signUp);
                                                smt.executeUpdate(qpass);
                                                smt.executeUpdate(pbal);
                                                //smt.close();
                                                JOptionPane.showMessageDialog(newacc, "Account Number Genrated Succesfully \nYour avvount number : " + accountNumber);
                                                JOptionPane.showMessageDialog(newacc, "Your Details Updated Succesfully");
                                                new login(smt);
                                                newacc.setVisible(false);

                                            } catch (Exception w) {
                                                JOptionPane.showMessageDialog(newacc, "Aadhaar Number Alredy Exist");
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(newacc, "Age must be 18 years or older.");
                    }
                } catch (Exception s) {
                    JOptionPane.showMessageDialog(newacc,
                            "Sorry for inconvenience \n Some error from our side Try agin");
                    ;
                }
            }

        });

        JButton buttLogin = new JButton(new AbstractAction("Login") {

            public void actionPerformed(ActionEvent e) {
                try {
                    new login(smt);
                    newacc.setVisible(false);
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(newacc, "Sorry for inconvinance");

                }

            }
        });

        buttLogin.setBounds(170, 600, 100, 30);
        buttSignup.setBounds(50, 600, 100, 30);
        newacc.add(buttLogin);
        newacc.add(buttSignup);
        newacc.setSize(500, 750);
        newacc.setLayout(null);
        newacc.setVisible(true);
        newacc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newacc.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    smt.close();
                    JOptionPane.showMessageDialog(newacc, "Thank YOU");// Print "BY" to the terminal
                    System.exit(0); // Terminate the program
                } catch (Exception w) {
                    JOptionPane.showMessageDialog(newacc, "Could not Disconnect you from server");
                }

            }
        });
    }
}