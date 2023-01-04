package com.kv.service;

import com.kv.model.ClusterInformation;
import com.kv.model.NodeInfo;

import java.util.ArrayList;
import java.util.List;

public class HashRing {
    int replicates;
    int writeNumbers;
    ClusterInformation cluster;

    //head is coordinator node, remaining nodes are replicated nodes
    public List<NodeInfo> clockWise(int hash){
        return new ArrayList<>();
    }
}
