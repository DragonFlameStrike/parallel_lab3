package com.parallel;

import java.io.Serializable;
import java.util.ArrayList;

public class Gather implements Operation {
    @Override
    public void execution(
            Matrix sendbuf,
            int sendcount,
            Datatype sendtype,
            GroupOfWorkers workers,
            String recvbuf) {
    }
}
