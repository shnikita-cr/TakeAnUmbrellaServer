package com.takeanumbrella.takeanumbrellaserver.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(path = "api/v1/clients/{id}")
    @GetMapping
    public Client getClient(@PathVariable Long id) {//implements CLIENT INFO request
        return clientService.getClient(id);
    }

    @RequestMapping(path = "api/v1/clients")
    @PostMapping
    public HttpStatus addClient(@RequestBody Client client) {//implements NEW CLIENT request
        return clientService.addClient(client);
    }
}
