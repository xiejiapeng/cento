package com.kv.service;

import com.kv.model.ClusterInformation;
import com.kv.proto.NodeOuterClass;

import java.util.concurrent.ConcurrentHashMap;

public class Server {
    String nodeId;
    ConcurrentHashMap<String, NodeOuterClass.Node> localData;

    ClusterInformation clusterInformation;
    HashRing hashRing;

    GossipClient gossipClient;

    public Server(String nodeId){
        this.nodeId = nodeId;
        this.localData = new ConcurrentHashMap<>();
        clusterInformation = new ClusterInformation();
        this.gossipClient = new GossipClient(clusterInformation);
    }

    public void waitStart() throws InterruptedException {
        while (!gossipClient.ready()) {
            synchronized (gossipClient){
                gossipClient.wait();
            }
        }
    }
}
