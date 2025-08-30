package com.example.toDoListBackend.mappers;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.models.ToDoItem;

public class ToDoItemMapper {

    public static ToDoItemDTO toDto(ToDoItem toDoItem) {
        if (toDoItem == null) {
            return null;
        }
        return new ToDoItemDTO(
                toDoItem.getId(),
                toDoItem.getTaskTitle(),
                toDoItem.getCompleted()
        );
    }

    public static ToDoItem toEntity(ToDoItemDTO toDoItemDTO) {
        if (toDoItemDTO == null) {
            return null;
        }
        return new ToDoItem(
                toDoItemDTO.getId(),
                toDoItemDTO.getTaskTitle(),
                toDoItemDTO.getCompleted()
        );
    }
}
