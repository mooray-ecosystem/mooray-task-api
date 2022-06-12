package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody CommentCreationRequest commentRequest) {
        // FIXME: Refactor logging
        log.error("c.n.mooray.taskapi.controller.CommentRestController: Enter createComment");

        MoorayResult result = commentService.createComment(commentRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .headers(httpHeaders -> httpHeaders.setAccept(List.of(APPLICATION_JSON)))
                             .body(result.getPayload());
    }

}
