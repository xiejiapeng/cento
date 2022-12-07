package com.twitter.server.dao;

import com.twitter.common.model.User;
import org.springframework.stereotype.Repository;

/*
    total user: 1,000,000,000

    may use mongodb? hbase?
 */
@Repository
public interface UserProfileRepository {
    public User getUserProfile(long userId);

    public boolean isPopular(long userId);
}
