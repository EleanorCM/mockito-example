package com.in28minutes.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/***
 * When we mock, we do not make an instance of the actual class - only
 * a dummy which returns defined values. With a SPY, we can make an
 * instance of the actual class but OVERWRITE specific functionalities.
 */
public class SpyTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

//    @Spy
//    List arrayListSpy = Mockito.spy(new ArrayList());

    /***
     * Spies watch on as the real behaviour takes place, unless
     * they are called upon to change the behaviour.
     */
    @Test
    public void test() {
        List arrayListSpy = spy(ArrayList.class);
        assertEquals(0, arrayListSpy.size());
        // This would not work with a mock, which lacks the class' functionality:
        arrayListSpy.add("Dummy");
        assertEquals(1, arrayListSpy.size());
        arrayListSpy.remove("Dummy");
        assertEquals(0, arrayListSpy.size());
        // From the next line on, .size() will always be 5:
        when(arrayListSpy.size()).thenReturn(5);
        assertEquals(5, arrayListSpy.size());
    }

    /***
     * Note that well-built real-world projects don't have any reason
     * to use a spy, so you should AVOID THEM!
     */
}
