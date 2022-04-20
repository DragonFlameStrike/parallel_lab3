package com.parallel;

import java.io.Serializable;
import java.util.ArrayList;

public class Gather implements Operation{
    /**
     * @param sendbuf Куда класть
     * @param sendcount Сколько класть
     * @param sendtype Что класть
     * @param workers У кого брать
     * @param recvbuf Откуда брать
     * @param commutator И как это все отправлять
     */
    public void execution(Matrix sendbuf,
                          int sendcount,
                          Datatype sendtype,
                          GroupOfWorkers workers,
                          String recvbuf,
                          Commutator commutator) {
        for (Worker worker : workers.getWorkers()) {
            Datatype datatype = sendtype.createDatatype(worker.getResult(),0,worker.getResult().size());
            commutator.sendData(0,datatype,"result",worker.getWorkerNumber());
        }

    }
}
