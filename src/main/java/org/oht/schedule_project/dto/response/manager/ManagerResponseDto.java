package org.oht.schedule_project.dto.response.manager;

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
public class ManagerResponseDto {

    private Long id;

    private String name;

    private String email;

    private String createdAt;

    private String updatedAt;

}
