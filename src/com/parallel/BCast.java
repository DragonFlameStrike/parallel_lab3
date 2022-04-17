package com.parallel;

public class BCast implements Operation{
    /**
     * @param groupOfWorkers Откуда брать
     * @param recvbuf Что брать
     * @param count Сколько брать
     * @param datatype Что из этого всего собирать
     * @param commutator И как это все отправлять
     */
    public void execution(GroupOfWorkers groupOfWorkers,
                          String recvbuf,
                          int count,
                          Datatype datatype,
                          Commutator commutator) {
        for (int finishWorkerIndex = 0; finishWorkerIndex < groupOfWorkers.size(); finishWorkerIndex++) {
            Worker worker = groupOfWorkers.getWorker(finishWorkerIndex);
            Datatype data = datatype.createDatatype(worker.getPartMatrix(recvbuf),0,count);
            commutator.sendDataAllInGroupFromFirst(finishWorkerIndex,data,recvbuf);
        }
    }
}

