package com.duru.schoolManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWorkResponse {
    private Long projectId;
    private Long writersId;
    private String projectTopic;
    private Long budget;
    private String department;
    private LocalDate expectedDate;
    private Boolean isAvailable;
}
