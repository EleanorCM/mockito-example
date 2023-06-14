package com.in28minutes.business;

import com.in28minutes.data.api.TodoService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

/***
 * MOCKING creates objects to simulate the behaviour of real objects.
 * Unlike stubs, mocks are created at RUNTIME (DYNAMICALLY).
 * They offer greater functionality than stubs and are easier
 * to maintain.
 */

public class TodoBusinessImplMockitoInjectMocksTest {

    /***
     * RULES are an alternative to using a RUNNER (@RunWith(MockitoJUnitRunner.class))
     * above the class declaration. The advantage is that while there can only
     * be one runner, you can have MULTIPLE rules. These run before and after
     * the test class. Rules must be PUBLIC.
     */

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TodoService todoServiceMock;

    /***
     * TodoBusinessImpl REQUIRES a TodoService instance to be passed in.
     * In other words, we need to INJECT OUR MOCK (todoServiceMock) INTO
     * a TodoBusinessImpl object. @InjectMocks CREATES AN INSTANCE of
     * TodoBusinessImpl class and automatically injects our todoService
     * mock into it. This is an alternative to:
     * TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
     */

    @InjectMocks
    TodoBusinessImpl todoBusinessImpl;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpringMockUsingBDD() {
        //GIVEN - setup
        List<String> allTodos = Arrays.asList("Learn to dance", "Learn Spring", "Learn Spring MVC");
        when(todoServiceMock.retrieveTodos("Eleanor")).thenReturn(allTodos);
            // an alternative syntax for when().thenReturn() is given().willReturn()!
        //WHEN - the actual method call / behaviour being tested
        List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Eleanor");
        //THEN - assertions
        assertEquals(2, filteredTodos.size());
            // another way to assert this equivalence:
        assertThat(filteredTodos.size(), is(2));
    }

    @Test
    public void testDeleteTodosNotRelatedToSpringMockUsingBDD() {
        //GIVEN - setup
        List<String> allTodos = Arrays.asList("Learn to dance", "Learn Spring", "Learn Spring MVC");
        when(todoServiceMock.retrieveTodos("Eleanor")).thenReturn(allTodos);
        todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //WHEN - the actual method call / behaviour being tested
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Eleanor");
        //THEN - assertions
            // Instead of an assert, this verifies that .deleteTodo was called on
            // todoServiceMock with the only list element that is not related to Spring.
            // An assert is not that helpful here, because there is no functionality
            // in the .deleteTodo() implementation.
        verify(todoServiceMock, times(1)).deleteTodo("Learn to dance");
    }

    @Test
    public void testDeleteTodosNotRelatedToSpringMockUsingBDDArgumentCaptor() {
        //GIVEN - setup
        List<String> allTodos = Arrays.asList("Learn to dance", "Learn Spring", "Learn Spring MVC");
        when(todoServiceMock.retrieveTodos("Eleanor")).thenReturn(allTodos);
        todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
        //WHEN - the actual method call / behaviour being tested
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Eleanor");
        //THEN - assertions
            // This verifies at runtime which value was deleted:
        verify(todoServiceMock, times(1)).deleteTodo(stringArgumentCaptor.capture());
            // The same thing using BDD syntax:
        then(todoServiceMock).should(times(1)).deleteTodo(stringArgumentCaptor.capture());
            // And asserting that the value is what we expect:
        assertThat(stringArgumentCaptor.getValue(), is("Learn to dance"));
    }
}
