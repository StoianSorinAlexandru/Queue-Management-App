package model;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable{

    private BlockingQueue<Task> blockingQueue;
    private AtomicInteger waitingPeriod;

    public Server(int waitingPeriod){
        this.waitingPeriod = new AtomicInteger(waitingPeriod);
        this.blockingQueue = new LinkedBlockingQueue<>();
    }
    public Server(){
        this.waitingPeriod = new AtomicInteger(0);
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!blockingQueue.isEmpty()) {
                blockingQueue.element().setServiceTime(blockingQueue.element().getServiceTime() - 1);
                waitingPeriod = new AtomicInteger(waitingPeriod.intValue() - 1);
                if (blockingQueue.element().getServiceTime() == 0) {

                    try {
                        blockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }


        }
    }

    public void addTask(Task task){
        blockingQueue.add(task);
        waitingPeriod = new AtomicInteger(waitingPeriod.intValue() + task.getServiceTime());
    }

    public int getIntWaitingTime(){
        return waitingPeriod.intValue();
    }

    public int getSizeOfQueue(){
        return blockingQueue.size();
    }

    public ArrayList<Task> getTasks(){
        ArrayList<Task> arrayList = new ArrayList<>();

        for(Task s : blockingQueue){
            arrayList.add(s);
        }

        return arrayList;
    }

    @Override
    public String toString() {
        String string = new String();

        for (Task t : blockingQueue)
            string = string + "  " + t.toString();

        return string;
    }
}
