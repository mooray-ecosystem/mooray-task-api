package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TaskRestController {

    private final TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<MoorayResult> createTask(@Valid @RequestBody TaskCreationRequest taskRequest) {
        // FIXME: Remove logging
        log.debug("[TaskRestController.createTask]");

        MoorayResult result = taskService.createTask(taskRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/tasks")
    public ResponseEntity<MoorayResult> getTasks() {
        // FIXME: Remove logging
        log.debug("[TaskRestController.getTasks]");

        MoorayResult result = taskService.getTasks();

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<MoorayResult> getTask(@PathVariable Long id) {
        // FIXME: Remove logging
        log.debug("[TaskRestController.getTasks]");

        MoorayResult result = taskService.getTasks();

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);

    }

}
