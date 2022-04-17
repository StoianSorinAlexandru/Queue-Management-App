package gui;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame{

    ///main panel
    private JPanel contentPanel;

    ///imput panel
    private JPanel imputPanel;

    ///time panel
    private JPanel timePanel;
    private JLabel timeLabel;
    private JTextField timeTextField;

    ///queues panel
    private JPanel queuePanel;
    private JLabel queueLabel;
    private JTextField queueTextField;

    ///panel for the number of persons in queue
    private JPanel peoplePanel;
    private JLabel peopleLabel;
    private JTextField peopleTextField;

    ///arrival time panels
    private JPanel arrivalTimePanel;
    private JLabel arrivalTimeLabel;

    ///panel for min arrival time
    private JPanel minArrivalTimePanel;
    private JLabel minArrivalTimeLabel;
    private JTextField minArrivalTimeTextField;

    ///panel for max arrival time
    private JPanel maxArrivalTimePanel;
    private JLabel maxArrivalTimeLabel;
    private JTextField maxArrivalTimeTextField;

    ///service time panels
    private JPanel serviceTimePanel;
    private JLabel serviceTimeLabel;

    ///panel for min Service time
    private JPanel minServiceTimePanel;
    private JLabel minServiceTimeLabel;
    private JTextField minServiceTimeTextField;

    ///panel for max Service time
    private JPanel maxServiceTimePanel;
    private JLabel maxServiceTimeLabel;
    private JTextField maxServiceTimeTextField;

    ///panel for the simulation result
    private JPanel simulationPanel;
    private JLabel simulationLabel;
    private JTextArea simulationTextArea;
    private JScrollPane scrollPane;

    ///buton compute
    private JButton computeJButton;

    ///controller
    Controller controller = new Controller(this);

    public View(String name){
        super(name);
        prepareGui();
    }

    public void prepareGui(){
        ///1280 x 720
        this.setSize(720, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.contentPanel = new JPanel(new GridLayout(1, 2));
        this.prepareImputData();
        this.prepareSimulationResults();
        this.setContentPane(this.contentPanel);
    }

    public void prepareImputData(){

        ///imput setup
        this.imputPanel = new JPanel();
        this.imputPanel.setLayout(new BoxLayout(this.imputPanel, BoxLayout.PAGE_AXIS));

        ///time
        this.timePanel = new JPanel();
        this.timePanel.setLayout(new BoxLayout(this.timePanel, BoxLayout.PAGE_AXIS));
        this.timeLabel = new JLabel("Time of simulation", JLabel.LEFT);
        this.timeTextField = new JTextField();
        this.timeTextField.setPreferredSize(new Dimension(100, 20));
        this.timePanel.add(this.timeLabel);
        this.timePanel.add(this.timeTextField);

        ///queues
        this.queuePanel = new JPanel();
        this.queuePanel.setLayout(new BoxLayout(this.queuePanel, BoxLayout.PAGE_AXIS));
        this.queueLabel = new JLabel("Number Of Queues", JLabel.LEFT);
        this.queueTextField = new JTextField();
        this.queueTextField.setPreferredSize(new Dimension(100, 20));
        this.queuePanel.add(this.queueLabel);
        this.queuePanel.add(this.queueTextField);

        ///people
        this.peoplePanel = new JPanel();
        this.peoplePanel.setLayout(new BoxLayout(this.peoplePanel, BoxLayout.PAGE_AXIS));
        this.peopleLabel = new JLabel("Number Of People in Queue", JLabel.LEFT);
        this.peopleTextField = new JTextField();
        this.peopleTextField.setPreferredSize(new Dimension(200, 40));
        this.peoplePanel.add(this.peopleLabel);
        this.peoplePanel.add(this.peopleTextField);

        ///arrival time min
        this.minArrivalTimePanel = new JPanel();
        this.minArrivalTimePanel.setLayout(new BoxLayout(this.minArrivalTimePanel, BoxLayout.PAGE_AXIS));
        this.minArrivalTimeLabel = new JLabel("Minimum time of arrival", JLabel.LEFT);
        this.minArrivalTimeTextField = new JTextField();
        this.minArrivalTimeTextField.setPreferredSize(new Dimension(200, 40));
        this.minArrivalTimePanel.add(this.minArrivalTimeLabel);
        this.minArrivalTimePanel.add(this.minArrivalTimeTextField);

        ///arrival time max
        this.maxArrivalTimePanel = new JPanel();
        this.maxArrivalTimePanel.setLayout(new BoxLayout(this.maxArrivalTimePanel, BoxLayout.PAGE_AXIS));
        this.maxArrivalTimeLabel = new JLabel("Maximum time of arrival", JLabel.LEFT);
        this.maxArrivalTimeTextField = new JTextField();
        this.maxArrivalTimeTextField.setPreferredSize(new Dimension(200, 40));
        this.maxArrivalTimePanel.add(this.maxArrivalTimeLabel);
        this.maxArrivalTimePanel.add(this.maxArrivalTimeTextField);

        ///arrival time
        this.arrivalTimePanel = new JPanel();
        this.arrivalTimePanel.setLayout(new BoxLayout(this.arrivalTimePanel, BoxLayout.PAGE_AXIS));
        this.arrivalTimeLabel = new JLabel("Time of arrival", JLabel.LEFT);
        this.arrivalTimePanel.add(this.arrivalTimeLabel);
        this.arrivalTimePanel.add(this.minArrivalTimePanel);
        this.arrivalTimePanel.add(this.maxArrivalTimePanel);

        ///service time min
        this.minServiceTimePanel = new JPanel();
        this.minServiceTimePanel.setLayout(new BoxLayout(this.minServiceTimePanel, BoxLayout.PAGE_AXIS));
        this.minServiceTimeLabel = new JLabel("Minimum time of service", JLabel.LEFT);
        this.minServiceTimeTextField = new JTextField();
        this.minServiceTimeTextField.setPreferredSize(new Dimension(200, 40));
        this.minServiceTimePanel.add(this.minServiceTimeLabel);
        this.minServiceTimePanel.add(this.minServiceTimeTextField);

        ///service time max
        this.maxServiceTimePanel = new JPanel();
        this.maxServiceTimePanel.setLayout(new BoxLayout(this.maxServiceTimePanel, BoxLayout.PAGE_AXIS));
        this.maxServiceTimeLabel = new JLabel("Maximum time of service", JLabel.LEFT);
        this.maxServiceTimeTextField = new JTextField();
        this.maxServiceTimeTextField.setPreferredSize(new Dimension(200, 40));
        this.maxServiceTimePanel.add(this.maxServiceTimeLabel);
        this.maxServiceTimePanel.add(this.maxServiceTimeTextField);

        ///service time
        this.serviceTimePanel = new JPanel();
        this.serviceTimePanel.setLayout(new BoxLayout(this.serviceTimePanel, BoxLayout.PAGE_AXIS));
        this.serviceTimeLabel = new JLabel("Time of service", JLabel.LEFT);
        this.serviceTimePanel.add(this.serviceTimeLabel);
        this.serviceTimePanel.add(this.minServiceTimePanel);
        this.serviceTimePanel.add(this.maxServiceTimePanel);

        ///initialize Button
        this.computeJButton = new JButton("Run Simulation");
        this.computeJButton.setActionCommand("Run Simulation");
        this.computeJButton.addActionListener(this.controller);

        ///add to the main panel the smaller panels
        this.imputPanel.add(timePanel);
        this.imputPanel.add(queuePanel);
        this.imputPanel.add(peoplePanel);
        this.imputPanel.add(arrivalTimePanel);
        this.imputPanel.add(serviceTimePanel);

        this.imputPanel.add(computeJButton);

        this.contentPanel.add(imputPanel);

    }

    public void prepareSimulationResults(){

        this.simulationPanel = new JPanel();
        this.simulationPanel.setLayout(new BoxLayout(this.simulationPanel, BoxLayout.PAGE_AXIS));
        this.simulationLabel = new JLabel("Simulation Results:", JLabel.CENTER);
        this.simulationTextArea = new JTextArea(10000, 10000);
        this.scrollPane = new JScrollPane(simulationTextArea);
        this.scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.simulationTextArea.setPreferredSize(new Dimension(500,500));
        this.simulationTextArea.setEditable(false);
        this.simulationPanel.add(simulationLabel);
        this.simulationPanel.add(scrollPane);

        this.contentPanel.add(simulationPanel);
    }

    public JTextField getQueueTextField() {
        return queueTextField;
    }

    public void setQueueTextField(JTextField queueTextField) {
        this.queueTextField = queueTextField;
    }

    public JTextField getPeopleTextField() {
        return peopleTextField;
    }

    public void setPeopleTextField(JTextField peopleTextField) {
        this.peopleTextField = peopleTextField;
    }

    public JTextField getMinArrivalTimeTextField() {
        return minArrivalTimeTextField;
    }

    public void setMinArrivalTimeTextField(JTextField minArrivalTimeTextField) {
        this.minArrivalTimeTextField = minArrivalTimeTextField;
    }

    public JTextField getMaxArrivalTimeTextField() {
        return maxArrivalTimeTextField;
    }

    public void setMaxArrivalTimeTextField(JTextField maxArrivalTimeTextField) {
        this.maxArrivalTimeTextField = maxArrivalTimeTextField;
    }

    public JTextField getMinServiceTimeTextField() {
        return minServiceTimeTextField;
    }

    public void setMinServiceTimeTextField(JTextField minServiceTimeTextField) {
        this.minServiceTimeTextField = minServiceTimeTextField;
    }

    public JTextField getMaxServiceTimeTextField() {
        return maxServiceTimeTextField;
    }

    public void setMaxServiceTimeTextField(JTextField maxServiceTimeTextField) {
        this.maxServiceTimeTextField = maxServiceTimeTextField;
    }

    public JTextArea getSimulationTextArea() {
        return simulationTextArea;
    }

    public void setSimulationTextArea(JTextArea simulationTextArea) {
        this.simulationTextArea = simulationTextArea;
    }

    public JTextField getTimeTextField() {
        return timeTextField;
    }

    public void setTimeTextField(JTextField timeTextField) {
        this.timeTextField = timeTextField;
    }
}
