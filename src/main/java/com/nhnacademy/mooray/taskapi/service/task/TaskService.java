package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;

public interface TaskService {

    MoorayResult createTask(Long projectId, TaskCreationRequest taskRequest);

    MoorayResult retrieveTasks();

    MoorayResult retrieveTask(Long id);

    MoorayResult updateTask(Long id, TaskUpdateRequest taskRequest);

    MoorayResult deleteTask(Long id);

}
