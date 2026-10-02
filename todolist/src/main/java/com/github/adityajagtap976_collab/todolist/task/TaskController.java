package com.github.adityajagtap976_collab.todolist.task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

  private final List<Task> tasks = new ArrayList<>();
  private final AtomicLong idCounter = new AtomicLong(1);

  // GET /api/tasks - Retrieve all tasks
  @GetMapping
  public List<Task> getAllTasks() {
    return tasks;
  }

  // POST /api/tasks - Add a new task
  @PostMapping
  public ResponseEntity<Task> addTask(@RequestBody Task task) {
    task.setId(idCounter.getAndIncrement());
    tasks.add(task);
    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

  // DELETE /api/tasks/{id} - Delete a task by ID
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
    boolean removed = tasks.removeIf(task -> task.getId().equals(id));
    if (removed) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}