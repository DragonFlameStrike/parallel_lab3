package com.parallel;

import java.util.ArrayList;

import static com.parallel.Main.p2;

public class WorldCommutator implements Commutator {
    GroupOfWorkers workers;
    Root root;
    ArrayList<GroupOfWorkers> groupOfWorkersArrayList;

    public WorldCommutator(Root root,GroupOfWorkers workers) {
        this.root = root;
        this.workers = workers;
        parseWorkers(this.workers);
    }

    @Override
    public Worker getNextWorkerToReachGoal(int currWorkerNumber, int goalWorker) {
        if(currWorkerNumber>=p2){
            return groupOfWorkersArrayList.get(0).getWorkers().get(currWorkerNumber-p2);
        }
        else return groupOfWorkersArrayList.get(0).getWorkers().get(currWorkerNumber-1);

    }

    @Override
    public void sendData(int finishWorkerIndex, Datatype datatype, String recvbuf, int startWorkerNumber) {
        if(finishWorkerIndex == 0) {
            GroupOfWorkers currGroup = groupOfWorkersArrayList.get(0);
            currGroup.getWorker(startWorkerNumber).sendDatatypeToRoot(startWorkerNumber, datatype, recvbuf, this);
        }
        if(finishWorkerIndex == -1) {
            root.setPartOfResults(datatype,recvbuf,startWorkerNumber);
        }
    }
    private void parseWorkers(GroupOfWorkers workers){
        this.groupOfWorkersArrayList = new ArrayList<>();
        this.groupOfWorkersArrayList.add(workers);
    }


    @Override
    public void sendDataCertainInGroupFromFirst(int finishWorkerIndex, Datatype datatype, String recvbuf) {

    }
    @Override
    public void sendDataAllInGroupFromFirst(int groupNumber, Datatype datatype,String buf) {

    }
}
