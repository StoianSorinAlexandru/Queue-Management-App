package bussinessLogic;

import gui.View;

import model.Server;
import model.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.max;

public class SimulationManager implements Runnable{

    private int timeLimit;
    private int numberOfServers;
    private int numberOfClients;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiveTime;
    private int maxServiveTime;

    private Double averageWaitingTime;
    private Double averageServiceTime;
    private int peakHour;

    private View view;
    private SelectionPolicy selectionPolicy;

    private String stringAtTime;
    private String totalString;

    private ArrayList<Task> taskArrayList;
    private Scheduler scheduler;

    public SimulationManager(View view, int timeLimit, int numberOfServers, int numberOfClients, int minArrivalTime, int maxArrivalTime, int minServiveTime, int maxServiveTime) {

        this.selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        this.view = view;
        this.averageWaitingTime = 0.0;
        this.timeLimit = timeLimit;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiveTime = minServiveTime;
        this.maxServiveTime = maxServiveTime;
        this.scheduler = new Scheduler(numberOfServers, numberOfClients);
        generateRandomTasks();
    }

    public void generateRandomTasks(){
        taskArrayList = new ArrayList<>();
        int sum = 0;
        int waitingTime = 0;
        for(int i=0; i<numberOfClients; ++i){
            Task task = new Task();
            task.setId(i + 1);
            task.setArrivalTime(minArrivalTime + (int)(Math.random() * ((maxArrivalTime - minArrivalTime) + 1)));
            task.setServiceTime(minServiveTime + (int)(Math.random() * ((maxServiveTime - minServiveTime) + 1)));
            sum = sum + task.getServiceTime();
            waitingTime = waitingTime + task.getServiceTime();
            taskArrayList.add(task);
        }

        averageWaitingTime = Double.valueOf(waitingTime / numberOfClients);
        averageServiceTime = Double.valueOf(sum / numberOfClients);

        taskArrayList.sort(Task::compareTo);
    }

    public void showStats(){
        for(Task t : taskArrayList)
            System.out.println(t.toString());
        for(Server q : scheduler.servers){
            System.out.println(q.toString() + '\n');
        }

    }

    public String getStats(){
        String string = new String();
        string = string + "Waiting Clients:\n";
        for(Task t : taskArrayList)
            string = string + " " + t.toString();
        string = string + "\n\n";
        int i=1;
        for(Server s : scheduler.servers) {
            if(s.toString().isEmpty())
                string = string + "Queue " + i + ":   EMPTY!\n";
            else
                string = string + "Queue " + i + ": " + s.toString() + "\n";
            ++i;
        }
        string = string + "----------------------------------------------------------------\n\n";
        return string;
    }

    public boolean checkQueues(){

        boolean ok = false;

        for(Server q : scheduler.servers)
            if(q.getSizeOfQueue() != 0){
                ok = true;
            }

        if(!taskArrayList.isEmpty())
            ok = true;

        return ok;
    }

    public void printLogs(String string){

        try {
            File myObj = new File("simulation.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter myWriter = new FileWriter("simulation.txt");
            string = string + "Average Waiting Time: " + averageWaitingTime + "\nAverage Service Time: " + averageServiceTime + "\nPeak Hour: " + peakHour + "\n";
            myWriter.write(string);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        int currentTime = 0;
        int maxTaskPerHour = 0;
        totalString = new String();
        scheduler.RunThreads();
        while (currentTime < timeLimit && checkQueues()){
            currentTime++;
            String string = new String("----------------------------------------------------------------\n\nTime: " + currentTime + "\n");
            ArrayList<Task> aux = new ArrayList<>();
            for(Task t : taskArrayList){
                if(t.getArrivalTime() == currentTime) {
                    scheduler.dispatchTask(t);
                }
                else aux.add(t);
            }
            taskArrayList = aux;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int numberOfTasks = 0;
            for(Server s : scheduler.servers){
                numberOfTasks = numberOfTasks + s.getSizeOfQueue();
            }
            if(maxTaskPerHour < numberOfTasks){
                maxTaskPerHour = numberOfTasks;
                peakHour = currentTime;
            }
            System.out.println(numberOfTasks);

            //showStats();
            stringAtTime = string + getStats();

            totalString = totalString + stringAtTime;


            view.getSimulationTextArea().setText(totalString);
            System.out.println(stringAtTime);

        }

        printLogs(totalString);
    }


    public String getStringAtTime() {
        return stringAtTime;
    }
}
