package com.nhnacademy.mooray.taskapi.service.project;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult<Project> createProject(ProjectCreationRequest projectRequest) {
        if (projectRepository.existsByName(projectRequest.getName())) {
            return MoorayResult.fail("프로젝트가 이미 존재합니다.");
        }

        Project project = Project.create(projectRequest);
        Project savedProject = projectRepository.save(project);

        Map<String, Project> payload = new HashMap<>();
        payload.put("data", savedProject);

        return MoorayResult.success("프로젝트를 성공적으로 생성했습니다.", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Project> updateProject(Long id, ProjectUpdateRequest projectRequest) {
        log.info("c.n.mooray.taskapi.service.project.ProejctServiceImpl: Enter updateProject(..)");

        Project foundProject = projectRepository.findById(id)
                                                .orElseThrow(RuntimeException::new);

        Project updatedProject = Project.create(foundProject, projectRequest);
        Project savedProject = projectRepository.save(updatedProject);

        Map<String, Project> payload = new HashMap<>();
        payload.put("data", savedProject);

        return MoorayResult.success("프로젝트 상태를 성공적으로 수정했습니다", payload);
    }

}
