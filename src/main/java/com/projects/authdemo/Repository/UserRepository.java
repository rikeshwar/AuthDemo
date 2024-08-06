package com.projects.authdemo.Repository;

import com.projects.authdemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User save(User user);
}
