package com.parallel;

import java.util.ArrayList;

import static com.parallel.Main.p1;
import static com.parallel.Main.p2;

public class ColumnCommutator implements Commutator{
    GroupOfWorkers workers;
    ArrayList<GroupOfWorkers> groupOfWorkersArrayList;

    public ColumnCommutator(GroupOfWorkers workers) {
        this.workers = workers;
        parseWorkers(this.workers);
    }

    @Override
    public void sendDataAllInGroupFromFirst(int groupNumber, Datatype datatype) {
        GroupOfWorkers currGroup = groupOfWorkersArrayList.get(groupNumber);
        currGroup.getWorker(0).sendDatatype(currGroup.getWorker(currGroup.size()-1).getWorkerNumber(),datatype,false,true,"partB",this);
    }

    @Override
    public Worker getNextWorkerToReachGoal(int currWorkerNumber, int goalWorker) {
        GroupOfWorkers currGroup = getGroupByWorkerNumber(currWorkerNumber);
        int indexCurrWorkerInGroup = getWorkerIndexInGroup(currWorkerNumber,currGroup);
        if(currWorkerNumber<goalWorker) {
            return currGroup.getWorker(indexCurrWorkerInGroup + 1);
        }
        return null;
    }

    private int getWorkerIndexInGroup(int currWorkerNumber, GroupOfWorkers currWorkers) {
        for (int workerIndex = 0; workerIndex < currWorkers.size(); workerIndex++) {
            if(currWorkers.getWorker(workerIndex).getWorkerNumber() == currWorkerNumber) return workerIndex;
        }
        return -1;
    }

    private GroupOfWorkers getGroupByWorkerNumber(int currWorkerNumber) {
        for (int i = 0; i < groupOfWorkersArrayList.size(); i++) {
            GroupOfWorkers currWorkers = groupOfWorkersArrayList.get(i);
            for (int j = 0; j < currWorkers.size(); j++) {
                if(currWorkers.getWorker(j).getWorkerNumber() == currWorkerNumber) return currWorkers;
            }
        }
        return null;
    }



    private void parseWorkers(GroupOfWorkers workers){
        this.groupOfWorkersArrayList = new ArrayList<>();
        for (int columnWorker = 0; columnWorker < p2; columnWorker++) {
            int startOffset = columnWorker;
            int offset = 0;
            int count = p2;
            int startIndex = startOffset;
            this.groupOfWorkersArrayList.add(workers.getSubGroupOfWorkers(startIndex,1,p2-1, p1));
        }
    }
}
