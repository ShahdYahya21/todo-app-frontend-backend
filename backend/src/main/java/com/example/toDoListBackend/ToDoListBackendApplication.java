package com.example.toDoListBackend;

import com.example.toDoListBackend.mappers.ToDoItemMapper;
import com.example.toDoListBackend.repositories.ToDoItemRepository;
import com.example.toDoListBackend.services.ToDoItemService;
import com.example.toDoListBackend.services.ToDoItemServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ToDoListBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToDoListBackendApplication.class, args);
    }

    @Bean
    public ToDoItemMapper toDoItemMapper() {
        return new ToDoItemMapper();
    }

    @Bean
    public ToDoItemService toDoItemService(ToDoItemRepository toDoItemRepository, ToDoItemMapper toDoItemMapper) {
        return new ToDoItemServiceImpl(toDoItemRepository, toDoItemMapper);
    }
}
