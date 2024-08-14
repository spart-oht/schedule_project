package org.oht.schedule_project.service;


import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {

    // 일정 작성
    Schedule insertSchedule(ScheduleInsertDto scheduleInsertDto);

    // 선택한 일정 조회
    Schedule findIdSchedule(Long id);

    // 일정 목록 조회
    List<Schedule> schedules(ScheduleViewsDto scheduleViewsDto);

    // 선택한 일정 수정
    Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

    // 선택한 일정 삭제
    boolean deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

}
