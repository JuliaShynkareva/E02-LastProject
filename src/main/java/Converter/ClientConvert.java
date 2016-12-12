package Converter;

import DTO.ClientDTO;
import Entity.ClientEntity;

public class ClientConvert {

    public ClientDTO convertEntityToDTO(ClientEntity client) {

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setName(client.getName());
        clientDTO.setLastname(client.getLastname());
        clientDTO.setSurname(client.getSurname());

        return clientDTO;
    }

    public ClientEntity convertDTOToEntity(ClientDTO client) {

        ClientEntity clientEntity = new ClientEntity();

        clientEntity.setName(client.getName());
        clientEntity.setLastname(client.getLastname());
        clientEntity.setSurname(client.getSurname());

        return clientEntity;
    }

}
