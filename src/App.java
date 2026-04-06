import consoleTasks.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    private JTextField funcField, startField, stopField, stepField;
    private JPanel chartContainer;

    public App() {
        setTitle("GUIApplication");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createTitledBorder("Function"));
        topPanel.add(new JLabel("f(x):"));
        funcField = new JTextField("sin(x)/x", 30);
        topPanel.add(funcField);
        JLabel dfLabel = new JLabel("Derivative: ");
        topPanel.add(dfLabel);
        add(topPanel, BorderLayout.NORTH);

        chartContainer = new JPanel(new BorderLayout());
        add(chartContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        
        JButton plotBtn = new JButton("Plot");
        JButton exitBtn = new JButton("Exit");
        
        startField = new JTextField("-6", 4);
        stopField = new JTextField("6", 4);
        stepField = new JTextField("0.01", 4);

        bottomPanel.add(plotBtn);
        bottomPanel.add(exitBtn);
        bottomPanel.add(new JLabel("Start:"));
        bottomPanel.add(startField);
        bottomPanel.add(new JLabel("Stop:"));
        bottomPanel.add(stopField);
        bottomPanel.add(new JLabel("Step:"));
        bottomPanel.add(stepField);
        
        add(bottomPanel, BorderLayout.SOUTH);

        plotBtn.addActionListener(e -> updateChart(dfLabel));
        exitBtn.addActionListener(e -> System.exit(0));

        pack();
        setSize(600, 500);
        setLocationRelativeTo(null);
    }

    private void updateChart(JLabel dfLabel) {
        try {
            String fStr = funcField.getText();
            double start = Double.parseDouble(startField.getText());
            double stop = Double.parseDouble(stopField.getText());
            double step = Double.parseDouble(stepField.getText());

            SFunction mainFunc = new SFunction(fStr);
            SFunction derivFunc = mainFunc.derive();

            dfLabel.setText("Derivative: "+derivFunc.toString());

            XYSeries seriesFunc = new XYSeries("Function");
            XYSeries seriesDeriv = new XYSeries("Derivative");

            for (double x = start; x <= stop; x += step) {
                seriesFunc.add(x, mainFunc.evalf(x));
                seriesDeriv.add(x, derivFunc.evalf(x));
            }

            XYSeriesCollection dataset = new XYSeriesCollection();
            dataset.addSeries(seriesFunc);
            dataset.addSeries(seriesDeriv);

            JFreeChart chart = ChartFactory.createXYLineChart(
                null, "X", "Y", dataset
            );

            chartContainer.removeAll();
            chartContainer.add(new ChartPanel(chart), BorderLayout.CENTER);
            chartContainer.validate();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error in function or parameters: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
