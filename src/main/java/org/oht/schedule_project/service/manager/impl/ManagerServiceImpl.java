package org.oht.schedule_project.service.manager.impl;


import lombok.RequiredArgsConstructor;
import org.oht.schedule_project.domain.Manager;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;
import org.oht.schedule_project.repository.manager.ManagerRepository;
import org.oht.schedule_project.repository.manager.impl.ManagerRepositoryImpl;
import org.oht.schedule_project.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepositoryImpl managerRepository;

    @Override
    public Manager editManager(ManagerInsertRequestDto managerInsertRequestDto) {
        return Optional.ofNullable(
                managerRepository.insertManager(managerInsertRequestDto)).orElseThrow(
                () -> new RuntimeException("등록에 실패하였습니다.")
        );
    }
}
