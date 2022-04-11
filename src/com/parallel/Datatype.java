package com.parallel;


public abstract class Datatype {
    public Datatype() {}
    /**
     *
     * @param matrix Исходная матрица откуда берется срез
     * @param datatypeNumber Порядковый номер среза в матрице
     * @param countOfElements Количество элементов в срезе
     * @return new Datatype
     */
    public abstract Datatype createDatatype(Matrix matrix, int datatypeNumber, int countOfElements);
    public abstract Matrix getPartMatrix();
}
