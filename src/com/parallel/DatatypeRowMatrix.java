package com.parallel;

public class DatatypeRowMatrix extends Datatype {
    private int start;
    private Matrix partMatrix;

    @Override
    public Datatype createDatatype(Matrix matrix, int datatypeNumber, int weight, int hight, int countOfElements) {
        partMatrix = new Matrix(weight, hight);
        this.start = datatypeNumber * weight * hight;
        for (int i = 0; i < countOfElements; i++) {
            partMatrix.pullElement(matrix.getElement(i + this.start));
        }
        return this;
    }

    @Override
    public Matrix getPartMatrix() {
        return partMatrix;
    }
}
