package com.parallel;

import static com.parallel.Main.*;

public class DatatypeColumnMatrix extends Datatype{
    private int start;
    private Matrix partMatrix;
    private int weight;
    private int hight;
    @Override
    public Datatype createDatatype(Matrix matrix, int datatypeNumber,int countOfElements) {
        DatatypeColumnMatrix currDatatype = new DatatypeColumnMatrix();
        currDatatype.createMatrix(matrix,datatypeNumber,countOfElements);
        return currDatatype;
    }
    @Override
    public Matrix getPartMatrix() {
        return partMatrix;
    }
    private void createMatrix(Matrix matrix, int datatypeNumber, int countOfElements){
        weight = matrix.getWeight()/p2;
        hight = matrix.getHight();
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
    }
}
