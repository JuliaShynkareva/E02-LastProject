package Service;

import Entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {

    DepartmentEntity createDepartment(DepartmentEntity department);

    void deleteDepartment(DepartmentEntity department);

    void updateDepartment(DepartmentEntity department);

    List<DepartmentEntity> getDepartments();

    DepartmentEntity getDepartmentById(String departmentId);

    DepartmentEntity getDepartmentByName(String name);
}
