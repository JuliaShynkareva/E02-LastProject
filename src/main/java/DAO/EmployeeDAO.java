package DAO;

import Entity.EmployeeEntity;

import java.util.List;

public interface EmployeeDAO {

    EmployeeEntity createEmployee(EmployeeEntity employee);

    void deleteEmployee(EmployeeEntity employee);

    void updateEmployee(EmployeeEntity employee);

    List<EmployeeEntity> getEmployees();

    EmployeeEntity getEmployeeById(String employeeId);

    EmployeeEntity getEmployeeByLogin(String login);

    EmployeeEntity getEmployeeBySurname(String surname);
}
