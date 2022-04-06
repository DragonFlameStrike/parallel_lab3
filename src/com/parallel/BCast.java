package com.parallel;

public class BCast implements Operation{
    private int diraction;
    @Override
    public void execution(Matrix matrix, int sendcount, Datatype sendtype, GroupOfWorkers groupOfWorkers) {
        for (int worker = 0; worker < groupOfWorkers.size(); worker++) {
            if(sendtype instanceof DatatypeColumnMatrix) {
                diraction = 1; // Vertical
            }
            if(sendtype instanceof DatatypeRowMatrix) {
                diraction = 0; // horizontal
            }
            groupOfWorkers.getWorker(worker).sendData(diraction,sendcount);
        }
    }
}