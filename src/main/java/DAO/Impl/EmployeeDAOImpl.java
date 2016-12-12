package DAO.Impl;

import DAO.EmployeeDAO;
import Entity.EmployeeEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("employeeDAO")
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    private List<EmployeeEntity> employees;


    @Override
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        sessionFactory.getCurrentSession().save(employee);
        return employee;
    }

    @Override
    public void deleteEmployee(EmployeeEntity employee) {

        EmployeeEntity mergedEmployee = (EmployeeEntity) sessionFactory.getCurrentSession().merge(employee);
        sessionFactory.getCurrentSession().delete(mergedEmployee);
    }

    @Override
    public void updateEmployee(EmployeeEntity employee) {

        EmployeeEntity mergedEmployee = (EmployeeEntity) sessionFactory.getCurrentSession().merge(employee);
        sessionFactory.getCurrentSession().update(mergedEmployee);
    }

    @Override
    public List<EmployeeEntity> getEmployees() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(EmployeeEntity.class);
        employees = criteria.list();
        return employees;
    }

    @Override
    public EmployeeEntity getEmployeeById(String employeeId) {

        String userHQL = "FROM employee WHERE id_employee = :employeeId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("employeeId", employeeId);
        return (EmployeeEntity) query.uniqueResult();
    }

    @Override
    public EmployeeEntity getEmployeeByLogin(String login) {

        String userHQL = "FROM employee WHERE login = :login";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("login", login);
        return (EmployeeEntity) query.uniqueResult();
    }

    @Override
    public EmployeeEntity getEmployeeBySurname(String surname) {

        String userHQL = "FROM employee WHERE surname = :surname";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("surname", surname);
        return (EmployeeEntity) query.uniqueResult();
    }
}
