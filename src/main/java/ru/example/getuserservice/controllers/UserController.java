package ru.example.getuserservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.example.getuserservice.model.User;
import ru.example.getuserservice.services.ParserJsonUserService;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

@RestController
@RequestMapping("/users")
public class UserController {

    private ParserJsonUserService parser;

    private Queue<User> queue = new ArrayDeque<>();
    public UserController(ParserJsonUserService parser) {
        this.parser = parser;
    }


    @GetMapping("/start")
    public ResponseEntity<ArrayList<User>> getUserRandomUserApi(@RequestParam(required = false) Integer results) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format("https://randomuser.me/api/?nat=us,dk,fr,gb&results=%s",results)))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ArrayList<User> users = parser.convertJsonToUser(response.body());

        return ResponseEntity.ok(users);
    }
}
