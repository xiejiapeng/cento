package com.twitter.server.dao;

import com.twitter.common.model.User;

import java.util.List;

/*
    total user 1,000,000,000
    avg sub 500 -> 500,000,000,000 => can store all subs in one record
 */
public interface UserRelationRepository {
    public List<User> findAllSubscribtors(long userId);
}
