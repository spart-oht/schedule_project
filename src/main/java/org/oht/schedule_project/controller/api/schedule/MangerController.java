package org.oht.schedule_project.controller.api.schedule;


import jakarta.validation.Valid;
import org.oht.schedule_project.domain.Manager;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;
import org.oht.schedule_project.dto.response.CommonResponseDto;
import org.oht.schedule_project.dto.response.manager.ManagerResponseDto;
import org.oht.schedule_project.service.manager.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class MangerController {

    @Autowired
    ManagerService managerService;

    @PutMapping("/edit")
    public ResponseEntity<CommonResponseDto<ManagerResponseDto>> insertManager(@Valid @RequestBody ManagerInsertRequestDto managerInsertRequestDto){
        Manager newManager = managerService.editManager(managerInsertRequestDto);

        ManagerResponseDto managerResponseDto = ManagerResponseDto.builder()
                .id(newManager.getId())
                .name(newManager.getName())
                .email(newManager.getEmail())
                .createdAt(newManager.getCreatedAt())
                .build();

        return new ResponseEntity<>(new CommonResponseDto<>(HttpStatus.OK, "success", managerResponseDto), HttpStatus.OK);
    }

}
