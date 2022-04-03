package com.parallel;

import java.util.ArrayList;

import static com.parallel.Main.*;

public class Scatter implements Operation{

    @Override
    public void execution(ArrayList<Datatype> sendtype, Object recvbuf) {

    }

    /**
     *
     * @param sendbuf this.A
     * @param sendoffset 0
     * @param sendcount
     * @param sendtype Vector<"Double">
     * @param recvbuf worker
     * @param recvoffset 0
     * @param recvcount
     * @param recvtype Datatype
     */
    public void execution1(java.lang.Object sendbuf,
                           int sendoffset,
                           int sendcount,
                           Datatype sendtype,
                           java.lang.Object recvbuf,
                           int recvoffset,
                           int recvcount,
                           Datatype recvtype) {
        ArrayList<Datatype> datatypes = new DataManager(A,n1,n2,p1,0).getDatatypes();
    }
}
