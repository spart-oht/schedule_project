package org.oht.schedule_project.service.schedule.impl;

import lombok.RequiredArgsConstructor;
import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.schedule.ScheduleDeleteDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleUpdateDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleViewsDto;
import org.oht.schedule_project.repository.manager.impl.ManagerRepositoryImpl;
import org.oht.schedule_project.repository.schedule.impl.ScheduleRepositoryImpl;
import org.oht.schedule_project.service.schedule.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepositoryImpl scheduleRepository;

    private final ManagerRepositoryImpl managerRepository;

    @Override
    public Schedule insertSchedule(ScheduleInsertDto ScheduleInsertDto) {

        Optional.ofNullable(managerRepository.findByManagerId(ScheduleInsertDto.getManagerId())).orElseThrow(() -> new NoSuchElementException("해당 id 로 등록된 매니저가 없습니다."));

        return Optional.ofNullable(scheduleRepository.insertSchedule(ScheduleInsertDto)).orElseThrow(() -> new RuntimeException("등록에 실패하였습니다."));
    }

    @Override
    public Schedule findIdSchedule(Long id) {
        return Optional.of(scheduleRepository.schedule(id)).orElse(new Schedule());
    }

    @Override
    public List<Schedule> schedules(ScheduleViewsDto scheduleViewsDto){
        return Optional.of(scheduleRepository.schedules(scheduleViewsDto)).orElse(new ArrayList<>());
    }

    @Override
    public Schedule updateSchedule(ScheduleUpdateDto scheduleUpdateDto){

        // 데이터 존재 확인
        Schedule schedule = Optional.ofNullable(scheduleRepository.findByScheduleId(scheduleUpdateDto.getId())).orElseThrow(() -> new NoSuchElementException("데이터가 존재하지 않습니다."));

        // 변경하려는 매니저의 데이터 확인
        Optional.ofNullable(managerRepository.findByManagerId(scheduleUpdateDto.getManagerId())).orElseThrow(() -> new NoSuchElementException("해당 id 로 등록된 매니저가 없습니다."));

        return Optional.of(scheduleRepository.updateSchedule(scheduleUpdateDto, schedule)).orElseThrow(() -> new RuntimeException("수정에 실패하였습니다."));
    }

    @Override
    public void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto){
        // 데이터 존재 확인
        Schedule schedule = Optional.ofNullable(scheduleRepository.findByScheduleId(scheduleDeleteDto.getId())).orElseThrow(() -> new NoSuchElementException("데이터가 존재하지 않습니다."));

        // 해당 메모가 DB에 존재하는지 확인
        if(scheduleRepository.deleteSchedule(scheduleDeleteDto, schedule) == 0) throw new RuntimeException("삭제에 실패하였습니다.");
    }

}
