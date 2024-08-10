package com.projects.authdemo.Repository;

import com.projects.authdemo.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Role save(Role role);
    Optional<Role> findByName(String role_name);
}
