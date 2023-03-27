package com.miapp.todoapp.service;

import com.miapp.todoapp.exceptions.ToDoExceptions;
import com.miapp.todoapp.mapper.TaskInDTOToTask;
import com.miapp.todoapp.persistence.entity.Task;
import com.miapp.todoapp.persistence.entity.TaskStatus;
import com.miapp.todoapp.persistence.repository.TaskRepository;
import com.miapp.todoapp.service.dto.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        // Se inicializa el repositorio y el mapper en el constructor de la clase
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask(TaskInDTO taskInDTO){
        // Se mapea la entrada TaskInDTO a una entidad Task utilizando el objeto mapper
        Task task = mapper.map(taskInDTO);
        // Se guarda la entidad Task mapeada en el repositorio y se devuelve la entidad Task creada
        return this.repository.save(task);
    }

    public List <Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask= this.repository.findById(id);
        if (optionalTask.isEmpty()){
            // Si la tarea no existe, se lanza una excepción ToDoExceptions con un mensaje y código de estado HTTP.
            throw new ToDoExceptions("task not found", HttpStatus.NOT_FOUND);

        }
        this.repository.markTaskAsFinished(id);
    }

    @Transactional
    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        // Si la tarea no existe, se lanza una excepción ToDoExceptions con un mensaje y código de estado HTTP.
        if (optionalTask.isEmpty()) {
            throw new ToDoExceptions("task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);// Se elimina la tarea.
    }
}
