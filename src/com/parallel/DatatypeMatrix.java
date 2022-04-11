package com.parallel;

import static com.parallel.Main.p1;

public class DatatypeMatrix extends Datatype{
    private int start;
    private Matrix partMatrix;
    private int weight;
    private int hight;
    @Override
    public Datatype createDatatype(Matrix matrix, int datatypeNumber, int countOfElements) {
        DatatypeMatrix currDatatype = new DatatypeMatrix();
        currDatatype.createMatrix(matrix,datatypeNumber,countOfElements);
        return currDatatype;
    }

    private void createMatrix(Matrix matrix, int datatypeNumber, int countOfElements) {
        weight = matrix.getWeight();
        hight = matrix.getHight();
        partMatrix = new Matrix(weight, hight);
        this.start = datatypeNumber * weight * hight;
        for (int i = 0; i < countOfElements; i++) {
            partMatrix.pullElement(matrix.getElement(i + this.start));
        }
    }

    @Override
    public Matrix getPartMatrix() {
        return partMatrix;
    }
}
