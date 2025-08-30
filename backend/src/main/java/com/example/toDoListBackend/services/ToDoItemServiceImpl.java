package com.example.toDoListBackend.services;

import com.example.toDoListBackend.dtos.ToDoItemDTO;
import com.example.toDoListBackend.mappers.ToDoItemMapper;
import com.example.toDoListBackend.models.ToDoItem;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException; // Directly use this for entity-related errors
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ToDoItemServiceImpl implements ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;
    private final ToDoItemMapper toDoItemMapper;

    public ToDoItemServiceImpl(ToDoItemRepository toDoItemRepository, ToDoItemMapper toDoItemMapper) {
        this.toDoItemRepository = toDoItemRepository;
        this.toDoItemMapper = toDoItemMapper;
    }

    @Override
    public List<ToDoItemDTO> getAllTodoItems() {
        log.info("Fetching all todo items");
        List<ToDoItem> toDoItems = toDoItemRepository.findAll(Sort.by(Sort.Order.asc("id")));
        return toDoItems.stream()
                .map(ToDoItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDoItemDTO> saveTodoItem(ToDoItemDTO toDoItemDTO) {
        log.info("Saving new todo item: {}", toDoItemDTO.getTaskTitle());
        ToDoItem toDoItem = ToDoItemMapper.toEntity(toDoItemDTO);
        toDoItemRepository.save(toDoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> deleteTodoItemById(Long id) {
        log.info("Deleting todo item with ID: {}", id);
        if (toDoItemRepository.existsById(id)) {
            toDoItemRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Todo item with ID " + id + " not found"); // Using EntityNotFoundException
        }
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> toggleCompletionStatusById(Long id) {
        log.info("Toggling completion status for todo item with ID: {}", id);
        ToDoItem todoItem = toDoItemRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("ToDoItem with id " + id + " not found.")); // Using EntityNotFoundException
        todoItem.setCompleted(!todoItem.getCompleted());
        toDoItemRepository.save(todoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> updateTodoItemTitle(ToDoItemDTO toDoItemDTO) {
        log.info("Updating title for todo item with ID: {}", toDoItemDTO.getId());
        ToDoItem toDoItem = toDoItemRepository.findById(toDoItemDTO.getId()).orElseThrow(() ->
                new EntityNotFoundException("ToDoItem with id " + toDoItemDTO.getId() + " not found.")); // Using EntityNotFoundException
        toDoItem.setTaskTitle(toDoItemDTO.getTaskTitle());
        toDoItemRepository.save(toDoItem);
        return getAllTodoItems();
    }

    @Override
    public List<ToDoItemDTO> getFilteredTodoItems(String toDoTitle) {
        log.info("Fetching todo items filtered by title: {}", toDoTitle);
        List<ToDoItem> toDoItems = toDoItemRepository.findByTaskTitleStartingWith(toDoTitle.trim().toLowerCase());
        return toDoItems.stream()
                .map(ToDoItemMapper::toDto)
                .collect(Collectors.toList());
    }
}
