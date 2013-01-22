import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: bbu
 * Date: 22.01.13
 * Time: 16:38
 */
public class GUI {
    private JFrame frame;
    private JButton KTGCbtn;
    private JButton GCTbtn;

    public GUI() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame("Stresstest Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(2,1));

        // Create Buttons
        KTGCbtn = new JButton("KillTheGarbageCollector");
        GCTbtn = new JButton("GCTest");

        // Add Buttons to panel
        contentPane.add(KTGCbtn);
        contentPane.add(GCTbtn);

        // Add ActionListners to Buttons
        KTGCbtn.addActionListener(new KTGCActionListener());
        GCTbtn.addActionListener(new GCTActionListener());

        // Show Frame
        frame.setSize(300, 250);
        frame.setVisible(true);
    }


    // Inner Classes
    private class KTGCActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            new KillTheGarbageCollector();
        }
    }

    private class GCTActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            new GCTest();
        }
    }
}
