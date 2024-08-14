package org.oht.schedule_project.dto.request;


import lombok.Getter;

@Getter
public class ScheduleUpdateDto {

    private Long id;

    private String toDo;

    private Long managerId;

    private String password;

}
