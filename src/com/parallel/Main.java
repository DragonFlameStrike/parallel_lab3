package com.parallel;

import java.util.Vector;
public class Main {
    static final int p1 = 2;
    static final int p2 = 2;
    static final int NumberOfThreads = p1 * p2;

    public static void main(String[] args) {

    }

    class Worker implements Runnable {
        Vector<Double> partA;
        Vector<Double> partB;
        double result;
        Worker(Director director,int workerNumber){
            partA = director.getPartA(workerNumber);
            partB = director.getPartB(workerNumber);
        }
        @Override
        public void run() {
            mul(partA,partB);
        }

        double mul(Vector<Double> x, Vector<Double> y) {
            double sum = 0;
            for (int i = 0; i < x.size(); i++) {
                sum += x.get(i) * y.get(i);
            }
            return sum;
        }

        double getResult() {
            return result;
        }
    }

    class Director {
        private Vector<Double> matrixA;
        private Vector<Double> matrixB;
        private Vector<Double> matrixR;
        private int n1;
        private int n2;
        private int n3;

        Director(int n1, int n2, int n3) {
            matrixA = new Vector<>();
            matrixB = new Vector<>();
            matrixR = new Vector<>();
            this.n1 = n1;
            this.n2 = n2;
            this.n3 = n3;
            initA();
            initB();
            initR();
            matrixB = transportB();

        }

        private void initA() {
            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n1; j++) {
                    matrixA.add(2.0);
                }
            }
        }
        private void initB() {
            for (int i = 0; i < n3; i++) {
                for (int j = 0; j < n2; j++) {
                    matrixB.add(3.0);
                }
            }
        }
        private void initR() {
            for (int i = 0; i < p1; i++) {
                for (int j = 0; j < p2; j++) {
                    matrixR.add(0.0);
                }
            }
        }
        private Vector<Double> transportB(){
            Vector<Double> newB = new Vector<>();
            for (int i = 0; i < n3; i++) {
                for (int j = 0; j < n2; j++) {
                    newB.add(matrixB.get(j*n2+i));
                }
            }
            return newB;
        }
        public Vector<Double> getPartA(int workerNumber){
            int startIndex =n2*workerNumber/p1;
            int finishIndex = n2*(workerNumber+1)/p1;
            return (Vector<Double>) matrixA.subList(startIndex,finishIndex);
        }
        public Vector<Double> getPartB(int workerNumber){
            int startIndex =n3*workerNumber/p2;
            int finishIndex = n3*(workerNumber+1)/p2;
            return (Vector<Double>) matrixB.subList(startIndex,finishIndex);


        }
    }
}