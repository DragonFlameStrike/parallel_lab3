package com.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.parallel.Main.*;


public class Root implements Process,Runnable{
    private Thread t;
    private int countResume;
    private boolean wait;
    private Vector<Double> A;
    private Vector<Double> B;
    private ArrayList<Datatype> result;
    private ArrayList<Datatype> datatypesA;
    private ArrayList<Datatype> datatypesB;
    private List<Process> workers = new ArrayList<>();
    Root() {
        countResume = 0;
        result = new ArrayList<>();
        A = new Vector<>();
        B = new Vector<>();
        initA(A);
        initB(B);

        datatypesA = new DataManager(A,n1,n2,p1,0).getDatatypes();
        datatypesB = new DataManager(B,n2,n3,p2,1).getDatatypes();

    }
    @Override
    public void run() {
        new Sender().execution(datatypesA,workers);
        new Sender().execution(datatypesB,workers);
        for (Process worker:
             workers) {
            worker.start();
        }
        synchronized (this) {
            wait = true;
            while (wait) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        new Gather().execution(result,workers);
    }
    @Override
    public void start() {
        //System.out.println("Запуск " + currThread);
        if (t == null) {
            t = new Thread(this, String.valueOf("root"));
            t.start();
        }
    }

    public synchronized void resume() {
        countResume++;
        if (countResume == NumberOfThreads) {
            wait = false;
            notify();
            countResume=0;
        }
    }

    private void initA(Vector<Double> matrixA) {
        for (int i = 0; i < n2; i++) {
            for (int j = 0; j < n1; j++) {
                matrixA.add(2.0);
            }
        }
    }

    private void initB(Vector<Double> matrixB) {
        for (int i = 0; i < n3; i++) {
            for (int j = 0; j < n2; j++) {
                matrixB.add(3.0);
            }
        }
    }

    public void setWorkers(List<Process> workers) {
        this.workers = workers;
    }
}
