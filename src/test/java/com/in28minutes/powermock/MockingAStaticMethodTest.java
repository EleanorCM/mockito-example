package com.in28minutes.powermock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingAStaticMethodTest {

    // To combine Mockito and Powermock you need a specific runner
    // Initialise the class containing the static method for mocking (prepare for test)
    // Mock that state

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @Test
    public void testRetrieveTodosRelatedToSpringMockUsingBDD() {
        //GIVEN - setup
        List<Integer> stats = Arrays.asList(1,2,3);
        when(dependency.retrieveAllStats()).thenReturn(stats);
            // We need to tell Powermock that we are going to mock
            // a static method in UtilityClass:
        PowerMockito.mockStatic(UtilityClass.class);
            // Then we can mock the method's behaviour like any other:
        when(UtilityClass.staticMethod(6)).thenReturn(150);
        //WHEN - the actual method call / behaviour being tested
        int result = systemUnderTest.methodCallingAStaticMethod();
        //THEN - assertions
        assertEquals(150, result);
            // With Powermockito, verifying looks different:
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);

    }
}
