package com.parallel;

import java.util.ArrayList;
import java.util.Vector;

public class DataManager {
    private ArrayList<Datatype> datatypes = new ArrayList<>();
    /**
     *
     * @param matrix matrix
     * @param weight Ширина среза
     * @param hight Высота среза
     * @param p1 process grid hight
     * @param p2 process grid weight
     * @param diraction 0 - horizontal,1 - vertical
     */
    public DataManager(Matrix matrix, int weight, int hight, int p1,int p2,int diraction) {
        if(diraction == 0){
            int countOfElements = matrix.size()/p1;
            for (int datatypeNumber = 0; datatypeNumber < p1; datatypeNumber++) {
                datatypes.add(new Datatype(matrix,datatypeNumber,weight,hight,countOfElements));
            }
        }
        if(diraction == 1){
            int countOfElements = matrix.size()/p2;
            for (int datatypeNumber = 0; datatypeNumber < p2; datatypeNumber++) {
                datatypes.add(new Datatype(matrix,datatypeNumber,weight,hight,countOfElements));
            }
        }
    }

    public ArrayList<Datatype> getDatatypes() {
        return datatypes;
    }
}
