package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static java.time.LocalDate.now;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Comments")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    private String author;

    private String content;

    @Column(name = "created_at")
    private LocalDate createdDate;

    @Column(name = "updated_at")
    private LocalDate updatedDate;

    @Column(name = "deleted_at")
    private LocalDate deletedDate;

    public static Comment create(Task task, CommentCreationRequest commentRequest) {
        return Comment.builder()
                      .task(task)
                      .author(commentRequest.getAuthor())
                      .content(commentRequest.getContent())
                      .createdDate(now())
                      .updatedDate(now())
                      .build();
    }

}
