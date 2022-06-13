package com.nhnacademy.mooray.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.project.ProjectService;
import com.nhnacademy.mooray.taskapi.service.tag.TagService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TagRestController.class)
class TagRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TagService tagService;

    @MockBean
    ProjectService projectService;

    @DisplayName("생성된 프로젝트에서 태그를 생성합니다.")
    @Test
    void testCreateTag() throws Exception {
        String requestBody = objectMapper.writeValueAsString(TagCreationRequest.sample());

        this.mockMvc.perform(post("/projects/{project-id}/tags", 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 생성된 모든 태그를 조회합니다.")
    @Test
    void testRetrieveTags() throws Exception {
        this.mockMvc.perform(get("/projects/{project-id}/tags", 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 태그 1건을 정상적으로 조회합니다.")
    @Test
    void testRetrieveTag() throws Exception {
        // FIXME: requestBody
        String requestBody = objectMapper.writeValueAsString(TagUpdateRequest.sample());

        this.mockMvc.perform(get("/projects/{project-id}/tags/{tag-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 태그 1건을 정상적으로 수정합니다.")
    @Test
    void testUpdateTaskTag() throws Exception {
        String requestBody = objectMapper.writeValueAsString(TagUpdateRequest.sample());

        this.mockMvc.perform(put("/projects/{project-id}/tags/{tag-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 태그 1건을 정상적으로 삭제합니다.")
    @Test
    void testDeleteTag() throws Exception {
        this.mockMvc.perform(delete("/projects/{project-id}/tags/{tag-id}", 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

}
