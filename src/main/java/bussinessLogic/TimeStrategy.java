package bussinessLogic;

import model.Server;
import model.Task;

import java.util.ArrayList;

public class TimeStrategy implements Strategy{

    public void addTask(ArrayList<Server> servers, Task task){
        int Min = servers.get(0).getIntWaitingTime();
        Server auxServer = servers.get(0);
        for(Server s : servers){
            if(Min > s.getIntWaitingTime()){
                auxServer = s;
                Min = s.getIntWaitingTime();
            }
        }
        auxServer.addTask(task);
    }

}
