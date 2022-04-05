package com.parallel;

class Worker implements Runnable, Process {
    Thread t;
    Matrix partA;
    Matrix partB;
    Matrix result;
    int workerNumber;


    Worker(int workerNumber) {
        this.workerNumber = workerNumber;
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

    Matrix getResult() {
        return result;
    }

    public void setPartA(Matrix partA) {
        this.partA = partA;
    }

    public void setPartB(Matrix partB) {
        this.partB = partB;
    }
}