package org.oht.schedule_project.repository;

import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.*;
import org.oht.schedule_project.dto.response.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    Optional<Schedule> insertSchedule(ScheduleInsertDto scheduleInsertDto);

    Optional<Schedule> schedule(Long id);

    Optional<List<Schedule>> schedules(ScheduleViewsDto scheduleViewsDto);

    Optional<Schedule> updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

    boolean deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

}
