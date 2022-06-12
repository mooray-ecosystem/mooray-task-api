package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Task;

import java.util.List;

public interface TaskService {

    MoorayResult<Task> createTask(Long projectId, TaskCreationRequest taskRequest);

    MoorayResult<List<Task>> retrieveTasks(Long projectId);

    MoorayResult<Task> retrieveTask(Long id);

    MoorayResult<Task> updateTask(Long id, TaskUpdateRequest taskRequest);

    MoorayResult<Boolean> deleteTask(Long id);

}
