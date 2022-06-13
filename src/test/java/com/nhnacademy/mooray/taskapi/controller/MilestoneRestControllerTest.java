package com.nhnacademy.mooray.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.milestone.MilestoneUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.milestone.MilestoneService;
import com.nhnacademy.mooray.taskapi.service.project.ProjectService;
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

@WebMvcTest(controllers = MilestoneRestController.class)
class MilestoneRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    MilestoneService milestoneService;

    @MockBean
    ProjectService projectService;

    @DisplayName("생성된 프로젝트에서 마일스톤을 생성합니다.")
    @Test
    void testCreateMilestone() throws Exception {
        String requestBody = objectMapper.writeValueAsString(MilestoneCreationRequest.sample());

        this.mockMvc.perform(post("/projects/{project-id}/milestones", 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 생성된 모든 마일스톤을 조회합니다.")
    @Test
    void testRetrieveMilestones() throws Exception {
        this.mockMvc.perform(get("/projects/{project-id}/milestones", 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 마일스톤 1건을 정상적으로 조회합니다.")
    @Test
    void testRetrieveMilestone() throws Exception {
        // FIXME: requestBody
        String requestBody = objectMapper.writeValueAsString(MilestoneUpdateRequest.sample());

        this.mockMvc.perform(get("/projects/{project-id}/milestones/{milestone-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 마일스톤 1건을 정상적으로 수정합니다.")
    @Test
    void testUpdateTaskMilestone() throws Exception {
        String requestBody = objectMapper.writeValueAsString(MilestoneUpdateRequest.sample());

        this.mockMvc.perform(put("/projects/{project-id}/milestones/{milestone-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("생성된 특정 프로젝트에서 존재하는 마일스톤 1건을 정상적으로 삭제합니다.")
    @Test
    void testDeleteMilestone() throws Exception {
        this.mockMvc.perform(delete("/projects/{project-id}/milestones/{milestone-id}", 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

}
