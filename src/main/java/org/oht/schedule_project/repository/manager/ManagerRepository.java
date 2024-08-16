package org.oht.schedule_project.repository.manager;

import org.oht.schedule_project.domain.Manager;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;

import java.util.Optional;

public interface ManagerRepository {

    Manager insertManager(ManagerInsertRequestDto managerInsertRequestDto);

}
