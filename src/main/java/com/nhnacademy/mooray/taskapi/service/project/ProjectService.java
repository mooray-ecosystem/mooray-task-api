package com.nhnacademy.mooray.taskapi.service.project;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;

public interface ProjectService {

    MoorayResult createProject(ProjectCreationRequest projectRequest);

    MoorayResult updateProject(ProjectUpdateRequest projectRequest);

}
