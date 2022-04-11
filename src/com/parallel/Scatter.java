package com.parallel;

import java.util.ArrayList;

public class Scatter implements Operation{
    private ArrayList<Datatype> datatypes;
    /**
     * @param sendbuf Откуда
     * @param sendcount Сколько
     * @param sendtype Какой тип
     * @param workers Куда
     */
    @Override
    public void execution(Matrix sendbuf,
                          int sendcount,
                          Datatype sendtype,
                          GroupOfWorkers workers,
                          String recvbuf) {
        datatypes = new ArrayList<>();
        int countOfElements = sendbuf.size()/sendcount;
        for (int datatypeNumber = 0; datatypeNumber < sendcount; datatypeNumber++) {
            datatypes.add(sendtype.createDatatype(sendbuf,datatypeNumber,countOfElements));
        }
        for (int index = 0; index < workers.size(); index++) {
            workers.getWorker(index).setDatatype(datatypes.get(index),recvbuf);
        }

//        if(sendtype instanceof DatatypeColumnMatrix) {
//            for (int index = 0; index < workers.size(); index++) {
//                workers.getWorker(index).setSecondPart(datatypes.get(index).getPartMatrix());
//            }
//        }
//        if(sendtype instanceof DatatypeRowMatrix) {
//            for (int index = 0; index < workers.size(); index++) {
//                workers.getWorker(index).setFirstPart(datatypes.get(index).getPartMatrix());
//            }
//        }
    }
}
