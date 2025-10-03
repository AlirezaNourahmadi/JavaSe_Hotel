package hotel.model.service;

import hotel.model.entity.Task;
import hotel.model.repository.TaskRepository;
import lombok.Getter;

import java.util.List;

public class TaskService implements Service<Task, Integer> {
    @Getter
    private static TaskService service = new TaskService();

    private TaskService() {}

    @Override
    public void save(Task task) throws Exception {
        try (TaskRepository taskRepository = new TaskRepository()) {
            taskRepository.save(task);
        }
    }

    @Override
    public void edit(Task task) throws Exception {
        try (TaskRepository taskRepository = new TaskRepository()) {
            taskRepository.edit(task);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (TaskRepository taskRepository = new TaskRepository()) {
            taskRepository.delete(id);
        }
    }

    @Override
    public List<Task> findAll() throws Exception {
        try (TaskRepository taskRepository = new TaskRepository()) {
            return taskRepository.findAll();
        }
    }

    @Override
    public Task findById(Integer id) throws Exception {
        try (TaskRepository taskRepository = new TaskRepository()) {
            return taskRepository.findById(id);
        }
    }
}