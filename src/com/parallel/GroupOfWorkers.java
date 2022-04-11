package com.parallel;

import java.util.ArrayList;
import java.util.List;


public class GroupOfWorkers {
    private ArrayList<Worker> workers;

    public GroupOfWorkers(List<Process> workers) {
        this.workers = new ArrayList<>();
        for (int i = 0; i < workers.size(); i++) {
            this.workers.add((Worker)workers.get(i));
        }
    }
    public GroupOfWorkers(ArrayList<Worker> workers) {
        this.workers = new ArrayList<>();
        for (int i = 0; i < workers.size(); i++) {
            this.workers.add(workers.get(i));
        }
    }

    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public ArrayList<Worker> subList(int startIndex,int finishIndex) {
        ArrayList<Worker> subWorkers  = new ArrayList<>();
        for (int numWorker = startIndex; numWorker < finishIndex; numWorker++) {
            subWorkers.add(workers.get(numWorker));
        }
        return subWorkers;
    }

    public ArrayList<Worker> subColumn(int startIndex,int offset) {
        ArrayList<Worker> subWorkers  = new ArrayList<>();
        for (int numWorker = startIndex; numWorker < workers.size(); numWorker++) {
            if(numWorker%offset == 0){
                subWorkers.add(workers.get(numWorker));
            }
        }
        return subWorkers;
    }
    public void start(){
        for (Worker worker:
                workers) {
            worker.start();
        }
    }

    public Worker getWorker(int index) {
        return workers.get(index);
    }

    public int size() {
        return workers.size();
    }

    public GroupOfWorkers getSubGroupOfWorkers(int count, int offset, int index) {
        int startIndex = 1+index*(count+offset);
        int finishIndex = startIndex+count;
        ArrayList<Worker> subWorkers = this.subList(startIndex,finishIndex);
        GroupOfWorkers subGroupOfWorkers = new GroupOfWorkers(subWorkers);
        return subGroupOfWorkers;
    }
}
