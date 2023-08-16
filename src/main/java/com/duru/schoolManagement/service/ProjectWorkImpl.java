package com.duru.schoolManagement.service;

import com.duru.schoolManagement.data.model.ProjectWork;
import com.duru.schoolManagement.data.repository.ProjectWorkRepository;
import com.duru.schoolManagement.dto.request.ProjectWorkRequest;
import com.duru.schoolManagement.dto.response.ProjectWorkResponse;
import com.duru.schoolManagement.exception.NoAvailableProjectException;
import com.duru.schoolManagement.exception.ProjectAlreadyBookedException;
import com.duru.schoolManagement.exception.ProjectNotFoundException;
import com.duru.schoolManagement.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectWorkImpl implements ProjectWorkService {
    private final ProjectWorkRepository projectWorkRepository;

    //   private final ModelMapper mapper;
    @Override
    public ProjectWorkResponse createProjectProposal(ProjectWorkRequest projectWorkRequest) {
        if (projectWorkRequest.getProjectTopic() == null && projectWorkRequest.getDepartment() == null
                && projectWorkRequest.getDepartment()==null&& projectWorkRequest.getBudget()==null
                &&projectWorkRequest.getExpectedDate()==null ) {

            throw new IllegalArgumentException("project topic cannot be null");
        }
        ProjectWork projectWork = Mapper.map(projectWorkRequest);
        ProjectWork savedProjectWork = projectWorkRepository.save(projectWork);

        return Mapper.map(savedProjectWork);
    }

    @Override
    public List<ProjectWork> availableProject() {
        List<ProjectWork> availableProjectWork = new ArrayList<>();
        List<ProjectWork> projectWorkList = projectWorkRepository.findAll();
        for (int index = 0; index < projectWorkList.size(); index++) {
            log.info(String.valueOf(projectWorkList.get(index).getIsAvailable()));
            if (projectWorkList.get(index).getIsAvailable()) {
                availableProjectWork.add(projectWorkList.get(index));
            }
        }
        return availableProjectWork;
    }

    @Override
    public List<ProjectWork> assignedProject() {
        List<ProjectWork> assignedProjectWork = new ArrayList<>();
        List<ProjectWork> projectWorkList = projectWorkRepository.findAll();
        for (int index = 0; index < projectWorkList.size(); index++) {
            if (projectWorkList.get(index).getIsAvailable()) {//is Equals True
                assignedProjectWork.add(projectWorkList.get(index));
            }
        }
        return assignedProjectWork;
    }

    @Override
    public List<ProjectWork> getAllProject(){
        List<ProjectWork> getALllProject =projectWorkRepository.findAll();
        if (getALllProject.isEmpty()){
            throw  new NoAvailableProjectException("No Project Work");
        }
        else {
            return getALllProject;
        }
    }

    @Override
    public Optional<ProjectWork> getSpecificProject(Long projectId) throws ProjectNotFoundException{
      Optional<ProjectWork> returnedProject =      projectWorkRepository.findById(projectId);
      if (returnedProject.isEmpty()){
          throw new ProjectNotFoundException("no project with the id you gave me");
      }
      else {
          return returnedProject;
      }
    }

    @Override
    public ProjectWork bookProject(Long writersId, Long projectId) {
        Optional<ProjectWork> projectToBeBooked = getSpecificProject(projectId);
        if (projectToBeBooked.isPresent()) {
            if (!projectToBeBooked.get().getIsAvailable()) { // is Equals false
                projectToBeBooked.get().setIsAvailable(true);
                projectToBeBooked.get().setWriterId(writersId);
                projectWorkRepository.save(projectToBeBooked.get());
            }
                else {
                System.out.println(" igot here---->"+projectToBeBooked.get().getIsAvailable());
                throw new ProjectAlreadyBookedException("Project has already been booked");

            }
        }
        return projectToBeBooked.get();
    }

    @Override
    public List<ProjectWork> getProjectWorkForSpecificUser(Long userId){
                List<ProjectWork> project_works = new ArrayList<>();
                for (ProjectWork projectWork : getAllProject()) {
                    if (projectWork.getStudentId().equals(userId)) {
                        project_works.add(projectWork);
                    }
                }
                return project_works;
            }
    @Override
    public List<ProjectWork> getProjectWorkForSpecificWriter(Long writerId) {
        List<ProjectWork> project_works = new ArrayList<>();
        for (ProjectWork project_work : getAllProject()) {
            if (project_work.getWriterId().equals(writerId)) {
                project_works.add(project_work);
            }
        }
        return project_works;
    }

}









