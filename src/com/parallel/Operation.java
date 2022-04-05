package com.parallel;

import java.io.Serializable;
import java.util.ArrayList;

public interface Operation {
    public void execution(
            Matrix matrix,
            int sendcount,
            Serializable sendtype,
            GroupOfWorkers groupOfWorkers,
            int recvcount,
            Serializable recvtype);
}
