package com.duru.schoolManagement.service;

import com.duru.schoolManagement.data.model.ProjectWork;
import com.duru.schoolManagement.data.repository.ProjectWorkRepository;
import com.duru.schoolManagement.dto.request.ProjectWorkRequest;
import com.duru.schoolManagement.dto.response.ProjectWorkResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProjectServiceTest {
    @Autowired
    ProjectWorkService projectWorkService;
    @Autowired
    ProjectWorkRepository projectWorkRepository;

    //    @AfterEach
//    public void tearDown(){
//        projectWorkRepository.deleteAll();
//    }
    @Test
    public void testToRegisterAProjectWork() {
        ProjectWorkRequest projectWorkRequest = new ProjectWorkRequest();

        projectWorkRequest.setProjectTopic("goo");
        projectWorkRequest.setDepartment("vomartt");
        projectWorkRequest.setBudget(950L);
        projectWorkRequest.setExpectedDate(LocalDate.now());

        ProjectWorkResponse projectWorkResponse = projectWorkService.createProjectProposal(projectWorkRequest);
        assertNotNull(projectWorkResponse);
    }
    @Test
    public void testToFindAssignedProjectWork() {
        List<ProjectWork> projectWorkList = projectWorkService.assignedProject();
        assertEquals(1, projectWorkList.size());
    }
    @Test
    public void testForAvailableProject() {
        List<ProjectWork> projectWorkList = projectWorkService.availableProject();
        assertEquals(1, projectWorkList.size());
    }

    @Test
    public void testToGetAllProject() {
        List<ProjectWork> projectWorkList = projectWorkService.getAllProject();
        assertEquals(1, projectWorkList.size());
    }
    @Test
    public void testForSpecificProject() {
        Optional<ProjectWork> projectWork = projectWorkService.getSpecificProject(1152L);
        assertTrue(projectWork.isPresent());
        assertEquals(1152L, projectWork.get().getProjectId());
    }
    @Test
    public void testToBookAProject() {
        Optional<ProjectWork> specificProject = projectWorkService.getSpecificProject(1252L);
        projectWorkService.bookProject(899L,1252L);
    List<ProjectWork>projectWorks =   projectWorkService.assignedProject();
        System.out.println(projectWorks);
        assertTrue(specificProject.isPresent());
    }
}

