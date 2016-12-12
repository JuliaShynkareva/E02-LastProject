package DAO;

import Entity.StatusEntity;

import java.util.List;

public interface StatusDAO {

    StatusEntity createStatus(StatusEntity status);

    void deleteStatus(StatusEntity status);

    void updateStatus(StatusEntity status);

    List<StatusEntity> getStatuses();

    StatusEntity getStatusById(int statusId);
}
