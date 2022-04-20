package com.parallel;

import java.util.ArrayList;
import java.util.List;



public class Main {
    static final int p1 = 4;
    static final int p2 = 4;
    static final int n1 = 4;
    static final int n2 = 8;
    static final int n3 = 4;
    static final int NumberOfThreads = p1 * p2;
    static final int root = 0;

    public static void main(String[] args) {
        ArrayList<Process> processes = new ArrayList<>();
        processes.add(new Root());
        for (int processNumber = 0; processNumber < NumberOfThreads; processNumber++) {
            processes.add(new Worker(processNumber));
        }
        ((Root) processes.get(root)).setWorkers(processes.subList(1,processes.size()));
        processes.get(root).start();
    }

}

