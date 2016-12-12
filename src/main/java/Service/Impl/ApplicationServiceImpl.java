package Service.Impl;

import DAO.ApplicationDAO;
import Entity.ApplicationEntity;
import Service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationDAO dao;

    @Override
    @Transactional
    public ApplicationEntity createApplication(ApplicationEntity application) {
        return dao.createApplication(application);
    }

    @Override
    @Transactional
    public void deleteApplication(ApplicationEntity application) {
        dao.deleteApplication(application);
    }

    @Override
    @Transactional
    public void updateApplication(ApplicationEntity application) {
        dao.updateApplication(application);
    }

    @Override
    @Transactional
    public List<ApplicationEntity> getApplications() {
        return dao.getApplications();
    }

    @Override
    @Transactional
    public ApplicationEntity getApplicationById(int applicationId) {
        return dao.getApplicationById(applicationId);
    }

    @Override
    @Transactional
    public ApplicationEntity getApplicationByClient(int clientId) {
        return dao.getApplicationByClient(clientId);
    }

    @Override
    @Transactional
    public ApplicationEntity getApplicationByEmployee(int employeeId) {
        return dao.getApplicationByEmployee(employeeId);
    }
}
