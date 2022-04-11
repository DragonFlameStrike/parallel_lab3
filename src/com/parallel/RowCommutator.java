package com.parallel;

import java.util.ArrayList;

import static com.parallel.Main.*;

public class RowCommutator implements Commutator {
    GroupOfWorkers workers;
    ArrayList<GroupOfWorkers> groupOfWorkersArrayList;

    public RowCommutator(GroupOfWorkers workers) {
        this.workers = workers;
        parseWorkers(this.workers);
    }

    @Override
    public void sendData(int index, Datatype datatype){
        GroupOfWorkers currGroup = groupOfWorkersArrayList.get(index);
        for (int i = 0; i < currGroup.size(); i++) {
            Datatype newDatatype = null;
            try{
                newDatatype = datatype.clone();
            }
            catch (CloneNotSupportedException e)
            {
                System.out.println("Clone Exeption");
            }
            if(newDatatype != null) {
                currGroup.getWorker(i).setDatatype(newDatatype, "partA");
            }
        }
    }
    private void parseWorkers(GroupOfWorkers workers){
        this.groupOfWorkersArrayList = new ArrayList<>();
        for (int rowWorkers = 0; rowWorkers < p1; rowWorkers++) {
            this.groupOfWorkersArrayList.add(workers.getSubGroupOfWorkers(p2-1,1,rowWorkers));
        }
    }
}
