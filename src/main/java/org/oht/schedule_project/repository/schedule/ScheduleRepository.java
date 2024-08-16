package org.oht.schedule_project.repository.schedule;

import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.schedule.ScheduleDeleteDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleUpdateDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleViewsDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Schedule insertSchedule(ScheduleInsertDto scheduleInsertDto);

    Schedule schedule(Long id);

    List<Schedule> schedules(ScheduleViewsDto scheduleViewsDto);

    Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto, Schedule schedule);

    int deleteSchedule(ScheduleDeleteDto scheduleDeleteDto, Schedule schedule);

}
