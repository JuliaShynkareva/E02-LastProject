package DAO.Impl;

import DAO.ApplicationDAO;
import Entity.ApplicationEntity;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

@Repository("applicationDAO")
@Transactional
public class ApplicationDAOImpl implements ApplicationDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    private List<ApplicationEntity> applications;

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Override
    public ApplicationEntity createApplication(ApplicationEntity application) {

        sessionFactory.getCurrentSession().save(application);
        LOGGER.info("The application has been successfully added");
        return application;
    }

    @Override
    public void deleteApplication(ApplicationEntity application) {

        ApplicationEntity mergedApplication = (ApplicationEntity) sessionFactory.getCurrentSession().merge(application);
        sessionFactory.getCurrentSession().delete(mergedApplication);
        LOGGER.info("The application has been successfully removed");
    }

    @Override
    public void updateApplication(ApplicationEntity application) {

        ApplicationEntity mergedApplication = (ApplicationEntity) sessionFactory.getCurrentSession().merge(application);
        sessionFactory.getCurrentSession().update(mergedApplication);
        LOGGER.info("The application has been successfully updated");
    }

    @Override
    public List<ApplicationEntity> getApplications() {

        /*applications = (List<ApplicationEntity>) sessionFactory.getCurrentSession()
                .createCriteria(ApplicationEntity.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ApplicationEntity.class);

        applications = criteria.list();
        LOGGER.info("List of applications received");

        return applications;
    }

    @Override
    public ApplicationEntity getApplicationById(int applicationId) {

        String userHQL = "FROM application WHERE id_application = :applicationId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("applicationId", applicationId);
        return (ApplicationEntity) query.uniqueResult();
    }

    @Override
    public ApplicationEntity getApplicationByClient(int clientId) {

        String userHQL = "FROM application WHERE client_id = :clientId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("clientId", clientId);
        return (ApplicationEntity) query.uniqueResult();
    }

    @Override
    public ApplicationEntity getApplicationByEmployee(int employeeId) {

        String userHQL = "FROM application WHERE employee_id = :employeeId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("employeeId", employeeId);
        return (ApplicationEntity) query.uniqueResult();
    }
}
