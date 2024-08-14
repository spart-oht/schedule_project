package org.oht.schedule_project.dto.request.schedule;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleViewDto {

    @NotNull
    private Long id;

}
