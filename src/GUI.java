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
    private JTextField xmxField;
    private JTextField xmsField;
    private JComboBox<String> gcChooser;

    public GUI() {
        initialize();
    }

    private void initialize() {

        frame = new JFrame("Garbage Collector Chooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());

        // Button Panel
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(4,1));

        // Input Panel, sets Memory Size
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1,4));

        // Input Panel Text Fields
        xmxField = new JTextField("3g");
        xmsField = new JTextField("3g");
        JLabel xmxLabel = new JLabel("Xmx:");
        JLabel xmsLabel = new JLabel("Xms:");
        String[] gc = { "KillTheGarbageCollector", "GCTest", "GCTest2"};
        gcChooser = new JComboBox<String>(gc);

        // Add Fields to Input Panel
        inputPanel.add(xmsLabel);
        inputPanel.add(xmsField);
        inputPanel.add(xmxLabel);
        inputPanel.add(xmxField);
//        inputPanel.add(gcChooser);

        // Create Buttons
        btnG1 = new JButton("G1");
        btnCMS = new JButton("CMS");
        btnSerial = new JButton("Serial");

        // Add Buttons to panel
        btnPanel.add(btnSerial);
        btnPanel.add(btnCMS);
        btnPanel.add(btnG1);
        btnPanel.add(gcChooser);

        // Add ActionListners to Buttons
        btnG1.addActionListener(new G1ActionListener());
        btnCMS.addActionListener(new CMSActionListener());
        btnSerial.addActionListener(new SerialActionListener());

        // Add all stuff to contentPane
        contentPane.add(btnPanel, BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.SOUTH);

        // Show Frame
        //frame.setSize(300, 250);
        frame.pack();
        frame.setVisible(true);
    }

    // Very ugly...
    public static void startG1(String xms, String xmx, String gc) throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder;
        if(gc.equals("KillTheGarbageCollector")) {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-XX:+PrintGCDetails", "-cp",
                            classpath,
                            KillTheGarbageCollector.class.getName());
        } else if (gc.equals("GCTest")) {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-XX:+PrintGCDetails", "-cp",
                            classpath,
                            GCTest.class.getName());
        } else {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=250", "-XX:+PrintGCDetails", "-cp",
                            classpath,
                            GCTest2.class.getName());
        }
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void startCMS(String xms, String xmx, String gc) throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder;
        if(gc.equals("KillTheGarbageCollector")) {
              processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseConcMarkSweepGC", "-verbose:GC", "-cp",
                            classpath,
                            KillTheGarbageCollector.class.getName());
        } else if (gc.equals("GCTest")) {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseConcMarkSweepGC", "-verbose:GC", "-cp",
                            classpath,
                            GCTest.class.getName());
        } else {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseConcMarkSweepGC", "-verbose:GC", "-cp",
                            classpath,
                            GCTest2.class.getName());
        }
        Process process = processBuilder.start();
        process.waitFor();
    }

    public static void startSerial(String xms, String xmx, String gc) throws Exception {
        String separator = System.getProperty("file.separator");
        String classpath = System.getProperty("java.class.path");
        String path = System.getProperty("java.home")
                + separator + "bin" + separator + "java";
        ProcessBuilder processBuilder;
        if(gc.equals("KillTheGarbageCollector")) {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseSerialGC", "-verbose:GC", "-cp",
                            classpath,
                            KillTheGarbageCollector.class.getName());
        } else if (gc.equals("GCTest")) {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseSerialGC", "-verbose:GC", "-cp",
                            classpath,
                            GCTest.class.getName());
        } else {
            processBuilder =
                    new ProcessBuilder(path, "-Xms" + xms, "-Xmx" + xmx, "-XX:+UseSerialGC", "-verbose:GC", "-cp",
                            classpath,
                            GCTest2.class.getName());
        }
        Process process = processBuilder.start();
        process.waitFor();
    }

    // Inner Classes
    private class G1ActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startG1(xmsField.getText(), xmxField.getText(), (String)gcChooser.getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CMSActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startCMS(xmsField.getText(), xmxField.getText(), (String)gcChooser.getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class SerialActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            // Start StressTest
            try {
                startSerial(xmsField.getText(), xmxField.getText(), (String)gcChooser.getSelectedItem());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
