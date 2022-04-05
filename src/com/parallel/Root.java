package com.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.parallel.Main.*;


public class Root implements Process,Runnable{
    private Thread t;
    private int countResume;
    private boolean wait;
    private Matrix A;
    private Matrix B;
    private Matrix result;
    private GroupOfWorkers workers = null;
    Root() {
        countResume = 0;
        result = new Matrix(n1,n3);
        A = new Matrix(n1,n2);
        B = new Matrix(n2,n3);
        A.initA();
        B.initB();
        A.print();
    }
    @Override
    public void run() {
        GroupOfWorkers firstColumnWorkers = new GroupOfWorkers(workers.subColumn(0,p2));
        GroupOfWorkers firstRowWorkers = new GroupOfWorkers(workers.subList(0, p2));
        new Scatter().execution(A,1,new Double(0.0),firstColumnWorkers,1,new Double(0.0));
        new Scatter().execution(B,1,new Datatype(),firstRowWorkers,1,new Double(0.0));
        workers.start();
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
        new Gather().execution(result,1,new Datatype(),workers,1,new Datatype());
        result.print();
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


    public void setWorkers(List<Process> workers) {
        this.workers = new GroupOfWorkers(workers);
    }
}
