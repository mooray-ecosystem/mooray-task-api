package com.nhnacademy.mooray.taskapi.service.tag;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Tag;

import java.util.List;

public interface TagService {

    MoorayResult<Tag> createTag(Long projectId, TagCreationRequest tagRequest);

    MoorayResult<List<Tag>> retrieveTags(Long projectId);

    MoorayResult<Tag> retrieveTag(Long projectId, Long tagId);

    MoorayResult<Tag> updateTag(Long projectId, Long tagId, TagUpdateRequest tagRequest);

    MoorayResult<Boolean> deleteTag(Long projectId, Long tagId);

}
