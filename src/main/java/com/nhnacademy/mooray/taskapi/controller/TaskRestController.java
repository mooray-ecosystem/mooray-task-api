package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * TODO: Project 멤버는 Task 를 등록, 수정, 삭제 할 수 있습니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping("/projects/{id}/tasks")
    public ResponseEntity<MoorayResult> createTask(@PathVariable Long id,
                                                   @Valid @RequestBody TaskCreationRequest taskRequest) {

        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter createTask(..)");

        MoorayResult result = taskService.createTask(id, taskRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{id}/tasks")
    public ResponseEntity<MoorayResult> retrieveTasks() {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTasks()");

        MoorayResult result = taskService.retrieveTasks();

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}")
    public ResponseEntity<MoorayResult> retrieveTask(@PathVariable("project-id") Long projectId,
                                                     @PathVariable("task-id") Long taskId) {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTask(..)");

        MoorayResult result = taskService.retrieveTask(projectId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<MoorayResult> updateTask(@PathVariable Long id,
                                                   @Valid @RequestBody TaskUpdateRequest taskRequest) {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter updateTask(..)");

        MoorayResult result = taskService.updateTask(id, taskRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<MoorayResult> deleteTask(@PathVariable Long id) {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter deleteTask(..)");

        MoorayResult result = taskService.deleteTask(id);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

}
