package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import com.in28minutes.data.api.TodoServiceStub;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TodoBusinessImplStubTest {

    private TodoService todoServiceStub = new TodoServiceStub();
    private TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);

    @Test
    public void testRetrieveTodosRelatedToSpringSize() {
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Eleanor");
        assertEquals(2, filteredTodos.size());

    }

    @Test
    public void testRetrieveTodosRelatedToSpringContains() {
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Eleanor");
        List<String> exactMatches = Arrays.asList("Learn Spring", "Learn Spring MVC");
        assertTrue(filteredTodos.containsAll(exactMatches));

    }
}