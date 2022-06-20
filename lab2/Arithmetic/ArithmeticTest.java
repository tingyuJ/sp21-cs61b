package Arithmetic;

import static org.junit.Assert.*;
import org.junit.Test;

public class ArithmeticTest {

    /** Performs a few arbitrary tests to see if the product method is
     * correct */
    @Test
    public void testProduct() {
        /* assertEquals for comparison of ints takes two arguments:
        assertEquals(expected, actual).
        if it is false, then the assertion will be false,
        and this test will fail.
        */

        assertEquals(30, Arithmetic.product(5, 6));
        assertEquals(-30, Arithmetic.product(5, -6));
        assertEquals(0, Arithmetic.product(0, -6));
    }

    /** Performs a few arbitrary tests to see if the sum method is correct */
    @Test
    public void testSum() {

        assertEquals(11, Arithmetic.sum(5, 6));
        assertEquals(-1, Arithmetic.sum(5, -6));
        assertEquals(-6, Arithmetic.sum(0, -6));
        assertEquals(0, Arithmetic.sum(6, -6));
    }

    @Test
    public void testTwoArrays() {
        int[] n1 = new int[]{1,2,3};
        int[] n2 = new int[]{1,2,3};

        int[] n3 = new int[]{1,2,3,4,5};
        int[] n4 = new int[]{1,2,3,4,5};

        int[][] a1 = new int[10][20];
        a1[0][0] = 1;

        int[][] a2 = new int[10][20];
        a2[0][0] = 1;

        assertArrayEquals(a2, a1);
    }

    private class obj{
        private int id;
        private int num;
    }
//    @Test
//    public void testTwoObjects() {
//        obj o1 = new obj();
//        obj o2 = new obj();
//        o1.id = 1;
//        o1.num = 2;
//        o2.id = 1;
//        o2.num = 2;
//
//        assertEquals(o1, o2);
//        fail();
//    }
}
