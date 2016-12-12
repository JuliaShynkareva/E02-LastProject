package Service.Impl;

import DAO.EmployeeDAO;
import Entity.EmployeeEntity;
import Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    public EmployeeDAO dao;

    @Override
    @Transactional
    public EmployeeEntity createEmployee(EmployeeEntity employee) {
        return dao.createEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(EmployeeEntity employee) {
        dao.deleteEmployee(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(EmployeeEntity employee) {
        dao.updateEmployee(employee);
    }

    @Override
    @Transactional
    public List<EmployeeEntity> getEmployees() {
        return dao.getEmployees();
    }

    @Override
    @Transactional
    public EmployeeEntity getEmployeeById(String employeeId) {
        return dao.getEmployeeById(employeeId);
    }

    @Override
    public EmployeeEntity getEmployeeByLogin(String login) {
        return dao.getEmployeeByLogin(login);
    }

    @Override
    @Transactional
    public EmployeeEntity getEmployeeBySurname(String surname) {
        return dao.getEmployeeBySurname(surname);
    }
}
