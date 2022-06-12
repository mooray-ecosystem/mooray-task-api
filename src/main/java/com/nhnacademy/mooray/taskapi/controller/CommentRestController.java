package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/projects/{project-id}/tasks/{task-id}/comments")
    public ResponseEntity<Map<String, Object>> createComment(@PathVariable("project-id") Long projectId,
                                                             @PathVariable("task-id") Long taskId,
                                                             @RequestBody CommentCreationRequest commentRequest) {

        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter createComment");

        MoorayResult result = commentService.createComment(projectId, taskId, commentRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}/comments")
    public ResponseEntity<Map<String, Object>> retrieveComments(@PathVariable("project-id") Long projectId,
                                                                @PathVariable("task-id") Long taskId) {

        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter retrieveComments");

        MoorayResult result = commentService.retrieveComments(projectId, taskId);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<Map<String, Object>> retrieveComment(@PathVariable("project-id") Long projectId,
                                                               @PathVariable("task-id") Long taskId,
                                                               @PathVariable("comment-id") Long commentId) {

        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter retrieveComment");

        MoorayResult result = commentService.retrieveComment(projectId, taskId, commentId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

    @PutMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<Map<String, Object>> updateComment(@PathVariable("project-id") Long projectId,
                                                             @PathVariable("task-id") Long taskId,
                                                             @PathVariable("comment-id") Long commentId,
                                                             @RequestBody CommentUpdateRequest commentRequest) {

        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter updateComment");

        MoorayResult result = commentService.updateComment(projectId, taskId, commentId, commentRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

    @DeleteMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("project-id") Long projectId,
                                                             @PathVariable("task-id") Long taskId,
                                                             @PathVariable("comment-id") Long commentId) {

        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter deleteComment");

        MoorayResult result = commentService.deleteComment(projectId, taskId, commentId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

}
