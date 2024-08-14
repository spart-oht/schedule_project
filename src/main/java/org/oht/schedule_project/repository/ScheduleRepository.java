package org.oht.schedule_project.repository;

import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.schedule.ScheduleDeleteDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleUpdateDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleViewsDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Optional<Schedule> insertSchedule(ScheduleInsertDto scheduleInsertDto);

    Optional<Schedule> schedule(Long id);

    Optional<List<Schedule>> schedules(ScheduleViewsDto scheduleViewsDto);

    Optional<Schedule> updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

    boolean deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

}
