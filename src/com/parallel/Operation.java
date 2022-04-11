package com.parallel;


public interface Operation {
    public void execution(
            Matrix sendbuf,
            int sendcount,
            Datatype sendtype,
            GroupOfWorkers workers,
            String recvbuf);
}
