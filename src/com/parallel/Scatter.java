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
     * @param recvcount Сколько
     * @param recvtype Какй тип
     */
    @Override
    public void execution(Matrix sendbuf,
                          int sendcount,
                          Serializable sendtype,
                          GroupOfWorkers recvbuf,
                          int recvcount,
                          Serializable recvtype) {
        if(sendtype instanceof Datatype) {
            datatypes = new DataManager(sendbuf, sendbuf.getWeight()/p2, sendbuf.getHight(), p1, p2, 1).getDatatypes();
            for (int index = 0; index < recvbuf.size(); index++) {
                recvbuf.getWorker(index).setPartB(datatypes.get(index).getPartMatrix());
            }
        }
        if(sendtype instanceof Double) {
            datatypes = new DataManager(sendbuf, sendbuf.getWeight(), sendbuf.getHight()/p1, p1, p2, 0).getDatatypes();
            for (int index = 0; index < recvbuf.size(); index++) {
                recvbuf.getWorker(index).setPartA(datatypes.get(index).getPartMatrix());
            }
        }
    }
}
