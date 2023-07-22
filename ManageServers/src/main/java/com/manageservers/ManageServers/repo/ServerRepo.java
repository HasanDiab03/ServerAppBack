package com.manageservers.ManageServers.repo;

import com.manageservers.ManageServers.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {
    // data access layer that communicates with the database through query-like methods.
    Server findByIpAddress(String ipAddress);
}
