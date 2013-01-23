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
    private JButton btnG1;
    private JButton btnCMS;
    private JButton btnSerial;

    public GUI() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame("Garbage Collector Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(3,1));

        // Create Buttons
        btnG1 = new JButton("G1");
        btnCMS = new JButton("CMS");
        btnSerial = new JButton("Serial");

        // Add Buttons to panel
        contentPane.add(btnSerial);
        contentPane.add(btnCMS);
        contentPane.add(btnG1);

        // Add ActionListners to Buttons
        btnG1.addActionListener(new G1ActionListener());
        btnCMS.addActionListener(new CMSActionListener());
        btnSerial.addActionListener(new SerialActionListener());

        // Show Frame
        frame.setSize(300, 250);
        frame.setVisible(true);
    }

    public static void startG1() throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder =
                new ProcessBuilder(path, "-Xms3g", "-Xmx3g", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-XX:+PrintGCDetails", "-cp",
                        classpath,
                        KillTheGarbageCollector.class.getName());
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void startCMS() throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder =
                new ProcessBuilder(path, "-Xms3g", "-Xmx3g", "-XX:+UseConcMarkSweepGC", "-verbose:GC", "-cp",
                        classpath,
                        KillTheGarbageCollector.class.getName());
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void startSerial() throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder =
                new ProcessBuilder(path, "-Xms3g", "-Xmx3g", "-XX:+UseSerialGC", "-verbose:GC", "-cp",
                        classpath,
                        KillTheGarbageCollector.class.getName());
        Process process = processBuilder.start();
        process.waitFor();
    }

    // Inner Classes
    private class G1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startG1();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private class CMSActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startCMS();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    private class SerialActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startSerial();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
