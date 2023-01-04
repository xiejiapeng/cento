package com.kv.service;

import com.kv.model.ClusterInformation;

public class GossipClient {
    ClusterInformation clusterInformation;
    public GossipClient(ClusterInformation clusterInformation){
        this.clusterInformation = clusterInformation;
    }

    public boolean ready(){
        return false;
    }
}
