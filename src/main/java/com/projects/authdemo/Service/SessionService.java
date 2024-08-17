package com.projects.authdemo.Service;

import com.projects.authdemo.Config.UserConfig;
import com.projects.authdemo.Enum.Status;
import com.projects.authdemo.Model.Session;
import com.projects.authdemo.Model.User;
import com.projects.authdemo.Repository.SessionRepository;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Service
public class SessionService {
    @Autowired
    SessionRepository sessionRepository;


    public Session createSession(User user)
    {
        Session session=new Session();
        session.setUser(user);
        //session.setToken(RandomStringUtils.randomAscii(20));//here
        //if we use normal random may contain non Ascii value will craete
        //problem in passing the data over http header which at its local
        //level also try to encrypt and its work only with Ascii value
        //so its get removed from http header if non Ascii value exist

        //lets create jwt token
        Date date=new Date();
        SecretKey key= Jwts.SIG.HS256.key().build();
        String jwtToken= Jwts.builder().id(String.valueOf(user.getId()))
                                       .issuedAt(date).claim("name",user.getName())
                                               .signWith(key).compact();
        session.setToken(jwtToken);



        session.setStatus(Status.ACTIVE);
        return sessionRepository.save(session);

    }
    public List<Session> getSessionByUserId(Long id)
    {
        return sessionRepository.getSessionsByUserId(id);
    }

}
