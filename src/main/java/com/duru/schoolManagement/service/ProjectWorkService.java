package com.duru.schoolManagement.service;

import com.duru.schoolManagement.data.model.ProjectWork;
import com.duru.schoolManagement.dto.request.ProjectWorkRequest;
import com.duru.schoolManagement.dto.response.ProjectWorkResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface ProjectWorkService {
     ProjectWorkResponse createProjectProposal (ProjectWorkRequest projectWorkRequest);
     List<ProjectWork> availableProject();
     List<ProjectWork>assignedProject();
     List<ProjectWork>getAllProject();
     Optional<ProjectWork>getSpecificProject(Long projectId);
     ProjectWork bookProject(Long writersId,Long projectId);

     List<ProjectWork> getProjectWorkForSpecificUser(Long userId);

     List<ProjectWork> getProjectWorkForSpecificWriter(Long WriterId);
}
