package org.oht.schedule_project.repository.schedule.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.schedule.ScheduleDeleteDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleInsertDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleUpdateDto;
import org.oht.schedule_project.dto.request.schedule.ScheduleViewsDto;
import org.oht.schedule_project.repository.schedule.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public Optional<Schedule> insertSchedule(ScheduleInsertDto scheduleInsertDto) {

        Schedule schedule = new Schedule(scheduleInsertDto);

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO todo_list (to_do, manager_id, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, schedule.getToDo());
                    preparedStatement.setLong(2, schedule.getManagerId());
                    preparedStatement.setString(3, schedule.getPassword());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(schedule.getCreatedAt()));
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(schedule.getCreatedAt()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return Optional.of(schedule);
    } // DB 저장

    @Override
    public Optional<Schedule> schedule(Long id) {
        // DB 조회
        String sql = "SELECT id, to_do, manager_id, created_at FROM todo_list WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setToDo(resultSet.getString("to_do"));
                schedule.setManagerId(resultSet.getLong("manager_id"));

                // 원하는 포맷을 지정
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                // LocalDateTime을 포맷된 문자열로 변환
                String formattedDateTime = resultSet.getTimestamp("created_at").toLocalDateTime().format(formatter);
                schedule.setCreatedAt(formattedDateTime);
                return Optional.of(schedule);
            } else {
                return Optional.empty();
            }
        }, id);
    }

    @Override
    public Optional<List<Schedule>> schedules(ScheduleViewsDto scheduleViewsDto) {
        String sql = "SELECT a.id, a.manager_id, a.to_do, b.name, a.created_at, b.updated_at FROM todo_list as a join manager as b on a.manager_id = b.id where 1=1";
        if(!scheduleViewsDto.getUpdatedAt().equals("")){
            sql += " and a.updated_at like '%"+scheduleViewsDto.getUpdatedAt()+"%'";
        }

        if(!scheduleViewsDto.getManagerId().equals("")){
            sql += " and a.manager_id = '"+scheduleViewsDto.getManagerId() + "'";
        }

        if((Integer) scheduleViewsDto.getPageSize() != null){
            sql += " LIMIT " + scheduleViewsDto.getPageSize();
        }

        if((Integer) scheduleViewsDto.getPageNum() != null){
            sql += " OFFSET " + scheduleViewsDto.getPageSize() * (scheduleViewsDto.getPageNum() - 1);
        }

        log.info(sql);

        return Optional.of(jdbcTemplate.query(sql, new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Schedule.builder()
                        .id(rs.getLong("id"))
                        .toDo(rs.getString("to_do"))
                        .managerId(rs.getLong("manager_id"))
                        .createdAt(rs.getString("created_at"))
                        .build();
            }
        }));

    }

    @Override
    @Transactional
    public Optional<Schedule> updateSchedule(ScheduleUpdateDto scheduleUpdateDto, Schedule schedule) {

        if(schedule.getPassword().equals(scheduleUpdateDto.getPassword())){
            // memo 내용 수정
            String sql = "UPDATE todo_list SET to_do = ?, manager_id = ?, updated_at = SYSDATE()  WHERE id = ?";
            jdbcTemplate.update(sql, schedule.getToDo(), schedule.getManagerId(), schedule.getId());

            return Optional.of(Schedule.builder()
                    .id(schedule.getId())
                    .toDo(schedule.getToDo())
                    .managerId(schedule.getManagerId())
                    .updatedAt(schedule.getUpdatedAt())
                    .build());
        }else{
            throw new NoSuchElementException("패스워드가 일치하지 않습니다.");
        }

    }

    @Override
    @Transactional
    public int deleteSchedule(ScheduleDeleteDto scheduleDeleteDto, Schedule schedule) {

        if (schedule.getPassword().equals(scheduleDeleteDto.getPassword())) {
            // memo 내용 수정
            String sql = "DELETE FROM todo_list WHERE id = ?";
            int update = jdbcTemplate.update(sql, schedule.getId());

            return update;
        } else {
            throw new NoSuchElementException("패스워드가 일치하지 않습니다.");
        }
    }

    public Optional<Schedule> findByScheduleId(Long id) {
        // DB 조회
        String sql = "SELECT id, to_do, manager_id, password, created_at, updated_at FROM todo_list WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong("id"));
                schedule.setToDo(resultSet.getString("to_do"));
                schedule.setManagerId(resultSet.getLong("manager_id"));
                schedule.setPassword(resultSet.getString("password"));
                schedule.setCreatedAt(resultSet.getString("created_at"));
                schedule.setUpdatedAt(resultSet.getString("updated_at"));
                return Optional.of(schedule);
            } else {
                return Optional.empty();
            }
        }, id);
    }

}
