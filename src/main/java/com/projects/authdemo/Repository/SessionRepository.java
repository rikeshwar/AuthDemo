package com.projects.authdemo.Repository;

import com.projects.authdemo.Model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {

    Session save(Session session);
    List<Session> getSessionsByUserId(Long id);

}
