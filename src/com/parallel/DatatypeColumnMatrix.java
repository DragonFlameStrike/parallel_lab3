package com.parallel;

public class DatatypeColumnMatrix extends Datatype{
    private int start;
    private Matrix partMatrix;
    @Override
    public Datatype createDatatype(Matrix matrix, int datatypeNumber, int weight, int hight, int countOfElements) {
        partMatrix = new Matrix(weight,hight);
        this.start = datatypeNumber*weight;
        for (int element = 0, elementsInLine = 0,line = 0; element < countOfElements; element++, elementsInLine++) {
            if(elementsInLine == weight){
                elementsInLine = 0;
                line++;
            }
            int index = matrix.getWeight() * line + elementsInLine + this.start;
            partMatrix.pullElement(matrix.getElement(index));
        }
        return this;
    }
    @Override
    public Matrix getPartMatrix() {
        return partMatrix;
    }
}
