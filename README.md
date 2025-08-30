# Angular To-Do List App with Spring Boot Backend
This is a **full-stack To-Do List application** with an **Angular frontend** and **Spring Boot backend**. It allows users to:

- Add new tasks
- View all tasks
- Update task titles
- Delete tasks (with confirmation)
- Mark tasks as complete (with visual indication)
- Search tasks by title

All tasks are persisted in a **database** through the backend services.

---

## Project Structure

### Frontend (Angular)
- **AppComponent**: Root component that initializes the app.  
- **MainComponent**: Handles form input and passes data to the list.  
- **TodoListComponent**: Displays the list of to-do items and listens for updates.  
- **TodoItemComponent**: Renders each task, handles completion toggle, deletion, and update.  
- **LoaderComponent**: Displays a loading indicator while fetching or updating tasks.  
- **TodoService**: Central service for task state management (add, update, delete, search, toggle) that communicates with the backend via **Observables**.  
- **models.ts**: Defines all data types and interfaces used in the frontend, including:
  - `TodoItem`: Represents a task with `id`, `taskTitle`, and `completed` status.
  - `TodoActions`: Interface for passing actions like delete, toggle, and update.
  - `CreateTodoItemDTO`: Structure for creating a new task.  

### Backend (Spring Boot)
- **ToDoListController**: Handles API endpoints for CRUD operations.  
- **ToDoItemService**: Contains business logic for tasks.  
- **ToDoItemRepository**: Interfaces with the database.  
- **ToDoItemMapper**: Maps between entities and DTOs.  
- **ToDoItemDTO** & **ToDoItem** models: Define the data structures.  
- **Exception Handling**: Provides proper responses for errors like “task not found” or invalid input.  
- MVC structure ensures clean separation of concerns.  

---

## Features

- **Reactive Forms**: Input validation in Angular.  
- **Standalone Components**: Clean and modular component setup.  
- **Dynamic Rendering**: Uses Angular’s modern `@if` and `@for` syntax.  
- **Conditional Styling**: Completed tasks appear differently (e.g., strikethrough).  
- **Search Support**: Filter tasks by title.  
- **Database Persistence**: All tasks are stored in a backend database.  
- **Update Task**: Users can update task titles via the frontend.  
- **Loader Component**: Shows a loading indicator while tasks are being fetched or updated from the backend.  
- **Exception Handling**: Proper error responses for backend issues like “task not found” or invalid input.  

---

## How to Run 
### Backend 
1. Navigate to the backend folder:
   ```bash
   cd backend
  
2. Run the Spring Boot app:
   ```bash
   ./mvnw spring-boot:run
  Backend will run on http://localhost:8080/ 
  
  ### Frontend 
1. Navigate to the frontend folder:
   ```bash
    cd frontend
   
2. Install dependencies:
   ```bash
   npm install
3. Run the Angular app:
   ```bash
   ng serve
Open your browser at http://localhost:4200/
