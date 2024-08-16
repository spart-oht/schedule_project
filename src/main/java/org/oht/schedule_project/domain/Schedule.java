package org.oht.schedule_project.domain;

import lombok.*;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private Long id;

    private String toDo;

    private Long managerId;

    private String password;

    private String createdAt;

    private String updatedAt;


    public Schedule(ScheduleInsertDto scheduleInsertDto) {
        this.toDo = scheduleInsertDto.getToDo();
        this.managerId = scheduleInsertDto.getManagerId();
        this.password = scheduleInsertDto.getPassword();
    }

}
