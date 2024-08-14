package org.oht.schedule_project.dto.request.manager;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ManagerInsertRequestDto {

    @NotNull
    private String name;

    @Email
    private String email;

    private String createdAt;

    private String updatedAt;
}
