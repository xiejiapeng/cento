package com.twitter.server.service;

import com.twitter.common.model.Twit;
import com.twitter.common.model.User;
import com.twitter.server.dao.UserProfileRepository;
import com.twitter.server.dao.UserRelationRepository;
import com.twitter.server.dao.UserTwitSaveResopitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
    DAU 100,000,000
    DAU * 5 = 500,000,000
    qps: 500,000,000 / 86400 ~ 6000 t/s
 */

@RestController
@RequestMapping("v1/twit/recv")
public class ReceivePostController {
    @Autowired
    private UserProfileRepository userProfileRepo;

    @Autowired
    private UserTwitSaveResopitory userTwitSaveResopitory;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private TwitRedirectService redirectService;

    @RequestMapping
    public void onReceive(@RequestParam long userId, @RequestBody Twit twit){
        User user = userProfileRepo.getUserProfile(userId);
        twit.setUserId(userId);
        userTwitSaveResopitory.saveTwit(twit);

        if(!userProfileRepo.isPopular(userId)) {
            List<User> subs = userRelationRepository.findAllSubscribtors(userId);
            subs.stream().map(u -> u.getUserId()).forEach(toId -> redirectService.redirect(userId, toId, twit));
        }
    }
}
