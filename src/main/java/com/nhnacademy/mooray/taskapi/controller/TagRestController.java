package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Tag;
import com.nhnacademy.mooray.taskapi.service.tag.TagService;
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
public class TagRestController {

    private final TagService tagService;

    @PostMapping("/projects/{project-id}/tags")
    public ResponseEntity<MoorayResult<Tag>> createTag(@PathVariable("project-id") Long projectId,
                                                       @Valid @RequestBody
                                                       TagCreationRequest tagRequest) {

        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TagRestController: Enter createTag(...)");

        MoorayResult<Tag> result = tagService.createTag(projectId, tagRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tags")
    public ResponseEntity<MoorayResult<List<Tag>>> retrieveTags(@PathVariable("project-id")
                                                                Long projectId) {

        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTags()");

        MoorayResult<List<Tag>> result = tagService.retrieveTags(projectId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/tags/{tag-id}")
    public ResponseEntity<MoorayResult<Tag>> retrieveTag(@PathVariable("project-id") Long projectId,
                                                         @PathVariable("tag-id") Long tagId) {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveTag(...)");

        MoorayResult<Tag> result = tagService.retrieveTag(projectId, tagId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @PutMapping("/projects/{project-id}/tags/{tag-id}")
    public ResponseEntity<MoorayResult<Tag>> updateTaskTag(@PathVariable("project-id") Long projectId,
                                                           @PathVariable("tag-id") Long tagId,
                                                           @Valid @RequestBody
                                                           TagUpdateRequest tagRequest) {

        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter updateTag(..)");

        MoorayResult<Tag> result = tagService.updateTag(projectId, tagId, tagRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @DeleteMapping("/projects/{project-id}/tags/{tag-id}")
    public ResponseEntity<MoorayResult<Boolean>> deleteTag(@PathVariable("project-id") Long projectId,
                                                           @PathVariable("tag-id") Long tagId) {
        // FIXME: Remove logging
        log.error("c.n.mooray.taskapi.controller.TaskRestController: Enter deleteTask(..)");

        MoorayResult<Boolean> result = tagService.deleteTag(projectId, tagId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

}
