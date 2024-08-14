package org.oht.schedule_project.dto.request;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleInsertDto {

    private String toDo;

    private Long managerId;

    private String password;

    private String createdAt;

    private String updatedAt;

}
