package com.kv.model;

import java.util.ArrayList;
import java.util.List;

public class ClusterInformation {
    List<VirtualNodeInfo> nodes;
    int allNodeNums;

    public ClusterInformation(){
        nodes = new ArrayList<>();
        allNodeNums = 0;
    }

    public synchronized void addNode(NodeInfo nodeInfo){
        for (int i = 0; i < nodeInfo.virtualNodes; i++){
            VirtualNodeInfo v = new VirtualNodeInfo();
            v.internalId = i;
            v.nodeInfo = nodeInfo;
            nodes.add(v);
        }
        allNodeNums++;
    }


}
