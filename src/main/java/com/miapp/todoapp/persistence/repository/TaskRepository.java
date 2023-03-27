package com.miapp.todoapp.persistence.repository;

import com.miapp.todoapp.persistence.entity.Task;
import com.miapp.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByTaskStatus(TaskStatus status); //obtener la tarea por status

    @Modifying
    @Query(value ="UPDATE TASK SET FINISHED = true WHERE ID=:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id")Long id);
}

/*
* extends JpaRepository<Task, Long>: La interfaz TaskRepository extiende la interfaz JpaRepository
*  y especifica la entidad JPA (Task) y el tipo de la clave primaria (Long) para la que se definen
*  los métodos CRUD.

*public interface TaskRepository: Se define una interfaz pública llamada TaskRepository.
*import com.miapp.todoapp.persistence.entity.Task;: Se importa la entidad Task para poder ser
*  usada en la interfaz.
*
* */