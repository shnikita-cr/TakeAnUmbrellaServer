package com.takeanumbrella.takeanumbrellaserver.client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
    public Client getClient(Long id) {
        return new Client("name","email","password"); //FIXME client by id
    }
}
