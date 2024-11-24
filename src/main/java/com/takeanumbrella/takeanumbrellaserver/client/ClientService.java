package com.takeanumbrella.takeanumbrellaserver.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

//    private final ClientRepository repository;

//    public ClientService(ClientRepository repository) {
////        this.repository = repository;
//    }

    public Client getClient(Long id) {
        return new Client("name", "email", "password"); //FIXME client by id
    }

    public HttpStatus addClient(Client client) {
//        repository.save(client);
        System.out.println("Adding client " + client);
        return HttpStatus.CREATED;
    }
}
