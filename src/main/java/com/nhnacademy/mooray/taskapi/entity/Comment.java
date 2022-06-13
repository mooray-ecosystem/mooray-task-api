package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.dto.comment.CommentCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.comment.CommentUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

import static java.time.LocalDate.now;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Comments")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_no")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @Column(name = "project_no")
    private Long projectId;

    private String author;

    private String content;

    @Column(name = "created_at")
    private LocalDate createdDate;

    @Column(name = "updated_at")
    private LocalDate updatedDate;

    @Column(name = "deleted_at")
    private LocalDate deletedDate;

    public static Comment create(Long projectId, Task task, CommentCreationRequest commentRequest) {
        return Comment.builder()
                      .projectId(projectId)
                      .task(task)
                      .author(commentRequest.getAuthor())
                      .content(commentRequest.getContent())
                      .createdDate(now())
                      .updatedDate(now())
                      .build();
    }

    public static Comment create(Comment originalComment, CommentUpdateRequest taskRequest) {
        return Comment.builder()
                      .author(Optional.ofNullable(taskRequest.getAuthor()).orElse(originalComment.author))
                      .content(Optional.ofNullable(taskRequest.getContent()).orElse(originalComment.content))
                      .updatedDate(now())
                      .build();
    }

}
