package com.parallel;

class Worker implements Runnable, Process {
    Thread t;
    Matrix partA;
    Matrix partB;
    Matrix result;
    int workerNumber;
    GroupOfWorkers neighbours;
    //     0
    //   3 x 1
    //     2
    // x == this worker
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

    public void setNeighbours(GroupOfWorkers neighbours){
        this.neighbours = neighbours;
    }

    public void setPartA(Matrix partA) {
        for (int i = 0; i < partA.size(); i++) {
            if(this.partA == null) this.partA = new Matrix(partA.getWeight(),partA.getHight());
            this.partA.pullElement(partA.getElement(i));
        }
    }

    public void setPartB(Matrix partB) {
        for (int i = 0; i < partB.size(); i++) {
            if(this.partB == null) this.partB = new Matrix(partB.getWeight(),partB.getHight());
            this.partB.pullElement(partB.getElement(i));
        }
    }

    public void sendData(int diraction, int sendcount) {
        sendcount--;
        if(sendcount>0){
            if(diraction == 0){
                neighbours.getWorker(1).setPartA(partA);
                neighbours.getWorker(1).sendData(diraction,sendcount);
            }
            if(diraction == 1){
                neighbours.getWorker(2).setPartB(partB);
                neighbours.getWorker(2).sendData(diraction,sendcount);
            }
        }
    }
}