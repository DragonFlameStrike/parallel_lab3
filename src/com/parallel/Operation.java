package com.parallel;

import java.util.ArrayList;

public interface Operation {
    public void execution(
            ArrayList<Datatype> sendtype,
            java.lang.Object recvbuf);
}
