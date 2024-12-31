package com.todoapp.todoApp.service.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.todoapp.todoApp.entity.Task;
import com.todoapp.todoApp.entity.User;
import com.todoapp.todoApp.exceptions.TaskNotFoundException;
import com.todoapp.todoApp.exceptions.UserNotFoundException;
import com.todoapp.todoApp.repository.TaskRepository;
import com.todoapp.todoApp.repository.UserRepository;
import com.todoapp.todoApp.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public List<Task> getAllTasksForUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        return taskRepository.findByUserId(userId);
    }

    @Override
    public Task createTask(Task task,Long userId){
        User user=userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        task.setUser(user);
        return taskRepository.save(task);     
    }

    @Override
    public Task updateTask(Task task) {
        Task existingTask=taskRepository.findById(task.getId()).orElseThrow(()->new TaskNotFoundException("Task with ID " + task.getId() + " not found"));

        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setCompleted(task.isCompleted());
            
        return taskRepository.save(existingTask) ;
        
    }

    @Override
    public void deleteTaskById(Long taskId) {
        Task existingTask=taskRepository.findById(taskId).orElseThrow(()->new TaskNotFoundException("Task with ID " + taskId + " not found"));

        taskRepository.deleteById(taskId);
    }

}
