package com.parallel;

import java.util.Objects;

class Worker implements Runnable, Process {
    Thread t;
    Matrix partA;
    Matrix partB;
    Matrix result;
    Root root;
    int workerNumber;
    Worker(int workerNumber) {
        this.workerNumber = workerNumber;
    }

    @Override
    public void run() {
        if(-1 == mul(partA, partB)) {
            System.out.println(workerNumber + " worker have a problem");
        }
        this.root.resume();
    }
    @Override
    public void start() {

        //System.out.println("Запуск " + workerNumber + " worker'а");
        if (t == null) {
            t = new Thread(this, String.valueOf(workerNumber));
            t.start();
        }
    }
    public void execute(Root root){
        this.start();
        this.root = root;
    }

    double mul(Matrix x, Matrix y) {
        int len;
        if (x.getWeight() == y.getHight()) len = x.getWeight();
        else return -1;
        int xWeight = x.getWeight();
        int xHight = x.getHight();
        int yWeight = y.getWeight();
        result = new Matrix(xHight, yWeight);
        for (int currXRow = 0; currXRow < xHight; currXRow++) {
            for (int currYColumn = 0; currYColumn < yWeight; currYColumn++) {
                int sum = 0;
                for (int i = 0; i < len; i++) {
                    sum += x.getElement(currXRow * xWeight + i) * y.getElement(i * yWeight + currYColumn);
                }
                result.pullElement(sum);
            }
        }
        return 0;
    }

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
    public void sendDatatypeToRoot(int startWorkerIndex,Datatype datatype, String rcvBuffer, Commutator commutator) {
        if (this.workerNumber == 0) {
            commutator.sendData(-1,datatype,rcvBuffer,startWorkerIndex);
        }
        else{
            Worker nextWorker = commutator.getNextWorkerToReachGoal(this.workerNumber,0);
            nextWorker.sendDatatypeToRoot(startWorkerIndex,datatype,rcvBuffer,commutator);
        }
    }
}