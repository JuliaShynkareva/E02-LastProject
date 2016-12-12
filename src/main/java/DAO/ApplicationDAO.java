package DAO;

import Entity.ApplicationEntity;

import java.util.List;

public interface ApplicationDAO {

    ApplicationEntity createApplication(ApplicationEntity application);

    void deleteApplication(ApplicationEntity application);

    void updateApplication(ApplicationEntity application);

    List<ApplicationEntity> getApplications();

    ApplicationEntity getApplicationById(int applicationId);

    ApplicationEntity getApplicationByClient(int clientId);

    ApplicationEntity getApplicationByEmployee(int employeeId);
}
