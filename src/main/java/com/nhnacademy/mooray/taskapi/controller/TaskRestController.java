package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping("/projects/{id}/tasks")
    public ResponseEntity<MoorayResult<Task>> createTask(@PathVariable Long id,
                                                         @Valid @RequestBody TaskCreationRequest taskRequest) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter createTask(..)");

        MoorayResult<Task> result = taskService.createTask(id, taskRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tasks")
    public ResponseEntity<MoorayResult<List<Task>>> retrieveTasks(@PathVariable("project-id") Long projectId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTasks()");

        MoorayResult<List<Task>> result = taskService.retrieveTasks(projectId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}")
    public ResponseEntity<MoorayResult<Task>> retrieveTask(@PathVariable("project-id") Long projectId,
                                                           @PathVariable("task-id") Long taskId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTask(..)");

        MoorayResult<Task> result = taskService.retrieveTask(projectId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @PutMapping("/projects/{project-id}/tasks/{task-id}")
    public ResponseEntity<MoorayResult<Task>> updateTask(@PathVariable("project-id") Long projectId,
                                                         @PathVariable("task-id") Long taskId,
                                                         @Valid @RequestBody TaskUpdateRequest taskRequest) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter updateTask(..)");

        MoorayResult<Task> result = taskService.updateTask(projectId, taskRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @DeleteMapping("/projects/{project-id}/tasks/{task-id}")
    public ResponseEntity<MoorayResult<Boolean>> deleteTask(@PathVariable("project-id") Long projectId,
                                                            @PathVariable("task-id") Long taskId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter deleteTask(..)");

        MoorayResult<Boolean> result = taskService.deleteTask(taskId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

}
