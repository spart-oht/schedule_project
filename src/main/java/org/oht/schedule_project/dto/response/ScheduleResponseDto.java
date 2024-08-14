package org.oht.schedule_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long id;

    private String toDo;

    private Long managerId;

    private String createdAt;

    private String updatedAt;

}
