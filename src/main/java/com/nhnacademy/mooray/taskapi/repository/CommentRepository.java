package com.nhnacademy.mooray.taskapi.repository;

import com.nhnacademy.mooray.taskapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
