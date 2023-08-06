package com.duru.schoolManagement.controller;

import com.duru.schoolManagement.data.model.ProjectWork;
import com.duru.schoolManagement.dto.request.ProjectWorkRequest;
import com.duru.schoolManagement.dto.response.ApiResponse;
import com.duru.schoolManagement.dto.response.ProjectWorkResponse;
import com.duru.schoolManagement.exception.NoAvailableProjectException;
import com.duru.schoolManagement.exception.ProjectNotFoundException;
import com.duru.schoolManagement.service.ProjectWorkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@AllArgsConstructor
@Slf4j
public class ProjectWorkController {
    ProjectWorkService projectWorkService;

    @PostMapping("/registerProject")
    public ResponseEntity<?> createProjectProposal(@RequestBody ProjectWorkRequest projectWorkRequest){
        log.info("{}", projectWorkRequest.toString());
        ProjectWorkResponse projectWork ;
        try {
         projectWork =   projectWorkService.createProjectProposal(projectWorkRequest);

        }catch (IllegalArgumentException e){
          return  new ResponseEntity<>(new ApiResponse(false,e.getMessage()),
                  HttpStatus.BAD_REQUEST);
        }
      return   new ResponseEntity<>(new ApiResponse(true,projectWork),
                HttpStatus.CREATED);

    }
    @GetMapping("/projects/{projectId}")
      public ResponseEntity<?>getSpecificProject(@PathVariable Long projectId) {
        Optional<ProjectWork> projectWork;
        try {
            projectWork =  projectWorkService.getSpecificProject(projectId);


        }catch (ProjectNotFoundException e){
            return  new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(new ApiResponse(true,projectWork.get()),
                HttpStatus.OK);
    }
    @GetMapping("/availableProjects")
    public ResponseEntity<?> getAvailableProject() {
        List<ProjectWork> projectWork;
        try {
            log.info("before method call");
            projectWork = projectWorkService.availableProject();
            log.info("After method call");
            log.info(projectWork.get(0).getProjectTopic());
        } catch (NoAvailableProjectException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true, projectWork),
                HttpStatus.OK);
    }
    @GetMapping("/projects/all")
    public ResponseEntity<?>getAllProject()  {
        List<ProjectWork> projectWorkList;
        try {
            projectWorkList = projectWorkService.getAllProject();
            return  new ResponseEntity<>(new ApiResponse(true,projectWorkList), HttpStatus.FOUND);
        }catch (NoAvailableProjectException e){
            return  new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/assignedProjects")
    public  ResponseEntity<?>assignedProject(){
             List<ProjectWork> assignedProjectWork =projectWorkService.assignedProject();
        return new ResponseEntity<>(new ApiResponse(true,assignedProjectWork),HttpStatus.FOUND);
    }






}
