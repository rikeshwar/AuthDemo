package com.projects.authdemo.Repository;

import com.projects.authdemo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User save(User user);
    Optional<User> findById(Long id);

    @Query (value = "select * from user",nativeQuery = true)
    List<User> getAllUsers();
}
