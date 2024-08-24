package com.projects.authdemo.Security.Repo;

import java.util.Optional;


import com.projects.authdemo.Security.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findByClientId(String clientId);
    Optional<Client> findByClientName(String name);
}