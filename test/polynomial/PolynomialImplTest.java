package polynomial;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is the class that Test PolynomialImpl.
 */
public class PolynomialImplTest {

    private Polynomial p1;
    private Polynomial p2;
    private Polynomial p3;
    private Polynomial hiddenX;
    private Polynomial hyper;
    private Polynomial hyperY;
    private Polynomial empty;
    private Polynomial classExample;
    private Polynomial wideSpace;

    /**
     * Create instances to test the implementation.
     * @throws Exception
     */
    @org.junit.Before
    public void setUp() throws Exception {

        this.p1 = new PolynomialImpl("4x^3 +3x^1 -5");
        this.p2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
        this.p3 = new PolynomialImpl("-3x -2x -5 + 11x^1");
        this.hiddenX = new PolynomialImpl("-3 -2 -5 + 11");
        this.hyper = new PolynomialImpl("2x^9 + 3x^8 + 5x^6 - 4x^7 - 6x^5 + 7x^4 - 8x^3 - 9x^2 + 2x + 9 + 1x^10");
        this.hyperY = new PolynomialImpl("2y^9 + 3y^8 + 5y^6 - 4y^7 - 6y^5 + 7y^4 - 8y^3 - 9y^2 + 2y + 9 + 1y^10");
        this.empty = new PolynomialImpl();
        this.classExample = new PolynomialImpl("3x^4-5x^3+2x-4");
        this.wideSpace = new PolynomialImpl("3 x ^ 4       -         5 x ^ 3 + 2 x     - 4");
    }

    /**
     * Check that no object can be created with negative values
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPolynomial () {

            Polynomial invalid = new PolynomialImpl("4x^-3 +3x^1 -5");
    }

    /**
     * Check the method AddTerm Method, only valid cases in this test.
     */
    @Test
    public void testAddTermPower () {
        this.p1.addTerm(4,3);
        assertEquals(8, this.p1.getCoefficient(3));
        this.p1.addTerm(4,4);
        assertEquals(4, this.p1.getCoefficient(4));

        this.p2.addTerm(10,4);
        assertEquals(7, this.p2.getCoefficient(4));
    }

    /**
     * Check the method AddTerm Method will not accept negative powers.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddTermPower () {
        this.p1.addTerm(4,-3);
    }

    /**
     * Check the highest power on this Polynomial.
     * If a term is added, and that power is greater, then that power should be the degree
     */
    @Test
    public void testGetDegree () {

        assertEquals(3, this.p1.getDegree());
        assertEquals(5, this.p2.getDegree());
        assertEquals(1, this.p3.getDegree());
        assertEquals(0, this.hiddenX.getDegree());

        this.hiddenX.addTerm(25,12);
        assertEquals(12, this.hiddenX.getDegree());

    }

    /**
     * Obtain the coefficient depending the power, if power is not found
     * return 0.
     */
    @Test
    public void testCoefficient () {

        assertEquals(1, this.hyper.getCoefficient(10));
        assertEquals(2, this.hyper.getCoefficient(9));
        assertEquals(3, this.hyper.getCoefficient(8));
        assertEquals(-4, this.hyper.getCoefficient(7));
        assertEquals(5, this.hyper.getCoefficient(6));
        assertEquals(-6, this.hyper.getCoefficient(5));
        assertEquals(7, this.hyper.getCoefficient(4));
        assertEquals(-8, this.hyper.getCoefficient(3));
        assertEquals(-9, this.hyper.getCoefficient(2));
        assertEquals(2, this.hyper.getCoefficient(1));
        assertEquals(9, this.hyper.getCoefficient(0));

        assertEquals(0, this.hyper.getCoefficient(20));
        assertEquals(0, this.hyper.getCoefficient(-1));
    }

    /**
     * Evaluate the Polynomial, it will return a double value
     */
    @Test
    public void testEvaluate(){

//        this.p1 = new PolynomialImpl("4x^3 +3x^1 -5");
        assertEquals( 510.00 ,this.p1.evaluate(5.00), 0.001);
//        this.p2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
        assertEquals(-95.00, this.p2.evaluate(2.00), 0.001);
//        this.p3 = new PolynomialImpl("-3x -2x -5 + 11x^1");
        assertEquals(55.00, this.p3.evaluate(10.00), 0.001);
//        this.hiddenX = new PolynomialImpl("-3 -2 -5 + 11");
        assertEquals(1.00, this.hiddenX.evaluate(10.25),0.001);
//        this.hyper = new PolynomialImpl("1x^10 + 2x^9 + 3x^8 - 4x^7 + 5x^6 - 6x^5 + 7x^4 - 8x^3 - 9x^2 + 2x + 9");
        assertEquals( 2.00 , this.hyper.evaluate(1), 0.001 );
        assertEquals( 2.00 , this.hyperY.evaluate(1), 0.001 );
        assertEquals(40.0625, this.classExample.evaluate((2.50)),0.001);
        assertEquals(40.0625, this.wideSpace.evaluate((2.50)),0.001);

    }

    /**
     * Check if two polynomials are the same.
     */
    @Test
    public void testIsSame() {
        assertFalse(this.p1.isSame(this.hiddenX));
        assertFalse(this.p2.isSame(this.hiddenX));
        assertFalse(this.p3.isSame(this.hiddenX));

        assertTrue(this.p1.isSame(this.p1));
//        ("4x^3 +3x^1 -5");
        this.empty.addTerm(-5,0);
        this.empty.addTerm(3,1);
        this.empty.addTerm(4,3);

        assertTrue(this.p1.isSame(this.empty));
    }

    /**
     * Add 2 Polynomials and verify with the toString method the
     * terms were correctly added
     */
    @Test
    public void testAdd() {

        int zeroP1 = this.p1.getCoefficient(0);
        int oneP1 = this.p1.getCoefficient(1);
        int threeP1 = this.p1.getCoefficient(1);

        Polynomial merge = this.p1.add(this.empty);
        assertTrue(this.p1.isSame(merge));
        //make sure is not mutated
        assertTrue(zeroP1 ==  this.p1.getCoefficient(0));
        assertTrue(oneP1 ==  this.p1.getCoefficient(1));
        assertTrue(threeP1 == this.p1.getCoefficient(1));


        //Test 2 Add three
        this.p1 = new PolynomialImpl("4x^3 +3x^1 -5");
        this.p2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
        this.p3 = new PolynomialImpl("-3x -2x -5 + 11x^1");

        Polynomial group = this.p1.add(this.p2).add(p3);
        // -2x^5 -3x^4 +4x^3 +20x^1 -15;
        assertEquals("-2x^5 -3x^4 +4x^3 +20x^1 -15", group.toString());
    }

    /**
     * Check the string is organized from greater to lower term, there is a space between terms
     */
    @Test
    public void testToString(){

        assertEquals("4x^3 +3x^1 -5",this.p1.toString());
        assertEquals("-2x^5 -3x^4 +11x^1 -5", this.p2.toString());
        assertEquals("6x^1 -5", this.p3.toString());
        assertEquals("1", this.hiddenX.toString());
        assertEquals("1x^10 +2x^9 +3x^8 -4x^7 +5x^6 -6x^5 +7x^4 -8x^3 -9x^2 +2x^1 +9", this.hyper.toString());
        assertEquals("1y^10 +2y^9 +3y^8 -4y^7 +5y^6 -6y^5 +7y^4 -8y^3 -9y^2 +2y^1 +9", this.hyperY.toString());
        assertEquals("0", this.empty.toString());
        assertEquals("3x^4 -5x^3 +2x^1 -4", this.classExample.toString());
        assertEquals("3x^4 -5x^3 +2x^1 -4", this.wideSpace.toString());
    }

    /**
     * AS the method isSame is using the equals method, we need to test the
     * hashCode is working as well, and returning the same hash for the same tooString.
     */
    @Test
    public void testHashCode() {

        Polynomial test = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1 + 10x");
        Polynomial test2 = new PolynomialImpl();

        test2.addTerm(11,1);
        test2.addTerm(10,1);
        test2.addTerm(-2,5);
        test2.addTerm(-3,4);
        test2.addTerm(-5,0);

        assertEquals(test.hashCode(),test2.hashCode());

    }

}