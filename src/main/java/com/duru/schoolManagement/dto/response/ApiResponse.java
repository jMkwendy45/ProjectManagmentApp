package com.duru.schoolManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean isSuccessful;
    private Object data;
}
