package com.parallel;

import java.util.Objects;

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

    public void setPartMatrix(Datatype datatype, String rcvBuffer) {
        Matrix partMatrix = datatype.getPartMatrix();
        if (Objects.equals(rcvBuffer, "partA")) this.partA = partMatrix;
        if (Objects.equals(rcvBuffer, "partB")) this.partB = partMatrix;
    }

    public Matrix getPartMatrix(String buf) {
        if(Objects.equals(buf, "partA")) return partA;
        if(Objects.equals(buf, "partB")) return partB;
        return null;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }

    public void sendDatatype(int where, Datatype datatype, boolean saveToCurr, boolean saveToEveryWorker, String rcvBuffer, Commutator commutator) {
        if (saveToCurr || where==this.workerNumber) {
            Datatype newDatatype = null;
            try {
                newDatatype = datatype.clone();
            } catch (CloneNotSupportedException e) {
                System.out.println("Clone Exeption");
            }
            if (newDatatype != null) {
                setPartMatrix(newDatatype, rcvBuffer);
            }
        }
        if(where!=this.workerNumber) {
            Worker nextWorker = commutator.getNextWorkerToReachGoal(this.workerNumber,where);
            nextWorker.sendDatatype(where, datatype, saveToEveryWorker, saveToEveryWorker, rcvBuffer, commutator);
        }
    }
}