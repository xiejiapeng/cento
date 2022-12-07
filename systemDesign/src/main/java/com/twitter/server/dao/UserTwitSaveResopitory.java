package com.twitter.server.dao;

import com.twitter.common.model.Twit;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTwitSaveResopitory {
    public void saveTwit(Twit twit);
}
