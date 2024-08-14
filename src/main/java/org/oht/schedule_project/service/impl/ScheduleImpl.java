package org.oht.schedule_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.*;
import org.oht.schedule_project.dto.response.CommonResponseDto;
import org.oht.schedule_project.dto.response.ScheduleResponseDto;
import org.oht.schedule_project.enums.StatusEnum;
import org.oht.schedule_project.repository.impl.ScheduleRepositoryImpl;
import org.oht.schedule_project.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleImpl implements ScheduleService {

    private final ScheduleRepositoryImpl scheduleRepository;

    @Override
    public Schedule insertSchedule(ScheduleInsertDto ScheduleInsertDto) {
        Optional<Schedule> schedule = Optional.ofNullable(scheduleRepository.insertSchedule(ScheduleInsertDto).orElseThrow(() -> new RuntimeException("등록에 실패하였습니다.")));

        return schedule.get();
    }

    @Override
    public Schedule findIdSchedule(Long id) {
        Optional<Schedule> schedule = Optional.of(scheduleRepository.schedule(id).orElse(new Schedule()));
        return schedule.get();
    }

    @Override
    public List<Schedule> schedules(ScheduleViewsDto scheduleViewsDto){
        Optional<List<Schedule>> schedules = Optional.of(scheduleRepository.schedules(scheduleViewsDto).orElse(new ArrayList<>()));

        return schedules.get();
    }

    @Override
    public Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto){
        // 해당 메모가 DB에 존재하는지 확인
        Optional<Schedule> updateSchedule = Optional.of(scheduleRepository.updateSchedule(scheduleUpdateDto).orElse(new Schedule()));

        return updateSchedule.get();
    }

    @Override
    public boolean deleteSchedule(ScheduleDeleteDto scheduleDeleteDto){
        // 해당 메모가 DB에 존재하는지 확인
        boolean deleteSchedule = scheduleRepository.deleteSchedule(scheduleDeleteDto);
        return deleteSchedule;
    }

}
