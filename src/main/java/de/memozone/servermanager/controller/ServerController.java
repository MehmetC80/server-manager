package de.memozone.servermanager.controller;

import de.memozone.servermanager.enumeration.Status;
import de.memozone.servermanager.model.Response;

import de.memozone.servermanager.model.Server;
import de.memozone.servermanager.service.implementation.ServerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {


    private final ServerServiceImpl serverService;


    @GetMapping("/list")
    public ResponseEntity<Response> getServers() throws InterruptedException {
        //for simulation waiting time
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity
                .ok(Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.List(30)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(Response.builder().timeStamp(now())
                .data(Map.of("server", server))
                .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
                .status(OK).
                statusCode(OK.value())
                .build());


    }

    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {

        return ResponseEntity.ok(
                Response.builder().timeStamp(now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build());

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {


        return ResponseEntity.ok(
                Response.builder().timeStamp(now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build());

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                Response.builder().timeStamp(now())
                        .data(Map.of("deleted server", serverService.delete(id)))
                        .message("Deleted server")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );

    }


    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {

        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
    }


}
