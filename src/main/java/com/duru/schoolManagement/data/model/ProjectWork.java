package com.duru.schoolManagement.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ProjectWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
//    private Long roleId;
    private Long studentId;
    private Long writerId;
    private String projectTopic;
    private  Double budget;
    @NotBlank(message = "department cannot be blank")
    @NotNull(message = "please enter a department")
    private String department;
    private LocalDate expectedDate;
    private Boolean isAvailable;
    private String status;

}
