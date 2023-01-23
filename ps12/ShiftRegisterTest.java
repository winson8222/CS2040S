// Copy your ShiftRegisterTest.java code here
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * ShiftRegisterTest
 * @author dcsslg
 * Description: set of tests for a shift register implementation
 */
public class ShiftRegisterTest {
    /**
     * Returns a shift register to test.
     * @param size
     * @param tap
     * @return a new shift register
     */
    ILFShiftRegister getRegister(int size, int tap) {
        return new ShiftRegister(size, tap);
    }

    /**
     * Tests shift with simple example.
     */
    @Test
    public void testShift1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 1, 1, 0, 0, 0, 1, 1, 1, 1, 0 };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.shift());
        }
    }
    /**
     * Tests shift with when tap is the first element of the seed.
     */
    @Test
    public void testShiftedge2() {
        ILFShiftRegister r = getRegister(5, 0);
        int[] seed = { 1,1,0,0,1 };
        r.setSeed(seed);
        int[] expected = { 0,0,0,1,0 };
        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], r.shift());
        }
    }
    /**
     * Tests shift with when tap is the last element of the seed.
     */
    @Test
    public void testShiftedge() {
        ILFShiftRegister r = getRegister(5, 4);
        int[] seed = { 1,1,0,0,1 };
        r.setSeed(seed);
        int[] expected = { 0,0,0,0,0 };
        for (int i = 0; i < 5; i++) {
            assertEquals(expected[i], r.shift());
        }
    }

    /**
     * Tests generate with simple example.
     */
    @Test
    public void testGenerate1() {
        ILFShiftRegister r = getRegister(9, 7);
        int[] seed = { 0, 1, 0, 1, 1, 1, 1, 0, 1 };
        r.setSeed(seed);
        int[] expected = { 6, 1, 7, 2, 2, 1, 6, 6, 2, 3 };
        for (int i = 0; i < 10; i++) {
            assertEquals("GenerateTest1", expected[i], r.generate(3));
        }
    }
    /**
     * Tests generate with another simple example with different tap and length of seed.
     */
    @Test
    public void testGenerate2() {
        ILFShiftRegister r = getRegister(3, 1);
        int[] seed = { 0, 1, 0 };
        r.setSeed(seed);
        int[] expected = { 3,2,1,1 };
        for (int i = 0; i < 4; i++) {
            assertEquals("GenerateTest2", expected[i], r.generate(2));
        }
    }

    /**
     * Tests generate with another simple example with tap being the start of the array.
     */
    @Test
    public void testGenerateEdge() {
        ILFShiftRegister r = getRegister(3, 0);
        int[] seed = { 0, 1, 0 };
        r.setSeed(seed);
        int[] expected = { 1,3,1,0 };
        for (int i = 0; i < 4; i++) {
            assertEquals("GenerateTestEdge", expected[i], r.generate(2));
        }
    }
    /**
     * Tests generate when tap is the last element of the register
     */
    @Test
    public void testGenerateEdge2() {
        ILFShiftRegister r = getRegister(3, 2);
        int[] seed = { 0, 1, 0 };
        r.setSeed(seed);
        int[] expected = { 0,0,0,0 };
        for (int i = 0; i < 4; i++) {
            assertEquals("GenerateEdge2", expected[i], r.generate(2));
        }
    }
    /**
     * Tests register of length 1.
     */
    @Test
    public void testOneLength() {
        ILFShiftRegister r = getRegister(1, 0);
        int[] seed = { 1 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }
    /**
     * Tests register of when tap is the last element of the register.
     */
    @Test
    public void testedge() {
        ILFShiftRegister r = getRegister(2, 1);
        int[] seed = { 1, 0 };
        r.setSeed(seed);
        int[] expected = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], r.generate(3));
        }
    }

    /**
     * Tests with erroneous seed.
     * Comments: A proper response to this error is to throw an error in the setSeed function. The right way to
     * test this case is to check if the size of seed is equals to the length of seed. ALso test when seed includes digit other than 1 and 0,
     * also when the tap is greater than the length of the seed.
     */
    @Test
    public void testError() {
        ILFShiftRegister r = getRegister(4, 1);
        int[] seed = { 1, 0, 0, 0, 1, 1, 0 };
        r.setSeed(seed);
        r.shift();
        r.generate(4);
    }
}