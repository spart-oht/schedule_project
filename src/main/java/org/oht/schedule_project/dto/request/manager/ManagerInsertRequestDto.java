package org.oht.schedule_project.dto.request.manager;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ManagerInsertRequestDto {

    @NotNull(message = "이름은 필수값 입니다.")
    private String name;

    @NotNull(message = "이메일은 필수값 입니다.")
    @Email(message = "잘못된 형식의 email 입니다.")
    private String email;
}
