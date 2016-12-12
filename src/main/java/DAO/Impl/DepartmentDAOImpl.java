package DAO.Impl;

import DAO.DepartmentDAO;
import Entity.ApplicationEntity;
import Entity.DepartmentEntity;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository("departmentDAO")
@Transactional
public class DepartmentDAOImpl  implements DepartmentDAO{

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    private List<DepartmentEntity> departments;


    @Override
    public DepartmentEntity createDepartment(DepartmentEntity department) {
        sessionFactory.getCurrentSession().save(department);
        return department;
    }

    @Override
    public void deleteDepartment(DepartmentEntity department) {

        DepartmentEntity mergedDepartment = (DepartmentEntity) sessionFactory.getCurrentSession().merge(department);
        sessionFactory.getCurrentSession().delete(mergedDepartment);
    }

    @Override
    public void updateDepartment(DepartmentEntity department) {

        DepartmentEntity mergedDepartment = (DepartmentEntity) sessionFactory.getCurrentSession().merge(department);
        sessionFactory.getCurrentSession().update(mergedDepartment);
    }

    @Override
    public List<DepartmentEntity> getDepartments() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(DepartmentEntity.class);
        departments = criteria.list();
        return departments;
    }

    @Override
    public DepartmentEntity getDepartmentById(String departmentId) {

        String userHQL = "FROM department WHERE id_department = :departmentId";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("departmentId", departmentId);
        return (DepartmentEntity) query.uniqueResult();
    }

    @Override
    public DepartmentEntity getDepartmentByName(String name) {

        String userHQL = "FROM department WHERE name = :name";
        Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
        query.setParameter("name", name);
        return (DepartmentEntity) query.uniqueResult();
    }
}
