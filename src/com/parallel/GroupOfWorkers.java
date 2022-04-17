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

    public ArrayList<Worker> subList(int startIndex,int count,int offset,int total) {
        ArrayList<Worker> subWorkers  = new ArrayList<>();
        for (int numWorker = startIndex,countOfElement = 0; countOfElement < total; numWorker+=offset) {
            for (int i = 0; i < count; i++) {
                subWorkers.add(workers.get(numWorker));
                numWorker++;
                countOfElement++;
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

    public GroupOfWorkers getSubGroupOfWorkers(int startOffset, int count, int offset, int sizeSubGroup) {
        int startIndex = startOffset;
        ArrayList<Worker> subWorkers = this.subList(startIndex,count,offset,sizeSubGroup);
        GroupOfWorkers subGroupOfWorkers = new GroupOfWorkers(subWorkers);
        return subGroupOfWorkers;
    }
}
