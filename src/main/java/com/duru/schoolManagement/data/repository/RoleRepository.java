package com.duru.schoolManagement.data.repository;

import com.duru.schoolManagement.data.model.Role;
import com.duru.schoolManagement.data.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByRoleType(RoleType roleType);
}
