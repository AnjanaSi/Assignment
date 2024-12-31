package com.todoapp.todoApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.todoapp.todoApp.entity.Task;

@Service
public interface TaskService {
    List<Task> getAllTasksForUser(Long userId);
    Task createTask(Task task,Long userId);
    Task updateTask(Task task);
    void deleteTaskById(Long taskId);
}
