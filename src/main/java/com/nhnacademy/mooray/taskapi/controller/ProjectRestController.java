package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProjectRestController {

    private final ProjectService projectService;

    @PostMapping("/projects")
    public ResponseEntity<Map<String, Object>> createProject(@Valid @RequestBody ProjectCreationRequest projectRequest) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.ProjectRestController: Enter createProject");

        // TODO: 게이트웨이에서 관리자인지 체크 여부 로직 있어야 함
        MoorayResult projectResponse = projectService.createProject(projectRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(projectResponse.getPayload());
    }

    @PutMapping("/projects")
    public ResponseEntity<Map<String, Object>> updateProject(@Valid @RequestBody ProjectUpdateRequest projectRequest) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.ProjectRestController: Enter updateProject");

        MoorayResult projectResponse = projectService.updateProject(projectRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(projectResponse.getPayload());
    }

}
