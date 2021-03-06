import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */
public class HygienistGUI extends JFrame {

    public void HygienistGUI(){
        //initialise window settings
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //add buttons and event handlers
        JButton btnFApp = new JButton("Finish Appointment");
        btnFApp.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new HygienistGUI().FinishAppointmment();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new ViewAppointment().startDate("Hygienist");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (InstantiationException e1) {
                            e1.printStackTrace();
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
        );
        JButton btnBack = new JButton("Go Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new WelcomeGUI().WelcomeGUI();
                    }
                }
        );

        //create a buttonPanel and add all buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnFApp);
        buttonPanel.add(btnView);

        //create title label
        JLabel title = new JLabel("Hygienist Page");

        //for adding borders
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //add all components to the contentpane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);

        //add the border to the inputs
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        //Don't forget to pack!
        pack();
        //setting the location in the window and the close operation
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame visibility to true.
        setVisible(true);
    }

    //same as in dentsit gui
    public void FinishAppointmment()throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException{

        final DataAccessBase reg = new DataAccessBase("jdbc:mysql://stusql.dcs.shef.ac.uk/team012?user=team012&password=a735fd61");


        //initialising window settings
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //add buttons, labels and comboboxes for inputs
        JLabel title = new JLabel("Enter Appointment Time To Log Visit:");
        JLabel sTime = new JLabel("Start Time:");
        String[] hour = {"Hour","09","10","11","12","14","15","16","17"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        JButton bSubmit = new JButton("Submit");

        //creating the timepanel for selecting appointment time
        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        //adding elements to main panel
        JPanel mPanel = new JPanel();
        mPanel.add(timePanel);
        mPanel.add(bSubmit);
        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate localDate = LocalDate.now();
        final String today = dtf.format(localDate);

        //event listener for submit button
        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //this checks if an appointment exists
                        boolean appointmentExists = true;
                        String visitType= "";
                        String getVT = "SELECT TypeOfVisit,ID FROM Appointment Where (State = 'Active' "
                                + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
                                +":"+ min.getSelectedItem().toString()+":00' AND Partner = 'Hygienist')";
                        ResultSet rVT = reg.getData(getVT);
                        try {
                            while(rVT.next()){
                                if(!rVT.wasNull()) {
                                    visitType = rVT.getString("TypeOfVisit");
                                }
                                else
                                    appointmentExists = false;
                            }
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        //if it does then it wil first check in what category it is
                        if(appointmentExists) {
                            int cost = 0;
                            String getCost = "SELECT Cost FROM VisitType Where TypeOfVisit = '" + visitType + "'";
                            ResultSet rCost = reg.getData(getCost);
                            try {
                                while (rCost.next()) {
                                    cost = rCost.getInt("Cost");
                                }
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }

                            //update the appointment to waiting and assign a cost to it
                            String updateAppointment = "UPDATE Appointment SET State = 'Waiting', Cost = " + cost + " WHERE (State = 'Active' "
                                    + "And ADate = '" + today + "' AND StartTime = '" + hr.getSelectedItem().toString()
                                    + ":" + min.getSelectedItem().toString() + ":00' AND Partner = 'Hygienist' )";
                            reg.updateData(updateAppointment);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "This Appointment time is unavailable. Please Select another.");

                        }
                        dispose();
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        //added back button and event listener
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new HygienistGUI().HygienistGUI();
                    }
                }
        );

        //for adding the border to the inputs
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //add elements to contentpane and setting layout
        Container contentpane = getContentPane();
        contentpane.add(title, BorderLayout.NORTH);
        contentpane.add(mPanel, BorderLayout.CENTER);
        contentpane.add(btnBack, BorderLayout.SOUTH);

        //adding the border to the inputs
        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        //setting position and close operation for window
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
