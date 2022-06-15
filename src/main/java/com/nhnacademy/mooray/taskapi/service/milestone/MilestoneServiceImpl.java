package com.nhnacademy.mooray.taskapi.service.milestone;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Milestone;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.exception.NotFoundMilestoneException;
import com.nhnacademy.mooray.taskapi.repository.MilestoneRepository;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MilestoneServiceImpl implements MilestoneService {

    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult<Milestone> createMilestone(Long projectId, MilestoneCreationRequest milestoneRequest) {
        Project foundProject = projectRepository.findById(projectId)
                                                .orElseThrow(NotFoundMilestoneException::new);
        Milestone milestone = Milestone.create(foundProject, milestoneRequest);

        // CF: mapstruct
        Milestone savedMilestone = milestoneRepository.save(milestone);

        Map<String, Milestone> payload = new HashMap<>();
        payload.put("data", savedMilestone);

        return MoorayResult.success("성공적으로 마일스톤을 생성했습니다.", payload);
    }

    @Override
    public MoorayResult<List<Milestone>> retrieveMilestones(Long projectId) {
        List<Milestone> milestones = milestoneRepository.findAll();

        Map<String, List<Milestone>> payload = new HashMap<>();
        payload.put("data", milestones);

        return MoorayResult.success("마일스톤 목록을 성공적으로 조회했습니다.", payload);
    }

    @Override
    public MoorayResult<Milestone> retrieveMilestone(Long projectId, Long milestoneId) {
        Milestone foundMilestone = milestoneRepository.findById(milestoneId)
                                                      .orElseThrow(NotFoundMilestoneException::new);

        Map<String, Milestone> payload = new HashMap<>();
        payload.put("data", foundMilestone);

        return MoorayResult.success("마일스톤을 성공적으로 조회했습니다.", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Milestone> updateMilestone(Long projectId, Long milestoneId,
                                                   MilestoneUpdateRequest milestoneRequest) {
        // 1. 수정할 놈을 아이디로 찾는다.
        Milestone foundMilestone = milestoneRepository.findById(milestoneId)
                                                      .orElseThrow(NotFoundMilestoneException::new);

        // 2. 불변 객체를 새로 만들 때 원본과 바꿀 요청 데이터를 같이 던져준다.
        Milestone milestone = Milestone.create(foundMilestone, milestoneRequest);

        // 3. 업데이트 친다.
        Milestone savedMilestone = milestoneRepository.save(milestone);

        Map<String, Milestone> payload = new HashMap<>();
        payload.put("data", savedMilestone);

        return MoorayResult.success("마일스톤을 성공적으로 수정했습니다.", payload);

    }

    @Transactional
    @Override
    public MoorayResult<Boolean> deleteMilestone(Long projectId, Long milestoneId) {
        milestoneRepository.deleteById(milestoneId);

        Map<String, Boolean> payload = new HashMap<>();
        payload.put("data", true);

        return MoorayResult.success("마일스톤을 성공적으로 삭제했습니다.", payload);
    }

}
