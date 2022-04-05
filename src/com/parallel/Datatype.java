package com.parallel;


public abstract class Datatype {
    public Datatype() {}
    /**
     *
     * @param matrix Исходная матрица откуда берется срез
     * @param datatypeNumber Порядковый номер среза в матрице
     * @param weight Ширина среза
     * @param hight Высота среза
     * @param countOfElements Количество элементов в срезе
     */
    public abstract Datatype createDatatype(Matrix matrix, int datatypeNumber, int weight, int hight, int countOfElements);
    public abstract Matrix getPartMatrix();
}
