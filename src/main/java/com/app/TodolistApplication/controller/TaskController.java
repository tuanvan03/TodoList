package com.app.TodolistApplication.controller;

import com.app.TodolistApplication.model.Task;
import com.app.TodolistApplication.service.TaskService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getAll(Model model) {
        List<Task> tasks = taskService.getTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping
    public String create(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{taskId}/delete")
    public String delete(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/{taskId}/toggle")
    public String toggle(@PathVariable Long taskId) {
        taskService.toggleTask(taskId);
        return "redirect:/";
    }
}
