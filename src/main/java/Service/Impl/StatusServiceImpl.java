package Service.Impl;

import DAO.StatusDAO;
import Entity.StatusEntity;
import Service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("statusService")
public class StatusServiceImpl implements StatusService {

    @Autowired
    public StatusDAO dao;

    @Override
    @Transactional
    public StatusEntity createStatus(StatusEntity status) {
        return dao.createStatus(status);
    }

    @Override
    @Transactional
    public void deleteStatus(StatusEntity status) {
        dao.deleteStatus(status);
    }

    @Override
    @Transactional
    public void updateStatus(StatusEntity status) {
        dao.updateStatus(status);
    }

    @Override
    @Transactional
    public List<StatusEntity> getStatuses() {
        return dao.getStatuses();
    }

    @Override
    @Transactional
    public StatusEntity getStatusById(int statusId) {
        return dao.getStatusById(statusId);
    }
}
