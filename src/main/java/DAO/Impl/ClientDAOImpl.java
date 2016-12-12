package DAO.Impl;

import DAO.ClientDAO;
import Entity.ClientEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("clientDAO")
@Transactional
public class ClientDAOImpl  implements ClientDAO {

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    private List<ClientEntity> clients;


    @Override
    public ClientEntity createClient(ClientEntity client) {
        sessionFactory.getCurrentSession().save(client);
        return client;
    }

    @Override
    public void deleteClient(ClientEntity client) {

        ClientEntity mergedClient = (ClientEntity) sessionFactory.getCurrentSession().merge(client);
        sessionFactory.getCurrentSession().delete(mergedClient);
    }

    @Override
    public void updateClient(ClientEntity client) {

        ClientEntity mergedClient = (ClientEntity) sessionFactory.getCurrentSession().merge(client);
        sessionFactory.getCurrentSession().update(mergedClient);
    }

    @Override
    public List<ClientEntity> getClients() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClientEntity.class);
        clients = criteria.list();
        return clients;
    }

    @Override
    public ClientEntity getClientById(String clientId) {

        String userHQL = "FROM client WHERE id_client = :clientId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("clientId", clientId);
        return (ClientEntity) query.uniqueResult();
    }

    @Override
    public ClientEntity getClientByLogin(String login) {

        String userHQL = "FROM client WHERE login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("login", login);
        return (ClientEntity) query.uniqueResult();
    }

    @Override
    public ClientEntity getClientBySurname(String surname) {

        String userHQL = "FROM client WHERE surname = :surname";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("surname", surname);
        return (ClientEntity) query.uniqueResult();
    }

    @Override
    public boolean isClientExist(ClientEntity client) {

        Query query = sessionFactory.getCurrentSession().createQuery("FROM client WHERE name = :name " +
                "AND lastname = :lastname AND surname = :surname");
        query.setParameter("name", client.getName());
        query.setParameter("lastname", client.getLastname());
        query.setParameter("surname", client.getSurname());
        return query.list().size() > 0;
    }
}
