package org.oht.schedule_project.service;


import org.oht.schedule_project.dto.request.*;
import org.springframework.http.ResponseEntity;

public interface ScheduleService {

    // 일정 작성
    ResponseEntity<?> insertSchedule(ScheduleInsertDto scheduleInsertDto);

    // 선택한 일정 조회
    ResponseEntity<?> schedule(ScheduleViewDto scheduleViewDto);

    // 일정 목록 조회
    ResponseEntity<?> schedules(ScheduleViewsDto scheduleViewsDto);

    // 선택한 일정 수정
    ResponseEntity<?> updateSchedule(ScheduleUpdateDto scheduleUpdateDto);

    // 선택한 일정 삭제
    ResponseEntity<?> deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

}
