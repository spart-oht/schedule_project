package org.oht.schedule_project.controller.api.schedule;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.schedule.ScheduleDeleteDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleUpdateDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleViewsDto;
import org.oht.schedule_project.dto.response.CommonResponseDto;
import org.oht.schedule_project.dto.response.schedule.ScheduleResponseDto;
import org.oht.schedule_project.enums.StatusEnum;
import org.oht.schedule_project.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/edit")
    public ResponseEntity<?> insertSchedule(@Valid @RequestBody ScheduleInsertDto scheduleInsertDto) {

        Schedule schedule = scheduleService.insertSchedule(scheduleInsertDto);

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

    @GetMapping("/{id}")
    // @PathVariable Long id 사용가능
    public ResponseEntity<?> scheduleView(@PathVariable Long id) {

        Schedule schedule = scheduleService.findIdSchedule(id);

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

    @GetMapping
    public ResponseEntity<?> scheduleViews(@RequestBody ScheduleViewsDto scheduleViewsDto, @RequestParam int pageNum, @RequestParam int pageSize) {

        // 페이징 처리용
        scheduleViewsDto.setPageNum(pageNum);
        scheduleViewsDto.setPageSize(pageSize);

        List<Schedule> schedules = scheduleService.schedules(scheduleViewsDto);

        List<ScheduleResponseDto> scheduleResponseDtos = new ArrayList<>();

        for (Schedule schedule : schedules) {
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

    @PutMapping("/update")
    // 보안에 취약하기 떄문에 PathVariable 은 생략
    // @PathVariable Long id,
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleUpdateDto scheduleUpdateDto) {

        Schedule updateSchedule = scheduleService.updateSchedule(scheduleUpdateDto);

        ScheduleResponseDto scheduleResponseDto = ScheduleResponseDto.builder()
                .id(updateSchedule.getId())
                .managerId(updateSchedule.getManagerId())
                .updatedAt(updateSchedule.getUpdatedAt())
                .build();

        CommonResponseDto commonResponse;

        if (updateSchedule == null) {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.BAD_REQUEST.getCode())
                    .message("비밀번호가 일치하지 않습니다.")
                    .data(scheduleResponseDto)
                    .build();
        } else {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.OK.getCode())
                    .message("success")
                    .data(scheduleResponseDto)
                    .build();
        }

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    // 보안에 취약하기 떄문에 PathVariable 은 생략
    // @PathVariable Long id,
    public ResponseEntity<?> deleteSchedule(@RequestBody ScheduleDeleteDto scheduleDeleteDto) {
        boolean deleteSchedule = scheduleService.deleteSchedule(scheduleDeleteDto);

        CommonResponseDto commonResponse;

        if (deleteSchedule == true) {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.OK.getCode())
                    .message("success")
                    .data(null)
                    .build();
        } else {
            commonResponse = CommonResponseDto.builder()
                    .status(StatusEnum.BAD_REQUEST.getCode())
                    .message("비밀번호가 일치하지 않습니다.")
                    .data(null)
                    .build();
        }

        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }

}
