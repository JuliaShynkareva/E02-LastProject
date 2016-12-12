package Service.Impl;

import DAO.ClientDAO;
import Entity.ClientEntity;
import Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDAO dao;

    @Override
    @Transactional
    public ClientEntity createClient(ClientEntity client) {
        return dao.createClient(client);
    }

    @Override
    @Transactional
    public void deleteClient(ClientEntity client) {
        dao.deleteClient(client);
    }

    @Override
    @Transactional
    public void updateClient(ClientEntity client) {
        dao.updateClient(client);
    }

    @Override
    @Transactional
    public List<ClientEntity> getClients() {
        return dao.getClients();
    }

    @Override
    @Transactional
    public ClientEntity getClientById(String clientId) {
        return dao.getClientById(clientId);
    }

    @Override
    public ClientEntity getClientByLogin(String login) {
        return dao.getClientByLogin(login);
    }

    @Override
    @Transactional
    public ClientEntity getClientBySurname(String surname) {
        return dao.getClientBySurname(surname);
    }

    @Override
    public boolean isClientExist(ClientEntity client) {
        return dao.isClientExist(client);
    }
}
