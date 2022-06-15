package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Comment;
import com.nhnacademy.mooray.taskapi.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/projects/{project-id}/tasks/{task-id}/comments")
    public ResponseEntity<MoorayResult<Comment>> createComment(@PathVariable("project-id") Long projectId,
                                                               @PathVariable("task-id") Long taskId,
                                                               @RequestBody CommentCreationRequest commentRequest) {

        log.info("c.n.mooray.taskapi.controller.CommentRestController: Enter createComment");

        MoorayResult<Comment> result = commentService.createComment(projectId, taskId, commentRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}/comments")
    public ResponseEntity<MoorayResult<List<Comment>>> retrieveComments(@PathVariable("project-id") Long projectId,
                                                                        @PathVariable("task-id") Long taskId) {

        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter retrieveComments");

        MoorayResult<List<Comment>> result = commentService.retrieveComments(projectId, taskId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<MoorayResult<Comment>> retrieveComment(@PathVariable("project-id") Long projectId,
                                                                 @PathVariable("task-id") Long taskId,
                                                                 @PathVariable("comment-id") Long commentId) {

        log.info("c.n.mooray.taskapi.controller.CommentRestController: Enter retrieveComment");

        MoorayResult<Comment> result = commentService.retrieveComment(projectId, taskId, commentId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result);
    }

    @PutMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<MoorayResult<Comment>> updateComment(@PathVariable("project-id") Long projectId,
                                                               @PathVariable("task-id") Long taskId,
                                                               @PathVariable("comment-id") Long commentId,
                                                               @RequestBody CommentUpdateRequest commentRequest) {

        log.info("c.n.mooray.taskapi.controller.CommentRestController: Enter updateComment");

        MoorayResult<Comment> result = commentService.updateComment(projectId, taskId, commentId, commentRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result);
    }

    @DeleteMapping("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}")
    public ResponseEntity<MoorayResult<Boolean>> deleteComment(@PathVariable("project-id") Long projectId,
                                                               @PathVariable("task-id") Long taskId,
                                                               @PathVariable("comment-id") Long commentId) {

        log.info("c.n.mooray.taskapi.controller.CommentRestController: Enter deleteComment");

        MoorayResult<Boolean> result = commentService.deleteComment(projectId, taskId, commentId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result);
    }

}
