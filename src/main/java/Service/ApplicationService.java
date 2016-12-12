package Service;

import Entity.ApplicationEntity;

import java.util.List;

public interface ApplicationService {

    ApplicationEntity createApplication(ApplicationEntity application);

    void deleteApplication(ApplicationEntity application);

    void updateApplication(ApplicationEntity application);

    List<ApplicationEntity> getApplications();

    ApplicationEntity getApplicationById(int applicationId);

    ApplicationEntity getApplicationByClient(int clientId);

    ApplicationEntity getApplicationByEmployee(int employeeId);
}
