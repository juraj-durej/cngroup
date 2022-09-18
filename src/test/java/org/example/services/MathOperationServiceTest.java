package org.example.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MathOperationServiceTest {

    private MathOperationService mathOperationServiceUnderTest;

    @Before
    public void setUp() {
        mathOperationServiceUnderTest = new MathOperationService();
    }

    @Test
    public void testProcessInstructionSuccess() {

        Double base = 0.0;

        base = mathOperationServiceUnderTest.processInstruction(base,"add 15");
        base = mathOperationServiceUnderTest.processInstruction(base,"substract 5");
        base = mathOperationServiceUnderTest.processInstruction(base,"multiply 2");
        base = mathOperationServiceUnderTest.processInstruction(base,"divide 2");

        assertEquals(10.0, base, 0.001);
    }

    @Test
    public void testProcessInstructionWithNullBaseFailure() {

        Double result = mathOperationServiceUnderTest.processInstruction(null, "add 15");
        assertNull(result);
    }

    @Test
    public void testProcessInstructionInvalidOperationFailure() {

        Double result = mathOperationServiceUnderTest.processInstruction(0.0, "sqrt 2");
        assertNull(result);
    }

    @Test(expected = NumberFormatException.class)
    public void testProcessInstructionInvalidInputFailure() {
        mathOperationServiceUnderTest.processInstruction(0.0, "add a");
    }
}
