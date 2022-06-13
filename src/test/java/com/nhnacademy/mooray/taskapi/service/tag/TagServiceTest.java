package com.nhnacademy.mooray.taskapi.service.tag;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Tag;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @InjectMocks
    TagServiceImpl tagService;

    @Mock
    TagRepository tagRepository;

    @Mock
    ProjectRepository projectRepository;

    private Project project;

    @BeforeEach
    void setUp() {
        project = spy(Project.create(ProjectCreationRequest.sample()));
        BDDMockito.lenient()
                  .when(projectRepository.findById(anyLong()))
                  .thenReturn(Optional.of(project));

        BDDMockito.lenient()
                  .when(project.getProjectId())
                  .thenReturn(1L);
    }

    @DisplayName("성공적으로 태그 등록")
    @Test
    void testCreateTag() {
        TagCreationRequest tagRequest = spy(TagCreationRequest.sample());
        Tag tag = Tag.create(project, tagRequest);
        BDDMockito.given(tagRepository.save(any(Tag.class)))
                  .willReturn(tag);

        MoorayResult<Tag> result = tagService.createTag(1L, tagRequest);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 태그 목록 조회")
    @Test
    void testRetrieveTags() {
        TagCreationRequest tagRequest1 = spy(TagCreationRequest.sample());
        Tag tag1 = Tag.create(project, tagRequest1);
        BDDMockito.lenient()
                  .when(tagRepository.save(any(Tag.class)))
                  .thenReturn(tag1);

        TagCreationRequest tagRequest2 = spy(TagCreationRequest.sample());
        Tag tag2 = Tag.create(project, tagRequest2);
        BDDMockito.lenient()
                  .when(tagRepository.save(any(Tag.class)))
                  .thenReturn(tag2);

        BDDMockito.lenient()
                  .when(tagRepository.findAll())
                  .thenReturn(List.of(tag1, tag2));

        MoorayResult<List<Tag>> result = tagService.retrieveTags(1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", List.of(tag1, tag2));
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 태그 목록 중 특정 태그 1개 조회")
    @Test
    void testRetrieveTag() {
        TagCreationRequest tagRequest = spy(TagCreationRequest.sample());
        Tag tag = Tag.create(project, tagRequest);

        BDDMockito.lenient()
                  .when(tagRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(tag));
        BDDMockito.lenient()
                  .when(tagRepository.save(any(Tag.class)))
                  .thenReturn(tag);

        MoorayResult<Tag> result = tagService.retrieveTag(1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", tag);
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 태그 목록 중 특정 태그 1개 수정")
    @Test
    void testUpdateTag() {
        TagCreationRequest creationRequest = spy(TagCreationRequest.sample());
        Tag originalTag = Tag.create(project, creationRequest);
        BDDMockito.lenient()
                  .when(tagRepository.save(any(Tag.class)))
                  .thenReturn(originalTag);

        BDDMockito.lenient()
                  .when(tagRepository.findById(1L))
                  .thenReturn(Optional.of(originalTag));

        TagUpdateRequest updateRequest = spy(TagUpdateRequest.sample());
        Tag updatedTag = Tag.create(originalTag, updateRequest);

        BDDMockito.lenient()
                  .when(tagRepository.findById(1L))
                  .thenReturn(Optional.of(updatedTag));

        // when
        MoorayResult<Tag> result = tagService.updateTag(1L, 1L, updateRequest);

        assertThat(result).isNotNull();
    }

    @DisplayName("특정 프로젝트에 생성되어 있는 태그 목록 중 특정 태그 1개 삭제")
    @Test
    void testDeleteTag() {
        TagCreationRequest tagRequest = spy(TagCreationRequest.sample());
        Tag tag = Tag.create(project, tagRequest);

        BDDMockito.lenient()
                  .when(tagRepository.findById(1L))
                  .thenReturn(Optional.ofNullable(tag));
        BDDMockito.lenient()
                  .when(tagRepository.save(any(Tag.class)))
                  .thenReturn(tag);

        MoorayResult<Boolean> result = tagService.deleteTag(1L, 1L);

        assertThat(result).isNotNull();
        assertThat(result.getPayload()).containsEntry("data", true);
    }

}
