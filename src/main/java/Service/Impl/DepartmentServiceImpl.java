package Service.Impl;

import DAO.DepartmentDAO;
import Entity.DepartmentEntity;
import Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    public DepartmentDAO dao;

    @Override
    @Transactional
    public DepartmentEntity createDepartment(DepartmentEntity department) {
        return dao.createDepartment(department);
    }

    @Override
    @Transactional
    public void deleteDepartment(DepartmentEntity department) {
        dao.deleteDepartment(department);
    }

    @Override
    @Transactional
    public void updateDepartment(DepartmentEntity department) {
        dao.updateDepartment(department);
    }

    @Override
    @Transactional
    public List<DepartmentEntity> getDepartments() {

        return dao.getDepartments();
    }

    @Override
    @Transactional
    public DepartmentEntity getDepartmentById(String departmentId) {
        return dao.getDepartmentById(departmentId);
    }

    @Override
    @Transactional
    public DepartmentEntity getDepartmentByName(String name) {
        return dao.getDepartmentByName(name);
    }
}
