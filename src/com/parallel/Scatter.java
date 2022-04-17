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
     * @param commutator И как это все отправлять
     */
    public void execution(Matrix sendbuf,
                          int sendcount,
                          Datatype sendtype,
                          GroupOfWorkers workers,
                          String recvbuf,
                          Commutator commutator) {
        datatypes = new ArrayList<>();
        int countOfElements = sendbuf.size()/sendcount;
        for (int datatypeNumber = 0; datatypeNumber < sendcount; datatypeNumber++) {
            datatypes.add(sendtype.createDatatype(sendbuf,datatypeNumber,countOfElements));
        }

        for (int finishWorkerIndex = 0; finishWorkerIndex < workers.size(); finishWorkerIndex++) {
            commutator.sendDataCertainInGroupFromFirst(finishWorkerIndex,datatypes.get(finishWorkerIndex),recvbuf);
        }
    }
}

