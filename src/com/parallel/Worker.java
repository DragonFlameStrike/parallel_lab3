package com.parallel;

import java.util.Vector;

import static com.parallel.Main.n2;
import static com.parallel.Main.n1;
import static com.parallel.Main.n3;
import static com.parallel.Main.p2;
import static com.parallel.Main.p1;

class Worker implements Runnable, Process {
    Thread t;
    Datatype partA;
    Datatype partB;
    Vector<Double> result;
    int workerNumber;


    Worker(int workerNumber) {
        this.workerNumber = workerNumber;
        result = new Vector<>();

        result = new Vector<>();
        partA = new Datatype();
        partB = new Datatype();

    }

    @Override
    public void run() {
        //mul(partA, partB);
    }
    @Override
    public void start() {
        System.out.println("Запуск ");
        if (t == null) {
            t = new Thread(this, String.valueOf(workerNumber));
            t.start();
        }
    }

//    double mul(Datatype x, Datatype y) {
////        double sum = 0;
////        for (int i = 0; i < x.size(); i++) {
////            sum += x.get(i) * y.get(i);
////        }
////        return sum;
//    }

    Vector<Double> getResult() {
        return result;
    }

}