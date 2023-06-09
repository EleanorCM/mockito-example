package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;

import java.util.ArrayList;
import java.util.List;

public class TodoBusinessImpl {
    private TodoService todoService;

    public List<String> getTodoList() {
        return todoList;
    }

    private List<String> todoList;

    // This is the SUT - System Under Test
    // TodoService is a DEPENDENCY
    public TodoBusinessImpl(TodoService todoService) {
        this.todoService = todoService;
    }

    public List<String> retrieveTodosRelatedToSpring(String user) {
        List<String> filteredTodos = new ArrayList<String>();
        List<String> todoList = todoService.retrieveTodos(user);
        for (String todo : todoList) {
            if (todo.toLowerCase().contains("spring")) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        todoList = todoService.retrieveTodos(user);
        for (String todo : todoList) {
            if (!todo.toLowerCase().contains("spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }
}
