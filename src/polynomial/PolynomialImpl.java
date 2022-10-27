package polynomial;

import java.util.Arrays;

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

    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {

        if (!(other instanceof PolynomialImpl)) throw new IllegalArgumentException();
        return super.add(other);
    }

    @Override
    public boolean equals(Object other) {

        //Check if other is an instance of PolynomialImpl
        if (!(other instanceof PolynomialImpl)) return false;
        return super.equals(other);
    }

}


