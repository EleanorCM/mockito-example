package com.in28minutes.data.api;

import java.util.Arrays;
import java.util.List;

/***
 * A STUB is just a class which returns DUMMY DATA of some kind.
 * In this case we are creating a dummy implementation of a web service
 */

public class TodoServiceStub implements TodoService {
    @Override
    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Learn to dance", "Learn Spring", "Learn Spring MVC");
    }

    @Override
    public void deleteTodo(String todo) {

    }

    /***
     * One of the problems with stubbing is with DYNAMIC CONDITIONS.
     * If the web service should return different data according to
     * different conditions, the stub can become long and complex.
     *
     * Stubs are useful in SIMPLE SCENARIOS.
     * In more complex cases we use MOCKS.
     */
}
