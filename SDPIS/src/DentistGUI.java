import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by User on 14/11/2016.
 */
public class DentistGUI extends JFrame {

    public void DentistGUI(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);
        JButton btnChk = new JButton("Finish Appointment");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().FinishAppointmment();
                    }
                }
        );
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        try {
                            new ViewAppointments().ViewDentistAppointments();
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
        buttonPanel.add(btnChk);
        buttonPanel.add(btnView);

        JLabel title = new JLabel("Dentist View");

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set frame visibility to true.
        setVisible(true);
    }


    public void FinishAppointmment(){
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        JLabel title = new JLabel("Enter Appointment Information To Log Visit:");
        JLabel type = new JLabel("Treatment type:");
        String[] tType = {"Checkup","","","",""};
        final JComboBox Type = new JComboBox(tType);
        JLabel sTime = new JLabel("Start Time:");
        String[] hour = {"Hour","09","10","11","12","14","15","16"};
        String[] minute = {"Minute","00","20","40"};
        final JComboBox hr = new JComboBox(hour);
        final JComboBox min = new JComboBox(minute);
        JButton bSubmit = new JButton("Submit");

        JPanel timePanel = new JPanel();
        timePanel.add(sTime);
        timePanel.add(hr);
        timePanel.add(min);

        JPanel mPanel = new JPanel();
        mPanel.add(timePanel);
        mPanel.add(bSubmit);

        mPanel.setLayout(new BoxLayout(mPanel, BoxLayout.Y_AXIS));
        mPanel.setAlignmentX(Component.LEFT_ALIGNMENT);


        bSubmit.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        String[] FinishedApp = {(String)hr.getSelectedItem()+":"+(String)min.getSelectedItem()};

                        System.out.print(Arrays.toString(FinishedApp));

                    }
                }
        );

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        dispose();
                        new DentistGUI().DentistGUI();
                    }
                }
        );

        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        Container contentPane = getContentPane();
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(mPanel, BorderLayout.CENTER);
        contentPane.add(btnBack, BorderLayout.SOUTH);


        mPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));

        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
