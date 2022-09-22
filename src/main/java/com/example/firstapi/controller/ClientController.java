package com.example.firstapi.controller;

import com.example.firstapi.entity.Client;
import com.example.firstapi.mapper.MapperDecorated;
import com.example.firstapi.model.ClientDto;
import com.example.firstapi.model.ClientDtoResponse;
import com.example.firstapi.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {
	private final ClientRepository clientRepository;
	private final MapperDecorated mapper;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientDtoResponse> clients() {
		final List<Client> clientEntityList = clientRepository.findAll();

		final List<ClientDto> clientDtoList = clientEntityList.stream().map(mapper::toDto).collect(Collectors.toList());

		final ClientDtoResponse response = mapper.toResponse(clientDtoList);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ClientDto save(@RequestBody ClientDto clientDto) {
		final Client entity = clientRepository.save(mapper.toEntity(clientDto));

		return mapper.toDto(entity);
	}
}
