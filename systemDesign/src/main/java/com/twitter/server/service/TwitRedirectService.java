package com.twitter.server.service;

import com.twitter.common.model.Twit;
import org.springframework.stereotype.Component;

@Component
public interface TwitRedirectService {
    public void redirect(long fromId, long toId, Twit twit);
}
