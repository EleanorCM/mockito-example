package com.in28minutes.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.*;

/***
 * Mocking private methods is not really advisable.
 * It is useful in rare cases, such as when working with
 * a legacy system which can't just be refactored.
 */
@RunWith(PowerMockRunner.class)
public class InvokingPrivateMethodTest {

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testUsingPrivateMethod() throws Exception {
        //GIVEN
        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);
        //WHEN
            // we can't call a private method on systemUnderTest.
            // We INVOKE it using Whitebox:
            // ( First parameter: class containing private method;
            // Second parameter: name of private method as String)
        long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");
        //THEN
        assertEquals(6, result);
    }
}
