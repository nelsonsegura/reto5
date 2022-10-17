package com.ciclo3.controller;

import com.ciclo3.model.Client;
import com.ciclo3.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Client")
@CrossOrigin(origins = "*")

public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAllClient() {
        return clientService.getAllClient();
    }
    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client saveClient(@RequestBody Client c) {
        return clientService.saveClient(c);
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED )
    public Client updateClient(@RequestBody Client c) {
        return clientService.updateClient(c);
    };
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }


}
