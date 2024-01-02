package com.example.firstapi.controller;

import com.example.firstapi.entity.Client;
import com.example.firstapi.mapper.MapperDecorated;
import com.example.firstapi.model.ClientDto;
import com.example.firstapi.model.ClientDtoResponse;
import com.example.firstapi.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final MapperDecorated mapper;

    @CrossOrigin
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDtoResponse> clients() {
        final List<Client> clientEntityList = clientService.getAllClients();

        final List<ClientDto> clientDtoList = clientEntityList.stream().map(mapper::toDto).collect(Collectors.toList());

        final ClientDtoResponse response = mapper.toResponse(clientDtoList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDto> client(@PathVariable("id") String idClient) {
        final Client client = clientService.getClientById(idClient);

        if (Objects.isNull(client)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        final ClientDto clientDtoResponse = mapper.toDto(client);

        return new ResponseEntity<>(clientDtoResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto save(@RequestBody ClientDto clientDto) {
        final Client entity = clientService.save(mapper.toEntity(clientDto));

        return mapper.toDto(entity);
    }
}
