package com.nhnacademy.mooray.taskapi.service.comment;

import com.nhnacademy.mooray.taskapi.repository.CommentRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentServiceImpl commentService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    TaskRepository taskRepository;

    @DisplayName("")
    @Test
    void testCreateComment() {
    }

    @DisplayName("")
    @Test
    void testRetrieveComments() {
    }

    @DisplayName("")
    @Test
    void testRetrieveComment() {
    }

    @DisplayName("")
    @Test
    void testUpdateComment() {
    }

    @DisplayName("")
    @Test
    void testDeleteComment() {
    }

}