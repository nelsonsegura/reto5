package com.ciclo3.service;
import com.ciclo3.model.Client;
import com.ciclo3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClient() {
        return (List<Client>) clientRepository.getAllClient();
    }
    public Optional<Client> getClientById(Integer id) {
        return clientRepository.getClientById(id);
    }

    public Client saveClient(Client c) {
        if (c.getIdClient() == null){
            return clientRepository.saveClient(c);
        }else{
            Optional<Client> cl = clientRepository.getClientById(c.getIdClient());
            if (cl.isEmpty()){
                return clientRepository.saveClient(c);
            }else{
                return c;
            }
        }
    }
    public Client updateClient(Client client) {
        if (client.getIdClient() != null) {
            Optional<Client> e = clientRepository.getClientById(client.getIdClient());
            if (!e.isEmpty()) {
                if (client.getName() != null) {
                    e.get().setName(client.getName());
                }
                if (client.getAge() != null) {
                    e.get().setAge(client.getAge());
                }
                if (client.getEmail() != null) {
                    e.get().setEmail(client.getEmail());
                }
                if (client.getPassword() != null) {
                    e.get().setPassword(client.getPassword());
                }
                clientRepository.saveClient(e.get());
                return e.get();
            }
        }
        return client;
    }
    public boolean deleteClient(Integer id){
        Boolean d = getClientById(id).map(client -> {
            clientRepository.deleteClient(client);
            return true;
        }).orElse(false);
        return d;
    }

}
