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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleImpl implements ScheduleService {

    private final ScheduleRepositoryImpl ScheduleRepository;

    @Override
    public ResponseEntity<?> insertSchedule(ScheduleInsertDto ScheduleInsertDto) {
        Schedule schedule = ScheduleRepository.insertSchedule(ScheduleInsertDto);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                        .id(schedule.getId())
                        .toDo(schedule.getToDo())
                        .managerId(schedule.getManagerId())
                        .createdAt(schedule.getCreatedAt())
                        .build();

        // Entity -> ResponseDto
        CommonResponseDto commonResponse = CommonResponseDto.builder()
                .status(StatusEnum.OK.getCode())
                .message("success")
                .data(scheduleResponseDto)
                .build();

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> schedule(ScheduleViewDto scheduleViewDto) {
        Schedule schedule = ScheduleRepository.schedule(scheduleViewDto);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(schedule.getId())
                .toDo(schedule.getToDo())
                .managerId(schedule.getManagerId())
                .createdAt(schedule.getCreatedAt())
                .build();

        // Entity -> ResponseDto
        CommonResponseDto commonResponse = CommonResponseDto.builder()
                .status(StatusEnum.OK.getCode())
                .message("success")
                .data(scheduleResponseDto)
                .build();

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> schedules(ScheduleViewsDto scheduleViewsDto){
        List<Schedule> schedules = ScheduleRepository.schedules(scheduleViewsDto);

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();

        for(Schedule schedule : schedules){
            scheduleResponseDtos.add(
                    ScheduleResponseDto.builder()
                    .id(schedule.getId())
                    .toDo(schedule.getToDo())
                    .managerId(schedule.getManagerId())
                    .createdAt(schedule.getCreatedAt())
                    .build());
        }

        // Entity -> ResponseDto
        CommonResponseDto commonResponse = CommonResponseDto.builder()
                .status(StatusEnum.OK.getCode())
                .message("success")
                .data(scheduleResponseDtos)
                .build();

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateSchedule(ScheduleUpdateDto scheduleUpdateDto){
        // 해당 메모가 DB에 존재하는지 확인
        Schedule updateSchedule = ScheduleRepository.updateSchedule(scheduleUpdateDto);

        CommonResponseDto commonResponse;

        if(updateSchedule == null){
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.BAD_REQUEST.getCode())
                    .message("비밀번호가 일치하지 않습니다.")
                    .data(updateSchedule)
                    .build();
        } else {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.OK.getCode())
                    .message("success")
                    .data(updateSchedule)
                    .build();
        }

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteSchedule(ScheduleDeleteDto scheduleDeleteDto){
        // 해당 메모가 DB에 존재하는지 확인
        Schedule deleteSchedule = ScheduleRepository.deleteSchedule(scheduleDeleteDto);

        CommonResponseDto commonResponse;

        if(deleteSchedule == null){
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.BAD_REQUEST.getCode())
                    .message("비밀번호가 일치하지 않습니다.")
                    .data(null)
                    .build();
        } else {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.OK.getCode())
                    .message("success")
                    .data(null)
                    .build();
        }

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

}
