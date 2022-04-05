package com.parallel;

import java.io.Serializable;
import java.util.ArrayList;

public class Datatype implements Serializable {
    private int start;
    private Matrix partMatrix;

    /**
     *
     * @param matrix Исходная матрица откуда берется срез
     * @param datatypeNumber Порядковый номер среза в матрице
     * @param weight Ширина среза
     * @param hight Высота среза
     * @param countOfElements Количество элементов в срезе
     */
    public Datatype(Matrix matrix, int datatypeNumber, int weight, int hight, int countOfElements) {
        partMatrix = new Matrix(weight,hight);
        if(matrix.getWeight() == weight){
            this.start = datatypeNumber*weight*hight;
            for (int i = 0; i < countOfElements; i++) {
                partMatrix.pullElement(matrix.getElement(i+this.start));
            }
        }
        else {
            this.start = datatypeNumber*weight;
            for (int element = 0, elementsInLine = 0,line = 0; element < countOfElements; element++, elementsInLine++) {
                if(elementsInLine == weight){
                    elementsInLine = 0;
                    line++;
                }
                int index = matrix.getWeight() * line + elementsInLine + this.start;
                partMatrix.pullElement(matrix.getElement(index));
            }
        }

    }
    public Datatype() {
    }

    public Matrix getPartMatrix() {
        return partMatrix;
    }
}
