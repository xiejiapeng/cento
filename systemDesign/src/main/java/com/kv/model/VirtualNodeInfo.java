package com.kv.model;

public class VirtualNodeInfo {
    NodeInfo nodeInfo;
    int internalId;

    public String getId(){
        return nodeInfo.id + "_" + internalId;
    }
}
