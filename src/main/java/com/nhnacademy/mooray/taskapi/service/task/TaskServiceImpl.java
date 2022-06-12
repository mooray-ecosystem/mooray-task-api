package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Transactional
    @Override
    public MoorayResult createTask(TaskCreationRequest taskRequest) {
        // Mockito.when(projectService.createProject(any(ProjectCreationRequest.class)))
        //        .thenReturn(MoorayResult.success(anyString(), anyMap()));

        return null;
    }

    @Override
    public MoorayResult retrieveTasks() {
        List<Task> tasks = taskRepository.findAll();
        // TODO: payload
        return MoorayResult.success("", null);
    }

    @Override
    public MoorayResult retrieveTask(Long id) {
        // FIXME: Custom Exception
        Task task = taskRepository.findById(id)
                                  .orElseThrow(RuntimeException::new);

        return MoorayResult.success("", null);
    }

    @Transactional
    @Override
    public MoorayResult updateTask(Long id, TaskUpdateRequest taskRequest) {
        // 1. 수정할 놈을 아이디로 찾는다.
        Task foundTask = taskRepository.findById(id)
                                       .orElseThrow(RuntimeException::new);

        // 2. 불변 객체를 새로 만들 때 원본과 바꿀 요청 데이터를 같이 던져준다.
        Task task = Task.create(foundTask, taskRequest);

        // 3. 업데이트 친다.
        Task savedTask = taskRepository.save(task);

        return MoorayResult.success("", null);
    }

    @Transactional
    @Override
    public MoorayResult deleteTask(Long id) {
        // TODO: 삭제가 안되는 경우 로직 추가
        taskRepository.deleteById(id);

        return MoorayResult.success("", null);
    }

}
