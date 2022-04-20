package com.parallel;

public interface Commutator {
    void sendDataAllInGroupFromFirst(int groupNumber,Datatype datatype,String buf);
    Worker getNextWorkerToReachGoal(int workerNumber, int where);
    void sendDataCertainInGroupFromFirst(int finishWorkerIndex, Datatype datatype, String recvbuf);
    void sendData(int i, Datatype datatype, String result, int workerNumber);
}
