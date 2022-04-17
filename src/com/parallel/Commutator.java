package com.parallel;

public interface Commutator {
    void sendDataAllInGroupFromFirst(int groupNumber,Datatype datatype);
    Worker getNextWorkerToReachGoal(int workerNumber, int where);
}
