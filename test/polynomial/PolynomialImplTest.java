package polynomial;

import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomialImplTest {

    private Polynomial p1;
    private Polynomial p2;
    private Polynomial p3;
    private Polynomial hiddenX;
    private Polynomial hyper;
    private Polynomial empty;

    @org.junit.Before
    public void setUp() throws Exception {

        this.p1 = new PolynomialImpl("4x^3 +3x^1 -5");
        this.p2 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
        this.p3 = new PolynomialImpl("-3x -2x -5 + 11x^1");
        this.hiddenX = new PolynomialImpl("-3 -2 -5 + 11");
        this.hyper = new PolynomialImpl("2x^9 + 3x^8 + 5x^6 - 4x^7 - 6x^5 + 7x^4 - 8x^3 - 9x^2 + 2x + 9 + 1x^10");
        this.empty = new PolynomialImpl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPolynomial () {

            Polynomial invalid = new PolynomialImpl("4x^-3 +3x^1 -5");
    }

    @Test
    public void testAddTermPower () {
        this.p1.addTerm(4,3);
        assertEquals(8, this.p1.getCoefficient(3));
        this.p1.addTerm(4,4);
        assertEquals(4, this.p1.getCoefficient(4));

        this.p2.addTerm(10,4);
        assertEquals(7, this.p2.getCoefficient(4));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAddTermPower () {
        this.p1.addTerm(4,-3);
    }

    @Test
    public void testGetDegree () {

        assertEquals(3, this.p1.getDegree());
        assertEquals(5, this.p2.getDegree());
        assertEquals(1, this.p3.getDegree());
        assertEquals(0, this.hiddenX.getDegree());

        this.hiddenX.addTerm(25,12);
        assertEquals(12, this.hiddenX.getDegree());

    }

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

    }

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

    @Test
    public void testToString(){

        assertEquals("4x^3 +3x^1 -5",this.p1.toString());
        assertEquals("-2x^5 -3x^4 +11x^1 -5", this.p2.toString());
        assertEquals("6x^1 -5", this.p3.toString());
        assertEquals("1", this.hiddenX.toString());
        assertEquals("1x^10 +2x^9 +3x^8 -4x^7 +5x^6 -6x^5 +7x^4 -8x^3 -9x^2 +2x^1 +9", this.hyper.toString());
        assertEquals("0", this.empty.toString());
    }


}