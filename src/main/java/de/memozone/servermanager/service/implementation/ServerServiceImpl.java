package de.memozone.servermanager.service.implementation;


import de.memozone.servermanager.model.Server;
import de.memozone.servermanager.repository.ServerRepository;
import de.memozone.servermanager.service.ServerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static de.memozone.servermanager.enumeration.Status.SERVER_DOWN;
import static de.memozone.servermanager.enumeration.Status.SERVER_UP;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class ServerServiceImpl implements ServerService {


    private final ServerRepository serverRepository;


    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }


    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging a server with ip: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> List(int limit) {
        log.info("Get all servers");

        return serverRepository.findAll(PageRequest.of(0,limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Get single server with serverId: {}",id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("updating the server: {}",server.getName());

        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server with id: {}", id);
         serverRepository.deleteById(id);
         return true;
    }


    private String setServerImageUrl() {
        String[] imageNames={"server01.png","server02.png","server03.png","server04.png","server05.png"};

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("server/image/"+imageNames[new Random()
                        .nextInt(5)]).toUriString();
    }
}
