package com.parallel;

import java.util.ArrayList;
import java.util.List;


public class Matrix {
    private ArrayList<Double> matrix;
    private int hight;
    private int weight;

    public Matrix(int hight, int weight) {
        this.matrix = new ArrayList<>();
        this.hight = hight;
        this.weight = weight;
    }

    public void initA() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                //matrix.add((double)i*hight+j);
                matrix.add((double)i);
            }
        }
    }

    public void initB() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                //matrix.add((double)i*hight+j);
                matrix.add((double)j);
            }
        }
    }

    public void print() {
        for (int line = 0; line < hight; line++) {
            System.out.println(matrix.subList(line * weight, (line + 1) * weight));
        }
    }

    public void pullElement(double element) {
        matrix.add(element);
    }
    public void setElement(int index ,double element) {
        matrix.set(index,element);
    }
    public double getElement(int index){
        return matrix.get(index);
    }

    public int size() {
        return matrix.size();
    }

    public int getWeight() {
        return weight;
    }

    public int getHight() {
        return hight;
    }

    public void initResult() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                matrix.add(0.0);
            }
        }
    }
}
