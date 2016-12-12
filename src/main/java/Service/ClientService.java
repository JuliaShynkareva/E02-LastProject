package Service;

import Entity.ClientEntity;

import java.util.List;

public interface ClientService {

    ClientEntity createClient(ClientEntity client);

    void deleteClient(ClientEntity client);

    void updateClient(ClientEntity client);

    List<ClientEntity> getClients();

    ClientEntity getClientById(String clientId);

    ClientEntity getClientByLogin(String login);

    ClientEntity getClientBySurname(String surname);

    boolean isClientExist(ClientEntity client);
}
