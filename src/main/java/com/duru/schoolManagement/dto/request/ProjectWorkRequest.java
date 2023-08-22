package com.duru.schoolManagement.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWorkRequest {
    @NotBlank(message = "project topic cannot be blank" )
    private String projectTopic;
    @NotNull(message = "please enter a budget")
    private Double budget;
    private String department;
    @NotNull(message = "please enter a message")
    @NotBlank(message = "expected date cannot be plank")
    private LocalDate expectedDate;
}
