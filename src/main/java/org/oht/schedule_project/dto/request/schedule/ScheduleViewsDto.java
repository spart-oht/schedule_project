package org.oht.schedule_project.dto.request.schedule;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleViewsDto {

    private String updatedAt;

    private Long managerId;

    private int pageNum;

    private int pageSize;
}
