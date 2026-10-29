
package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.model.Task;
import com.taskflow.taskflow.service.TaskNotFoundException;
import com.taskflow.taskflow.service.TaskService;
import org.springframework.web.bind.annotation.*;
import com.taskflow.taskflow.service.TaskNotFoundException;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseEntity<Task> createTask(
            @Valid @RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
