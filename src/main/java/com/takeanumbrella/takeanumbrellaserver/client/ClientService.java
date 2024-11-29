package com.takeanumbrella.takeanumbrellaserver.client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client getClient(Long id) {
        System.out.println("getClient " + id);
        return repository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Long addClient(Client client) {
        Long id = repository.save(client).getClientId(); // TODO check if saved
        System.out.println("Adding client " + client);
        return id;
    }

}
