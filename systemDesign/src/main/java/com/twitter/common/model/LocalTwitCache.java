package com.twitter.common.model;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

public class LocalTwitCache {
    ConcurrentHashMap<User, TreeSet<Twit>> cache = new ConcurrentHashMap<>();
}
