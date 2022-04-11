package com.parallel;

import java.util.ArrayList;
import java.util.List;



public class Main {
    static final int p1 = 2;
    static final int p2 = 2;
    static final int n1 = 8;
    static final int n2 = 8;
    static final int n3 = 8;
    static final int NumberOfThreads = p1 * p2;
    static final int root = 0;

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Root());
        for (int processNumber = 0; processNumber < NumberOfThreads; processNumber++) {
            processes.add(new Worker(processNumber));
        }
        for (int workerNumber = 0,workerIndex = 1; workerNumber < NumberOfThreads; workerNumber++,workerIndex++) {
            List<Process> neighboursList = new ArrayList<>();
            if(workerNumber - p2 < 0) { neighboursList.add(null); } else { neighboursList.add(processes.get(workerIndex - p2));}
            if(workerNumber % p2 == p2 - 1) { neighboursList.add(null); } else { neighboursList.add(processes.get(workerIndex + 1));}
            if(workerNumber + p2 >= NumberOfThreads) { neighboursList.add(null); } else { neighboursList.add(processes.get(workerIndex + p2));}
            if(workerNumber % p2 == 0) { neighboursList.add(null); } else { neighboursList.add(processes.get(workerIndex - 1));}
            GroupOfWorkers neighboursGroup = new GroupOfWorkers(neighboursList);
            ((Worker)processes.get(workerIndex)).setNeighbours(neighboursGroup);
        } // init neighbours in every worker
        ((Root) processes.get(root)).setWorkers(processes.subList(1,processes.size()));
        processes.get(root).start();
    }

}

