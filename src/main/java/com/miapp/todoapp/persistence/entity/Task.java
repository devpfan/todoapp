package com.miapp.todoapp.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "task") // Nombre de la tabla en la base de datos
public class Task {

    @Id // Indica que este campo es la clave primaria de la tabla
    @GeneratedValue(strategy = GenerationType.AUTO) // Genera automáticamente el valor de la clave primaria
    private long id;
    private String title; // Título de la tarea
    private String description; // Descripción de la tarea
    private LocalDateTime createDate; // Fecha y hora de creación de la tarea
    private LocalDateTime eta; // Fecha y hora estimada de finalización de la tarea
    private boolean finished; // Indica si la tarea ha sido finalizada
    private TaskStatus taskStatus; // Estado actual de la tarea
    public Task() {
    }
}
