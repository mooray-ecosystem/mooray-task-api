package com.nhnacademy.mooray.taskapi.service.project;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult createProject(ProjectCreationRequest projectRequest) {
        if (projectRepository.existsByName(projectRequest.getName())) {
            // FIXME: Custom exc.
            return MoorayResult.fail("프로젝트가 이미 존재합니다.");
        }

        Project project = Project.create(projectRequest);
        Project savedProject = projectRepository.save(project);

        Map<String, Object> payload = new HashMap<>();
        payload.put("data", savedProject);

        return MoorayResult.success("프로젝트를 성공적으로 생성했습니다.", payload);
    }

    @Transactional
    @Override
    public MoorayResult updateProject(ProjectUpdateRequest projectRequest) {
        return null;
    }

}
