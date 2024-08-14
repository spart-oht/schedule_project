package org.oht.schedule_project.domain;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    private Long id;

    private String name;

    private String email;

    private String createdAt;

    private String updatedAt;

}
