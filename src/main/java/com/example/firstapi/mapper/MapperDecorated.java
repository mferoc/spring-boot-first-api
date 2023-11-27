package com.example.firstapi.mapper;

import com.example.firstapi.entity.Client;
import com.example.firstapi.model.ClientDto;
import com.example.firstapi.model.ClientDtoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class MapperDecorated {
    public Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .clientName(clientDto.getClientName())
                .build();
    }

    public ClientDto toDto(Client clientEntity) {
        return ClientDto.builder()
                .clientName(clientEntity.getClientName())
                .id(clientEntity.getId())
                .build();
    }

    public ClientDtoResponse toResponse(List<ClientDto> clientDtoList) {
        return ClientDtoResponse.builder()
                .clientDtoList(clientDtoList)
                .build();
    }
}
