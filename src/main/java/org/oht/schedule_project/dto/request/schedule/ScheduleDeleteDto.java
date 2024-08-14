package org.oht.schedule_project.dto.request.schedule;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ScheduleDeleteDto {

    @NotNull
    private Long id;

    @NotNull
    private String password;
}
