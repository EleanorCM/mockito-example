package com.in28minutes.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ListTest {
    @Mock
    List<String> listMock;

    @Test
    public void test() {
        //given

        //when
        when(listMock.size()).thenReturn(2);
        //then
        assertEquals(2, listMock.size());
    }

    @Test
    public void testMultiple() {
        //given

        //when
        // You can CHAIN .thenReturn calls:
        when(listMock.size()).thenReturn(2).thenReturn(3);
        //then
        assertEquals(2, listMock.size());
        assertEquals(3, listMock.size());
    }

    @Test
    public void testMockGet() {
        //when
        // You can CHAIN .thenReturn calls:
        when(listMock.get(0)).thenReturn("in28minutes").thenReturn("Hi");
        //then
        assertEquals("in28minutes", listMock.get(0));
        assertEquals("Hi", listMock.get(0));
    }

    @Test
    public void testListGetWithMatchers() {
        //when
        // Argument Matcher:
        // will have same return value regardless of int argument
        when(listMock.get(anyInt())).thenReturn("in28minutes");
        //then
        assertEquals("in28minutes", listMock.get(0));
        assertEquals("in28minutes", listMock.get(125));
    }

    @Test
    public void testListGetWithMatchersUsingBDD() {
        //given
        when(listMock.get(anyInt())).thenReturn("in28minutes");
        //when
        String listMockGetCall1 = listMock.get(0);
        String listMockGetCall2 = listMock.get(125);
        //then
        assertEquals("in28minutes", listMockGetCall1);
        assertEquals("in28minutes", listMockGetCall2);
    }

    /***
     * This is how you throw an exception forcibly to see what happens after.
     * It is NOT THE SAME as asserting that a given input causes an exception to be thrown -
     * but you still have to use the expected annotation. The annotation confirms that the exception
     * was thrown.
     *
     * You can add asserts to text the exception attributes, eg the message.
     */
    @Test(expected = RuntimeException.class)
    public void testListThrowException() {
        //given - setup
        RuntimeException e = new RuntimeException("Something went wrong :)");
        when(listMock.get(anyInt())).thenThrow(e);
        //when - the actual method call
        listMock.get(0);
        //then - assertions
        assertEquals("Something went wrong :)", e.getMessage());
        }

}
