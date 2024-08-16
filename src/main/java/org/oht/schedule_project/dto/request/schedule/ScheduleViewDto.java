package org.oht.schedule_project.dto.request.schedule;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleViewDto {

    @NotNull(message = "id는 필수 파라미터 입니다.")
    private Long id;

}
