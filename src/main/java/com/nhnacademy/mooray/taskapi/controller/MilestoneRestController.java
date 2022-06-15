package com.nhnacademy.mooray.taskapi.controller;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Milestone;
import com.nhnacademy.mooray.taskapi.service.milestone.MilestoneService;
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
public class MilestoneRestController {

    private final MilestoneService milestoneService;

    @PostMapping("/projects/{project-id}/milestones")
    public ResponseEntity<MoorayResult<Milestone>> createMilestone(@PathVariable("project-id") Long projectId,
                                                                   @Valid @RequestBody
                                                                   MilestoneCreationRequest milestoneRequest) {

        log.info("c.n.mooray.taskapi.controller.MilestoneRestController: Enter createMilestone(...)");

        MoorayResult<Milestone> result = milestoneService.createMilestone(projectId, milestoneRequest);

        return ResponseEntity.status(CREATED)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/milestones")
    public ResponseEntity<MoorayResult<List<Milestone>>> retrieveMilestones(@PathVariable("project-id")
                                                                            Long projectId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveMilestones()");

        MoorayResult<List<Milestone>> result = milestoneService.retrieveMilestones(projectId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @GetMapping("/projects/{project-id}/milestones/{milestone-id}")
    public ResponseEntity<MoorayResult<Milestone>> retrieveMilestone(@PathVariable("project-id") Long projectId,
                                                                     @PathVariable("milestone-id") Long milestoneId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter retrieveMilestone(...)");

        MoorayResult<Milestone> result = milestoneService.retrieveMilestone(projectId, milestoneId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @PutMapping("/projects/{project-id}/milestones/{milestone-id}")
    public ResponseEntity<MoorayResult<Milestone>> updateTaskMilestone(@PathVariable("project-id") Long projectId,
                                                                       @PathVariable("milestone-id") Long milestoneId,
                                                                       @Valid @RequestBody
                                                                       MilestoneUpdateRequest milestoneRequest) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter updateMilestone(...)");

        MoorayResult<Milestone> result = milestoneService.updateMilestone(projectId, milestoneId, milestoneRequest);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

    @DeleteMapping("/projects/{project-id}/milestones/{milestone-id}")
    public ResponseEntity<MoorayResult<Boolean>> deleteMilestone(@PathVariable("project-id") Long projectId,
                                                                 @PathVariable("milestone-id") Long milestoneId) {

        log.info("c.n.mooray.taskapi.controller.TaskRestController: Enter deleteTask(..)");

        MoorayResult<Boolean> result = milestoneService.deleteMilestone(projectId, milestoneId);

        return ResponseEntity.status(OK)
                             .contentType(APPLICATION_JSON)
                             .body(result);
    }

}
