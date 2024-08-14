package org.oht.schedule_project.dto.request.schedule;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ScheduleUpdateDto {

    @NotNull
    private Long id;

    @Nullable
    @Size(max = 200, message = "필드는 최대 200자까지 허용됩니다.")
    private String toDo;

    private Long managerId;

    @NotNull
    private String password;

}
