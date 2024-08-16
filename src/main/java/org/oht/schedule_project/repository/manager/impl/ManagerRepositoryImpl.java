package org.oht.schedule_project.repository.manager.impl;

import lombok.RequiredArgsConstructor;
import org.oht.schedule_project.domain.Manager;
import org.oht.schedule_project.domain.Schedule;
import org.oht.schedule_project.dto.request.manager.ManagerInsertRequestDto;
import org.oht.schedule_project.repository.manager.ManagerRepository;
import org.oht.schedule_project.service.manager.ManagerService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ManagerRepositoryImpl implements ManagerRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public Optional<Manager> insertManager(ManagerInsertRequestDto managerInsertRequestDto) {
        Manager manager = new Manager(managerInsertRequestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO manager (name, email, created_at, updated_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, manager.getName());
                    preparedStatement.setString(2, manager.getEmail());
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        manager.setId(id);

        return Optional.of(manager);
    }

    public Optional<Manager> findByManagerId(Long id) {
        // DB 조회
        String sql = "SELECT id, name, email, created_at, updated_at FROM manager WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Manager manager = new Manager();
                manager.setId(resultSet.getLong("id"));
                manager.setName(resultSet.getString("name"));
                manager.setEmail(resultSet.getString("email"));
                manager.setCreatedAt(resultSet.getString("created_at"));
                manager.setUpdatedAt(resultSet.getString("updated_at"));
                return Optional.of(manager);
            } else {
                return Optional.empty();
            }
        }, id);
    }
}
