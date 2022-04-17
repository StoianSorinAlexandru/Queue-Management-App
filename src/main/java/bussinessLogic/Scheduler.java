package bussinessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;

public class Scheduler {

    public ArrayList<Server> servers;
    public ArrayList<Thread> threads;
    private int maxNoServer;
    private int maxTasksPerServer;
    private Strategy strategy;

    public void RunThreads(){
        for(Thread t : threads)
            t.start();
    }

    public Scheduler(int maxNoServer, int maxTasksPerServer){
        servers = new ArrayList<>();
        threads = new ArrayList<>();
        strategy = new TimeStrategy();
        for(int i = 0; i < maxNoServer; ++i){
            Server s = new Server();
            this.servers.add(s);
            this.threads.add(new Thread(s));
        }
    }

    public void changeStrategy(SelectionPolicy selectionPolicy){
        if(selectionPolicy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ShortestQueueStrategy();
        }
        if(selectionPolicy == SelectionPolicy.SHORTEST_TIME){
            strategy = new TimeStrategy();
        }
    }

    public void dispatchTask(Task task){
        strategy.addTask(servers, task);
    }

    public ArrayList<Server> getServers(){
        return servers;
    }

}
