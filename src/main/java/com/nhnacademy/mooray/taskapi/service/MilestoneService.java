package com.nhnacademy.mooray.taskapi.service;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Milestone;

import java.util.List;

public interface MilestoneService {

    MoorayResult<Milestone> createMilestone(Long projectId, MilestoneCreationRequest milestoneRequest);

    MoorayResult<List<Milestone>> retrieveMilestones(Long projectId);

    MoorayResult<Milestone> retrieveMilestone(Long projectId, Long milestoneId);

    MoorayResult<Milestone> updateMilestone(Long projectId, Long milestoneId, MilestoneUpdateRequest taskRequest);

    MoorayResult<Boolean> deleteMilestone(Long projectId, Long milestoneId);

}
