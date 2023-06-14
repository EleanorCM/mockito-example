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
import static org.mockito.BDDMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingConstructorTest {

    // To combine Mockito and Powermock you need a specific runner

    @InjectMocks
    SystemUnderTest systemUnderTest;

    @Test
    public void testUsingMockedConstructor() {
        //GIVEN - setup
        List<Integer> stats = Arrays.asList(1,2,3);

        PowerMockito.mockStatic(UtilityClass.class);
            // Then we can mock the method's behaviour like any other:
        when(UtilityClass.staticMethod(6)).thenReturn(150);
        //WHEN
        int result = systemUnderTest.methodCallingAStaticMethod();
        //THEN
        assertEquals(150, result);
            // With Powermockito, verifying looks different:
        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(6);

    }
}
