package com.projects.authdemo.Service;

import com.projects.authdemo.Config.UserConfig;
import com.projects.authdemo.Enum.Status;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.SessionRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;


    public Session createSession(User user)
    {
        Session session=new Session();
        session.setUser(user);
        session.setToken(RandomStringUtils.random(20));
        session.setStatus(Status.ACTIVE);
        return sessionRepository.save(session);

    }

}
