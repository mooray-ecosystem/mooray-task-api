package com.nhnacademy.mooray.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.project.ProjectCreationRequest;
import com.nhnacademy.mooray.taskapi.service.project.ProjectService;
import org.junit.jupiter.api.*;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProjectRestController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProjectService projectService;

    @Order(1)
    @DisplayName("성공적으로 프로젝트 생성")
    @Test
    void createProject() throws Exception {
        ProjectCreationRequest projectRequest = ProjectCreationRequest.sample();

        /**
         * EXCEPTION:
         *
         * com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
         * No serializer found for class com.nhnacademy.mooray.taskapi.dto.request.ProjectCreationRequest and no
         * properties discovered to create BeanSerializer (to avoid exception, disable
         * SerializationFeature.FAIL_ON_EMPTY_BEANS)
         *
         * SOLUTION:
         *
         * @AllArgsConstructor
         * @Builder
         * Add **@Getter**
         */
        String requestBody = objectMapper.writeValueAsString(projectRequest);

        BDDMockito.given(projectService.createProject(any(ProjectCreationRequest.class)))
                  .willReturn(mock(MoorayResult.class));

        this.mockMvc.perform(post("/projects")
                                     .contentType(APPLICATION_JSON)
                                     .content(requestBody))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(APPLICATION_JSON));
                    // .andExpect(jsonPath("$.isSuccess", equalTo(true)));
    }

    // @Order(2)
    // @DisplayName("성공적으로 프로젝트 정보 수정")
    // @Test
    // void updateProject() throws Exception {
    //     Mockito.when(projectService.updateProject(any(ProjectUpdateRequest.class)))
    //            .thenReturn(MoorayResult.success(anyString(), anyMap()));
    //
    //     String projectName = "2022-03-김해-웹서비스개발-1기";
    //     ProjectUpdateRequest projectRequest = ProjectUpdateRequest.builder()
    //                                                               .adminId(1L)
    //                                                               .name(projectName)
    //                                                               .description("프로젝트 설명글이 있습니다.")
    //                                                               .status(ProjectStatus.ARCHIVED.name())
    //                                                               .build();
    //
    //     String requestBody = objectMapper.writeValueAsString(projectRequest);
    //
    //     this.mockMvc.perform(put("/projects")
    //                                  .contentType(APPLICATION_JSON)
    //                                  .content(requestBody))
    //                 .andDo(print())
    //                 .andExpect(status().isOk())
    //                 .andExpect(content().contentType(APPLICATION_JSON))
    //                 .andExpect(jsonPath("$.isSuccess", equalTo(true)));
    // }

}
