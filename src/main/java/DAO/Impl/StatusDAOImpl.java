package DAO.Impl;

import DAO.StatusDAO;
import Entity.StatusEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("statusDAO")
@Transactional
public class StatusDAOImpl implements StatusDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    private List<StatusEntity> statuses;


    @Override
    public StatusEntity createStatus(StatusEntity status) {
        sessionFactory.getCurrentSession().save(status);
        return status;
    }

    @Override
    public void deleteStatus(StatusEntity status) {

        StatusEntity mergedStatus = (StatusEntity) sessionFactory.getCurrentSession().merge(status);
        sessionFactory.getCurrentSession().delete(mergedStatus);
    }

    @Override
    public void updateStatus(StatusEntity status) {

        StatusEntity mergedStatus = (StatusEntity) sessionFactory.getCurrentSession().merge(status);
        sessionFactory.getCurrentSession().update(mergedStatus);
    }

    @Override
    public List<StatusEntity> getStatuses() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StatusEntity.class);
        statuses = criteria.list();
        return statuses;
    }

    @Override
    public StatusEntity getStatusById(int statusId) {

        String userHQL = "FROM status WHERE id_status = :statusId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("statusId", statusId);
        return (StatusEntity) query.uniqueResult();
    }
}
