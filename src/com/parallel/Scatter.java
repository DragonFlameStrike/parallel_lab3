package com.parallel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import static com.parallel.Main.*;

public class Scatter implements Operation{
    private ArrayList<Datatype> datatypes;
    /**
     *
     * @param sendbuf Откуда
     * @param sendcount Сколько
     * @param sendtype Какой тип
     * @param recvbuf Куда
     */
    @Override
    public void execution(Matrix sendbuf,
                          int sendcount,
                          Datatype sendtype,
                          GroupOfWorkers recvbuf) {
        if(sendtype instanceof DatatypeColumnMatrix) {
            datatypes = new DataManager(sendbuf, sendbuf.getWeight()/p2, sendbuf.getHight(), p1, p2, 1).getDatatypes();
            for (int index = 0; index < recvbuf.size(); index++) {
                recvbuf.getWorker(index).setPartB(datatypes.get(index).getPartMatrix());
            }
        }
        if(sendtype instanceof DatatypeRowMatrix) {
            datatypes = new DataManager(sendbuf, sendbuf.getWeight(), sendbuf.getHight()/p1, p1, p2, 0).getDatatypes();
            for (int index = 0; index < recvbuf.size(); index++) {
                recvbuf.getWorker(index).setPartA(datatypes.get(index).getPartMatrix());
            }
        }
    }
}
