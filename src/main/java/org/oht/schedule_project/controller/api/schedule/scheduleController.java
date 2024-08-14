package org.oht.schedule_project.controller.api.schedule;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oht.schedule_project.dto.request.*;
import org.oht.schedule_project.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class scheduleController {

    private final ScheduleService service;

    @PostMapping("/edit")
    public ResponseEntity<?> insertSchedule(@RequestBody ScheduleInsertDto scheduleInsertDto) {
        return service.insertSchedule(scheduleInsertDto);
    }

    @GetMapping("/{id}")
    // @PathVariable Long id 사용가능
    public ResponseEntity<?> scheduleView(@RequestBody ScheduleViewDto scheduleViewDto){
        return service.schedule(scheduleViewDto);
    }

    @GetMapping
    public ResponseEntity<?> scheduleViews(@RequestBody ScheduleViewsDto scheduleViewsDto){
        return service.schedules(scheduleViewsDto);
    }

    @PutMapping("/update/{id}")
    // @PathVariable Long id,
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleUpdateDto scheduleUpdateDto){
        return service.updateSchedule(scheduleUpdateDto);
    }

    @DeleteMapping("/delete/{id}")
    // @PathVariable Long id,
    public ResponseEntity<?> deleteSchedule(@RequestBody ScheduleDeleteDto scheduleDeleteDto){
        return service.deleteSchedule(scheduleDeleteDto);
    }

}
