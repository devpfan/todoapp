package com.miapp.todoapp.mapper;

import com.miapp.todoapp.persistence.entity.Task;
import com.miapp.todoapp.persistence.entity.TaskStatus;
import com.miapp.todoapp.service.dto.TaskInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TaskInDTOToTask implements IMapper<TaskInDTO, Task>{
    @Override
    public Task map(TaskInDTO in) {

        Task task = new Task();// Se crea una nueva instancia de la entidad Task

        // Se establece el título de la tarea en función del título de la entrada TaskInDTO
        task.setTitle(in.getTitle());

        // Se establece la descripción de la tarea en función de la descripción de la entrada TaskInDTO
        task.setDescription(in.getDescription());

        // Se establece el ETA (fecha de vencimiento estimada) de la tarea en función del ETA de la entrada TaskInDTO
        task.setEta(in.getEta());

        // Se establece la fecha de creación de la tarea en función de la fecha y hora actuales utilizando la clase LocalDateTime
        task.setCreateDate(LocalDateTime.now());

        // Se establece el estado de finalización de la tarea en false (no finalizada)
        task.setFinished(false);

        // Se establece el estado de la tarea en ON_TIME utilizando la enumeración TaskStatus
        task.setTaskStatus(TaskStatus.ON_TIME);

        // Se devuelve la instancia de la entidad Task
        return task;

    }
}
