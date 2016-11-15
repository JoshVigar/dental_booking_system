import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by User on 14/11/2016.
 */
public class HygienistGUI extends JFrame {

    public void HygienistGUI(){
        //Set title and size of Hygienist frame
        setTitle("Sheffield Dental Practice");
        setSize(500,600);

        //create a buttton to finish an appointment
        JButton btnChk = new JButton("Finish Appointment");
        btnChk.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to finish appointment view
                    }
                }
        );

        //add button action listener
        JButton btnView = new JButton("View Appointments");
        btnView.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        //Go to appointments
                    }
                }
        );
        //create a buttonPanel and add all buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(btnChk);
        buttonPanel.add(btnView);

        JLabel title = new JLabel("Hygienist View");

        //create ints to represent borders for contents of the panel
        int bHeight = (int)(this.getHeight()*0.1);
        int bWidth = (int)(this.getWidth()*0.1);

        //get the contentPane add the title and buttons.
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(title, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(bHeight,bWidth,bHeight,bWidth));
        //Don't forget to pack!
        pack();
        setLocationRelativeTo(null);
        //set frame visibility to true.
        setVisible(true);
    }

}
