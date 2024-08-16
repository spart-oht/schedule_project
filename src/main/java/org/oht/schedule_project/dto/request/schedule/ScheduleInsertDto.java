package org.oht.schedule_project.dto.request.schedule;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleInsertDto {

    @NotNull(message = "제목은 필수입니다.")
    @Size(max = 200, message = "필드는 최대 200자까지 허용됩니다.")
    private String toDo;

    private Long managerId;

    @NotNull(message = "비밀번호는 필수 파라미터 입니다.")
    private String password;

}
