package com.app.TodolistApplication.service;

import com.app.TodolistApplication.model.Task;
import com.app.TodolistApplication.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title) {
        // create a task
        Task task = new Task();
        task.setCompleted(false);
        task.setTitle(title);

        // save task
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    public void toggleTask(Long taskId) {
        // take task by id
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Invalid ID"));

        // reverse task's status
        task.setCompleted(!task.isCompleted());

        // save task
        taskRepository.save(task);
    }
}
