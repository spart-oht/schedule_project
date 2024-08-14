package org.oht.schedule_project.dto.response;


import lombok.Builder;
import lombok.Getter;
import org.oht.schedule_project.enums.StatusEnum;

@Getter
@Builder
public class CommonResponseDto {

    private int status;
    private String message;
    private Object data;

}
