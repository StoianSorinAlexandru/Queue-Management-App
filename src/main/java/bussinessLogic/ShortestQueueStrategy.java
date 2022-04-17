package bussinessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;

public class ShortestQueueStrategy implements Strategy{

    public void addTask(ArrayList<Server> servers, Task task){
        int Min = servers.get(0).getSizeOfQueue();
        Server auxServer = servers.get(0);
        for(Server s : servers){
            if(Min > s.getSizeOfQueue()){
                auxServer = s;
                Min = s.getSizeOfQueue();
            }
        }
        auxServer.addTask(task);
    }

}
