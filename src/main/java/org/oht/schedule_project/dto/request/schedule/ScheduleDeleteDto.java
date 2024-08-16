package org.oht.schedule_project.dto.request.schedule;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleDeleteDto {

    @NotNull(message = "id는 필수 파라미터 입니다.")
    private Long id;

    @NotNull(message = "비밀번호는 필수 파라미터 입니다.")
    private String password;
}
