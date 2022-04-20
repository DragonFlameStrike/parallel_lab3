package com.parallel;

import static com.parallel.Main.*;

public class DatatypeRowMatrix extends Datatype {
    private int start;
    private Matrix partMatrix;
    private int weight;
    private int hight;

    @Override
    public Datatype createDatatype(Matrix matrix, int datatypeNumber,int countOfElements) {
        DatatypeRowMatrix currDatatype = new DatatypeRowMatrix();
        currDatatype.createMatrix(matrix,datatypeNumber,countOfElements);
        return currDatatype;
    }
    @Override
    public Matrix getPartMatrix() {
        return partMatrix;
    }
    private void createMatrix(Matrix matrix, int datatypeNumber, int countOfElements){
        weight = matrix.getWeight();
        hight = matrix.getHight()/p1;
        partMatrix = new Matrix(hight, weight);
        this.start = datatypeNumber * weight * hight;
        for (int i = 0; i < countOfElements; i++) {
            partMatrix.pullElement(matrix.getElement(i + this.start));
        }
    }
}
