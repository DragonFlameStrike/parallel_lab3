package com.parallel;

import java.util.List;

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

    }
    @Override
    public void run() {
        GroupOfWorkers firstColumnWorkers = new GroupOfWorkers(workers.subList(0,1,p2-1,p1));
        GroupOfWorkers firstRowWorkers = new GroupOfWorkers(workers.subList(0,p2,0,p2));
        new Scatter().execution(A,p1,new DatatypeRowMatrix(),firstColumnWorkers,"partA",new ColumnCommutator(workers));
        new Scatter().execution(B,p2,new DatatypeColumnMatrix(),firstRowWorkers,"partB",new RowCommutator(workers));
        new BCast().execution(firstColumnWorkers,"partA",A.size()/p1,new DatatypeMatrix(),new RowCommutator(workers));
        new BCast().execution(firstRowWorkers,"partB",B.size()/p2,new DatatypeMatrix(),new ColumnCommutator(workers));
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
        new Gather().execution(result,1,new DatatypeRowMatrix(),workers,"");
        result.print();
    }


    @Override
    public void start() {
        //System.out.println("Запуск " + currThread);
        if (t == null) {
            t = new Thread(this, "root");
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
