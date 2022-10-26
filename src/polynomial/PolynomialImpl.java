package polynomial;

/**
 * This Class represents a Polynomial it extends PolynomialAbstract.
 */
public class PolynomialImpl extends PolynomialAbstract {

    /**
     * Constructor that implements a Polynomial from a string.
     * @param PolyString One single variable Polynomial.
     * @throws IllegalArgumentException If there is a negative power in the Polynomial.
     */
    public PolynomialImpl(String PolyString) throws IllegalArgumentException {
        super(PolyString);
    }

    /**
     * Constructor that implements an Empty Polynomial.
     */
    public PolynomialImpl() {
        super();
    }

}


