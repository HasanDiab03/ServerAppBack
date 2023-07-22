package com.manageservers.ManageServers.service.implementation;

import com.manageservers.ManageServers.enumeration.Status;
import com.manageservers.ManageServers.model.Server;
import com.manageservers.ManageServers.repo.ServerRepo;
import com.manageservers.ManageServers.service.ServerService;
import jakarta.servlet.Servlet;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

@RequiredArgsConstructor // constructor for required fields such as serverRepo,
                        // spring will magically give us an instance for the serverRepo.
@Service // to let the application know this is the service layer
@Transactional // all or nothing, either everything works, or nothing happens if exception occurs
@Slf4j // to print to the console
public class ServerServiceImplementation implements ServerService {
    // the implementation of the methods to use in the api
    private final ServerRepo serverRepo;
    @Override
    public Server create(Server server) {
        log.info("Saving new server:{}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server); // saves the server to the database
    }



    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP:{}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress); // find the server with ipAddress
        InetAddress address = InetAddress.getByName(ipAddress); // get the InetAddress of the ipAddress
                                                                // in order to check if it's reachable or not
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        // setting the status according to if the server is reachable within 10000 or not.
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepo.findAll(PageRequest.of(0, limit)).toList();
        // using the PageRequest.of method we can limit the number of Servers returned to a certain integer
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching sever by id: {}", id);
        return serverRepo.findById(id).get();
        // use the get() to return the server, since the find returns an optional
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepo.save(server);
        // through jpa, it will notice that the server passed has the same id as some other server
        // so instead of saving a new server, it will update it in place of the one with same id
        // if the id is new one, then it will do a creation
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by Id: {}", id);
        serverRepo.deleteById(id);
        return Boolean.TRUE; // if the id exists, it will delete it and return true, otherwise false

    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.jpeg"
                , "server2.jpeg"
                , "server3.jpeg"
                , "server4.jpeg"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
        // so what happens here is that it will return the current path (localhost:8080)  + /server/image/a random image name from the array i have
    }
}
