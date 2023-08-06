package com.duru.schoolManagement.utils;

import com.duru.schoolManagement.data.model.ProjectWork;
import com.duru.schoolManagement.dto.request.ProjectWorkRequest;
import com.duru.schoolManagement.dto.response.ProjectWorkResponse;

public class Mapper {
    public static ProjectWork map(ProjectWorkRequest projectWorkRequest) {
          ProjectWork projectWork = new ProjectWork();
          projectWork.setBudget(projectWorkRequest.getBudget());
          projectWork.setIsAvailable(true);
          projectWork.setStatus("uncompleted");
//          projectWork.setWriterId(projectWorkRequest.getWritersId());
          projectWork.setProjectTopic(projectWorkRequest.getProjectTopic());
          projectWork.setExpectedDate(projectWorkRequest.getExpectedDate());
          projectWork.setDepartment(projectWorkRequest.getDepartment());
          return projectWork;
    }
    public static ProjectWorkResponse map(ProjectWork projectWork) {
        ProjectWorkResponse projectWorkResponse = new ProjectWorkResponse();
        projectWorkResponse.setProjectTopic(projectWork.getProjectTopic());
        projectWorkResponse.setProjectId(projectWork.getProjectId());
        projectWorkResponse.setIsAvailable(projectWork.getIsAvailable());
//        projectWorkResponse.setWritersId(projectWork.getWriterId());
        projectWorkResponse.setDepartment(projectWork.getDepartment());
        projectWorkResponse.setExpectedDate(projectWork.getExpectedDate());
        projectWorkResponse.setBudget(projectWork.getBudget());
        return projectWorkResponse;
    }

}
