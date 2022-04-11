package com.parallel;

import java.util.ArrayList;

public class Scatter implements Operation{
    private ArrayList<Datatype> datatypes;
    /**
     * @param sendbuf Откуда брать
     * @param sendcount Сколько брать
     * @param sendtype Что собирать
     * @param workers Куда отправлять
     * @param recvbuf Куда класть
     */
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
    }
}
