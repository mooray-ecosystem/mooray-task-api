package com.nhnacademy.mooray.taskapi.service;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult createProject(ProjectCreationRequest projectRequest) {
        Project project = Project.create(projectRequest);

        Project savedProject = projectRepository.save(project);
        return MoorayResult.success();
    }

    @Transactional
    @Override
    public MoorayResult updateProject(ProjectUpdateRequest projectRequest) {
        return null;
    }

}
