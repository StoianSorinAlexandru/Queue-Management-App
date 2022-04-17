package gui;

import bussinessLogic.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    private View view;

    public Controller(View view){this.view = view;}


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String buttonPressed = e.getActionCommand();

        if(buttonPressed == "Run Simulation"){
            //if(true){
            if(isNumeric(view.getTimeTextField().getText()) && isNumeric(view.getQueueTextField().getText()) && isNumeric(view.getPeopleTextField().getText()) && isNumeric(view.getMinArrivalTimeTextField().getText()) && isNumeric(view.getMaxArrivalTimeTextField().getText()) && isNumeric(view.getMinServiceTimeTextField().getText()) && isNumeric(view.getMaxServiceTimeTextField().getText())){
                int timeOfSimulation = Integer.parseInt(view.getTimeTextField().getText());
                int nrOfQueues = Integer.parseInt(view.getQueueTextField().getText());
                int nrOfPeople = Integer.parseInt(view.getPeopleTextField().getText());
                int minArrivalTime = Integer.parseInt(view.getMinArrivalTimeTextField().getText());
                int maxArrivalTime = Integer.parseInt(view.getMaxArrivalTimeTextField().getText());
                int minServiceTime = Integer.parseInt(view.getMinServiceTimeTextField().getText());
                int maxServiveTime = Integer.parseInt(view.getMaxServiceTimeTextField().getText());
                SimulationManager simulationManager = new SimulationManager(view, timeOfSimulation, nrOfQueues, nrOfPeople, minArrivalTime, maxArrivalTime, minServiceTime, maxServiveTime);
                System.out.println(timeOfSimulation + " " +nrOfPeople + " " + nrOfQueues + " " + minArrivalTime + " " + maxArrivalTime + " " + minServiceTime + " " + maxServiveTime);
                //SimulationManager simulationManager = new SimulationManager(this.view,200, 20, 1000, 10,100, 3,9);
                //SimulationManager simulationManager = new SimulationManager(this.view,20, 2, 7, 2,10, 2,4);


                view.getSimulationTextArea().setText(simulationManager.getStringAtTime());
                Thread thread = new Thread(simulationManager);
                thread.start();
            }
            else{
                view.getSimulationTextArea().setText("WRONG IMPUT!");
            }
//



        }

    }
}
