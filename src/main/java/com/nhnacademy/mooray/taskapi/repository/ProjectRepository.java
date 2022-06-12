package com.nhnacademy.mooray.taskapi.repository.project;

import com.nhnacademy.mooray.taskapi.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    boolean existsByName(String name);

}
