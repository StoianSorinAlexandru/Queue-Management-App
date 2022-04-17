package model;

public class Task {

    private int id;
    private int arrivalTime;
    private int serviceTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int compareTo(Task task){
        return this.arrivalTime - task.arrivalTime;
    }

    @Override
    public String toString() {
        return "(" +
                this.getId()  +
                ", " +
                this.getArrivalTime()  +
                ", " +
                this.getServiceTime()  +
                ")";
    }
}
