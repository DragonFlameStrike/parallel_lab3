package com.parallel;


public interface Operation {
    public void execution(
            Matrix matrix,
            int sendcount,
            Datatype sendtype,
            GroupOfWorkers groupOfWorkers);
}
