package org.oht.schedule_project.repository;

import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.*;
import org.oht.schedule_project.dto.response.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleRepository {

    Schedule insertSchedule(ScheduleInsertDto scheduleInsertDto);

    Schedule schedule(ScheduleViewDto scheduleViewDto);

    List<Schedule> schedules(ScheduleViewsDto scheduleViewsDto);
//
//
    Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto);
//
    Schedule deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

}
