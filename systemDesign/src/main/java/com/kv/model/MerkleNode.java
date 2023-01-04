package com.kv.model;

import com.kv.proto.NodeOuterClass;

public class MerkleNode {
    MerkleNode left;
    MerkleNode right;

    NodeOuterClass.Node node;

    String hash;

    public MerkleNode(MerkleNode left, MerkleNode right, NodeOuterClass.Node node){
        this.left = left;
        this.right = right;
        this.node = node;
        if(node != null)this.hash = String.valueOf(node.getValue().hashCode());
        else this.hash = String.valueOf((left.hash + right.hash).hashCode());
    }
}
