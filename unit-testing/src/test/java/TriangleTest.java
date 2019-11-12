import static org.junit.Assert.*;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void sumGreaterThanThirdSide() {
        assertTrue(Triangle.isTriangleExists(5, 6, 9));
    }

    @Test
    public void sumLessThanThirdSide() {
        assertFalse(Triangle.isTriangleExists(2, 3, 6));
    }

    @Test
    public void sumIsEqualsToTheThirdSide() {
        assertFalse(Triangle.isTriangleExists(2, 3, 5));
    }

    @Test
    public void rightTriangle() {
        assertTrue(Triangle.isTriangleExists(5, 4, 3));
    }

    @Test
    public void allSidesAreZeros() {
        assertFalse(Triangle.isTriangleExists(0, 0, 0));
    }

    @Test
    public void negativeSides() {
        assertFalse(Triangle.isTriangleExists(-5, -6, -12));
    }

    @Test
    public void allSidesAreEqual() {
        assertTrue(Triangle.isTriangleExists(6.5, 6.5, 6.5));
    }

    @Test
    public void isoscelesTriangle() {
        assertTrue(Triangle.isTriangleExists(7, 7, 10));
    }

    @Test
    public void oneSideIsZero() {
        assertFalse(Triangle.isTriangleExists(0,4,6));
    }

    @Test
    public void oneSideIsNegative() {
        assertFalse(Triangle.isTriangleExists(-5, 6, 9.7));
    }


    @Test
    public void isTriangleExists() {
    }
}