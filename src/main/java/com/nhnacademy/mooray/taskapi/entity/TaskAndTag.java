package com.nhnacademy.mooray.taskapi.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "Tasks_Tags")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class TaskAndTag {

    @EmbeddedId
    private Pk pk;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_no")
    private Tag tag;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_no")
    private Task task;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    static class Pk implements Serializable {
        private Long tagId;
        private Long taskId;
    }

}
