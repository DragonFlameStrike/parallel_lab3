package com.parallel;

import java.util.ArrayList;


public class Matrix {
    private ArrayList<Double> matrix;
    private int hight;
    private int weight;

    public Matrix(int weight, int hight) {
        this.matrix = new ArrayList<>();
        this.hight = hight;
        this.weight = weight;
    }

    public void initA() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                matrix.add((double)i*hight+j);
            }
        }
    }

    public void initB() {
        for (int i = 0; i < hight; i++) {
            for (int j = 0; j < weight; j++) {
                matrix.add((double)i*hight+j);
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
}
