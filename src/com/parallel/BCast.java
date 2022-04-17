package com.parallel;

public class BCast implements Operation{
    /**
     * @param groupOfWorkers Откуда брать
     * @param buf Что брать
     * @param count Сколько брать
     * @param datatype Что из этого всего собирать
     * @param commutator И как это все отправлять
     */
    public void execution(GroupOfWorkers groupOfWorkers,
                          String buf,
                          int count,
                          Datatype datatype,
                          Commutator commutator) {
        for (int index = 0; index < groupOfWorkers.size(); index++) {
            Worker worker = groupOfWorkers.getWorker(index);
            Datatype data = datatype.createDatatype(worker.getPartMatrix(buf),0,count);
            commutator.sendDataAllInGroupFromFirst(index,data);
        }
    }
}

