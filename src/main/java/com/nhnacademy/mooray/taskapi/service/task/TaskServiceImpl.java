package com.nhnacademy.mooray.taskapi.service.task;

import com.nhnacademy.mooray.taskapi.dto.MoorayResult;
import com.nhnacademy.mooray.taskapi.dto.task.TaskCreationRequest;
import com.nhnacademy.mooray.taskapi.dto.task.TaskUpdateRequest;
import com.nhnacademy.mooray.taskapi.entity.Project;
import com.nhnacademy.mooray.taskapi.entity.Task;
import com.nhnacademy.mooray.taskapi.repository.ProjectRepository;
import com.nhnacademy.mooray.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    @Override
    public MoorayResult<Task> createTask(Long projectId, TaskCreationRequest taskRequest) {
        // Mockito.when(projectService.createProject(any(ProjectCreationRequest.class)))
        //        .thenReturn(MoorayResult.success(anyString(), anyMap()));
        // if (!projectRepository.existsById(projectId)) {
        //     FIXME: Custom exc.
        // return MoorayResult.fail("프로젝트가 존재하지 않습니다.");
        // }

        Project foundProject = projectRepository.findById(projectId).get();
        Task task = Task.create(foundProject, taskRequest);

        Task savedTask = taskRepository.save(task);

        Map<String, Task> payload = new HashMap<>();
        payload.put("data", savedTask);

        return MoorayResult.success("업무를 성공적으로 생성했습니다.", payload);
    }

    @Override
    public MoorayResult<List<Task>> retrieveTasks(Long projectId) {
        List<Task> tasks = taskRepository.findAll();

        Map<String, List<Task>> payload = new HashMap<>();
        payload.put("data", tasks);

        return MoorayResult.success("업무 목록을 성공적으로 조회했습니다.", payload);
    }

    @Override
    public MoorayResult<Task> retrieveTask(Long projectId) {
        // FIXME: Custom Exception
        Task task = taskRepository.findById(projectId)
                                  .orElseThrow(RuntimeException::new);

        Map<String, Task> payload = new HashMap<>();
        payload.put("data", task);

        return MoorayResult.success("", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Task> updateTask(Long id, TaskUpdateRequest taskRequest) {
        // 1. 수정할 놈을 아이디로 찾는다.
        Task foundTask = taskRepository.findById(id)
                                       .orElseThrow(RuntimeException::new);

        // 2. 불변 객체를 새로 만들 때 원본과 바꿀 요청 데이터를 같이 던져준다.
        Task task = Task.create(foundTask, taskRequest);

        // 3. 업데이트 친다.
        Task savedTask = taskRepository.save(task);

        Map<String, Task> payload = new HashMap<>();
        payload.put("data", savedTask);

        return MoorayResult.success("", payload);
    }

    @Transactional
    @Override
    public MoorayResult<Boolean> deleteTask(Long id) {
        // TODO: 삭제가 안되는 경우 로직 추가
        taskRepository.deleteById(id);

        Map<String, Boolean> payload = new HashMap<>();
        payload.put("data", true);

        return MoorayResult.success("", payload);
    }

}
