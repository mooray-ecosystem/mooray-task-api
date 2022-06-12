package com.nhnacademy.mooray.taskapi.repository.task;

import com.nhnacademy.mooray.taskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
