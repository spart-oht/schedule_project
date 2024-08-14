package org.oht.schedule_project.dto.response.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor

// jackson 라이브러리를 사용하는 경우
// dto 클래스를 사용하는 경우 null 인 값은 직렬화에서 제외함
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {

    private Long id;

    private String toDo;

    private Long managerId;

    private String createdAt;

    private String updatedAt;

}
