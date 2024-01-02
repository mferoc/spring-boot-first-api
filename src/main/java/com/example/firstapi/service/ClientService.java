package com.example.firstapi.service;

import com.example.firstapi.entity.Client;
import com.example.firstapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(String id) {
        final Optional<Client> clientEntityOptional = clientRepository.findById(Long.valueOf(id));

        return clientEntityOptional.orElse(null);
    }

}
