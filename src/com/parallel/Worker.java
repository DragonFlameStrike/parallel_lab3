package com.parallel;

import java.util.Objects;

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

//    public void setPartA(Matrix partA) {
//        for (int i = 0; i < firstPart.size(); i++) {
//            if(this.firstPart == null) this.firstPart = new Matrix(firstPart.getWeight(), firstPart.getHight());
//            this.firstPart.pullElement(firstPart.getElement(i));
//        }
//    }
//
//    public void setPartB(Matrix partB) {
//        for (int i = 0; i < secondPart.size(); i++) {
//            if(this.secondPart == null) this.secondPart = new Matrix(secondPart.getWeight(), secondPart.getHight());
//            this.secondPart.pullElement(secondPart.getElement(i));
//        }
//    }

//    public void sendData(int diraction, int sendcount) {
//        sendcount--;
//        if(sendcount>0){
//            if(diraction == 0){
//                neighbours.getWorker(1).setPartA(partA);
//                neighbours.getWorker(1).sendData(diraction,sendcount);
//            }
//            if(diraction == 1){
//                neighbours.getWorker(2).setPartB(partB);
//                neighbours.getWorker(2).sendData(diraction,sendcount);
//            }
//        }
//    }

    public void setDatatype(Datatype datatype,String rcvBuffer) {
        Matrix partMatrix = datatype.getPartMatrix();
        if (Objects.equals(rcvBuffer, "partA")) this.partA = partMatrix;
        if (Objects.equals(rcvBuffer, "partB")) this.partB = partMatrix;
    }

    public Matrix getPartMatrix(String buf) {
        if(Objects.equals(buf, "partA")) return partA;
        if(Objects.equals(buf, "partB")) return partB;
        return null;
    }
}