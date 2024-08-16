package org.oht.schedule_project.domain;


import lombok.*;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    private Long id;

    private String name;

    private String email;

    private String createdAt;

    private String updatedAt;

    public Manager(ManagerInsertRequestDto managerInsertRequestDto){
        this.name = managerInsertRequestDto.getName();
        this.email = managerInsertRequestDto.getEmail();
    }

}
