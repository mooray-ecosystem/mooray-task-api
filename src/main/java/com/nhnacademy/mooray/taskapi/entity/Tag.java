package com.nhnacademy.mooray.taskapi.entity;

import com.nhnacademy.mooray.taskapi.dto.tag.TagCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.tag.TagUpdateRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Tags")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Tag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_no")
    private Project project;

    private String name;

    private Tag(Project project, String name) {
        this.project = project;
        this.name = name;
    }

    public static Tag create(Project project, TagCreationRequest tagRequest) {
        return new Tag(project, tagRequest.getName());
    }

    public static Tag create(Tag originalTag, TagUpdateRequest tagRequest) {
        return new Tag(originalTag.getProject(), Optional.ofNullable(tagRequest.getName()).orElse(originalTag.name));
    }

}
