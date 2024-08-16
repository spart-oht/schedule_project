package org.oht.schedule_project.service.manager;

import org.oht.schedule_project.domain.Manager;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;

public interface ManagerService {

    Manager editManager(ManagerInsertRequestDto managerInsertRequestDto);

}
