package com.duru.schoolManagement.data.repository;

import com.duru.schoolManagement.data.model.ProjectWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ProjectWorkRepository  extends  JpaRepository<ProjectWork,Long>{

}


