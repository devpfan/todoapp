package com.miapp.todoapp.controller;

import com.miapp.todoapp.persistence.entity.Task;
import com.miapp.todoapp.persistence.entity.TaskStatus;
import com.miapp.todoapp.service.TaskService;
import com.miapp.todoapp.service.dto.TaskInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Se indica que esta clase es un controlador REST y se establece la ruta base de la API
@RestController
@RequestMapping("/tasks")
public class TaskController {

    // Se declara una variable del servicio TaskService
    private final TaskService taskService;

    // Constructor que recibe una instancia del servicio TaskService
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Se define un endpoint POST que permite crear una nueva tarea
    @PostMapping
    public Task createTask(@RequestBody TaskInDTO taskInDTO){
        // Se utiliza el servicio TaskService para crear una nueva tarea a partir de un objeto TaskInDTO
        return this.taskService.createTask(taskInDTO);

    }

    @GetMapping
    public List<Task> findAll(){
        return this.taskService.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Task> findAllbyStatus(@PathVariable("status")TaskStatus status){
        return this.taskService.findAllByTaskStatus(status);
    }

    @PatchMapping("/mark_as_finished/{id}")
    public ResponseEntity<Void> markAsFinished(@PathVariable("id")Long id){
        this.taskService.updateTaskAsFinished(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
