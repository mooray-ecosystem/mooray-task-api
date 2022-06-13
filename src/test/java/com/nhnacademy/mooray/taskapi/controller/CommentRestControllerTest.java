package com.nhnacademy.mooray.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import com.nhnacademy.mooray.taskapi.service.comment.CommentService;
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

@WebMvcTest(controllers = CommentRestController.class)
class CommentRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CommentService commentService;

    @MockBean
    TaskService taskService;

    @DisplayName("특정 프로젝트에 속한 특정 업무에 댓글을 정상적으로 생성합니다.")
    @Test
    void testCreateComment() throws Exception {
        String requestBody = objectMapper.writeValueAsString(CommentCreationRequest.sample());

        this.mockMvc.perform(post("/projects/{project-id}/tasks/{task-id}/comments", 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("특정 프로젝트에 속한 특정 업무에 댓글 목록을 정상적으로 조회합니다.")
    @Test
    void testRetrieveComments() throws Exception {
        this.mockMvc.perform(get("/projects/{project-id}/tasks/{task-id}/comments", 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("특정 프로젝트에 속한 특정 업무에 속한 한 댓글을 정상적으로 조회합니다.")
    @Test
    void testRetrieveComment() throws Exception {
        this.mockMvc.perform(get("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}", 1L, 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("특정 프로젝트에 속한 특정 업무에 속한 한 댓글을 정상적으로 수정합니다.")
    @Test
    void testUpdateComment() throws Exception {
        String requestBody = objectMapper.writeValueAsString(CommentUpdateRequest.sample());

        this.mockMvc.perform(put("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}", 1L, 1L, 1L)
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

    @DisplayName("특정 프로젝트에 속한 특정 업무에 속한 한 댓글을 정상적으로 삭제합니다.")
    @Test
    void testDeleteComment() throws Exception {
        this.mockMvc.perform(delete("/projects/{project-id}/tasks/{task-id}/comments/{comment-id}", 1L, 1L, 1L))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(APPLICATION_JSON));
    }

}