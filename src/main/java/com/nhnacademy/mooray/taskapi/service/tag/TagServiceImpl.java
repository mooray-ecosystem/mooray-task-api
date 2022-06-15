package com.nhnacademy.mooray.taskapi.service.tag;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Tag;
import com.nhnacademy.mooray.taskapi.exception.NotFoundTagException;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult<Tag> createTag(Long projectId, TagCreationRequest tagRequest) {
        Project foundProject = projectRepository.findById(projectId)
                                                .orElseThrow(NotFoundTagException::new);
        Tag tag = Tag.create(foundProject, tagRequest);

        Tag savedTag = tagRepository.save(tag);

        Map<String, Tag> payload = new HashMap<>();
        payload.put("data", savedTag);

        return MoorayResult.success("성공적으로 태그를 생성했습니다.", payload);
    }

    @Override
    public MoorayResult<List<Tag>> retrieveTags(Long projectId) {
        List<Tag> tags = tagRepository.findAll();

        Map<String, List<Tag>> payload = new HashMap<>();
        payload.put("data", tags);

        return MoorayResult.success("태그 목록을 성공적으로 조회했습니다.", payload);
    }

    @Override
    public MoorayResult<Tag> retrieveTag(Long projectId, Long tagId) {
        Tag foundTag = tagRepository.findById(tagId)
                                    .orElseThrow(NotFoundTagException::new);

        Map<String, Tag> payload = new HashMap<>();
        payload.put("data", foundTag);

        return MoorayResult.success("태그를 성공적으로 조회했습니다.", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Tag> updateTag(Long projectId, Long tagId, TagUpdateRequest tagRequest) {
        // 1. 수정할 놈을 아이디로 찾는다.
        Tag foundTag = tagRepository.findById(tagId)
                                    .orElseThrow(NotFoundTagException::new);

        // 2. 불변 객체를 새로 만들 때 원본과 바꿀 요청 데이터를 같이 던져준다.
        Tag tag = Tag.create(foundTag, tagRequest);

        // 3. 업데이트 친다.
        Tag savedTag = tagRepository.save(tag);

        Map<String, Tag> payload = new HashMap<>();
        payload.put("data", savedTag);

        return MoorayResult.success("태그를 성공적으로 수정했습니다.", payload);

    }

    @Transactional
    @Override
    public MoorayResult<Boolean> deleteTag(Long projectId, Long tagId) {
        tagRepository.deleteById(tagId);

        Map<String, Boolean> payload = new HashMap<>();
        payload.put("data", true);

        return MoorayResult.success("태그를 성공적으로 삭제했습니다.", payload);
    }

}
