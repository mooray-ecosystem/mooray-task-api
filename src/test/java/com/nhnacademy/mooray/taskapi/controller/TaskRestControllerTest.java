package com.nhnacademy.mooray.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.project.ProjectService;
import com.nhnacademy.mooray.taskapi.service.task.TaskService;
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

@WebMvcTest(controllers = TaskRestController.class)
class TaskRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    TaskService taskService;

    @MockBean
    ProjectService projectService;

    @DisplayName("프로젝트 특정 멤버가 업무를 정상적으로 등록합니다.")
    @Test
    void testCreateTask() throws Exception {

        // spy(new MoorayResult<>(true, null));

        // given
        // BDDMockito.given(projectService.createProject(any(ProjectCreationRequest.class)))
        //           .willReturn(mock(MoorayResult.class));
        // BDDMockito.given(taskService.createTask(anyLong(), any(TaskCreationRequest.class)))
        //           .willReturn(mock(MoorayResult.class));

        // boolean success1 = BDDMockito.doReturn(MoorayResult.class)
        //                              .when(projectService.createProject(any(ProjectCreationRequest.class)))
        //                              .isSuccess();
        // boolean success = BDDMockito.doReturn(MoorayResult.class)
        //                             .when(taskService.createTask(anyLong(), any(TaskCreationRequest.class)))
        //                             .isSuccess();

        // when
        String requestBody = objectMapper.writeValueAsString(TaskCreationRequest.sample());

        this.mockMvc.perform(post("/projects/{id}/tasks", 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(APPLICATION_JSON));
        // .andExpect(jsonPath("$.isSuccess", equalTo(true)));
    }

    @DisplayName("프로젝트 특정 멤버가 존재하는 업무를 정상적으로 조회합니다.")
    @Test
    void retrieveTasks() throws Exception {
        String requestBody = objectMapper.writeValueAsString(TaskCreationRequest.sample());

        this.mockMvc.perform(get("/projects/{project-id}/tasks", 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("프로젝트 특정 멤버가 존재하는 특정 업무 1건을 정상적으로 조회합니다.")
    @Test
    void retrieveTask() throws Exception {
        String requestBody = objectMapper.writeValueAsString(TaskCreationRequest.sample());

        this.mockMvc.perform(get("/projects/{project-id}/tasks/{task-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("프로젝트 특정 멤버가 존재하는 특정 업무 1건에 대해 정상적으로 수정합니다.")
    @Test
    void updateTask() throws Exception {
        String requestBody = objectMapper.writeValueAsString(TaskUpdateRequest.sample());

        this.mockMvc.perform(put("/projects/{project-id}/tasks/{task-id}", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));

    }

    @DisplayName("프로젝트 특정 멤버가 존재하는 특정 업무 1건에 대해 정상적으로 삭제합니다.")
    @Test
    void deleteTask() throws Exception {
        this.mockMvc.perform(delete("/projects/{project-id}/tasks/{task-id}", 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

}