package polynomial;

import java.util.*;

/**
 * This Class represents a Polynomial it implements a Polynomial Interface, it has a TreeMap and
 * a varLabel.
 */
public class PolynomialImpl implements Polynomial {

    private final TreeMap<Integer, Integer> PolynomialTree = new TreeMap<>(Collections.reverseOrder());
    private char varLabel = 'x';

    /**
     * Constructor that implements an Empty Polynomial.
     */
    public PolynomialImpl() {}

    /**
     * Constructor that implements a Polynomial from a string.
     * @param PolyString One single variable Polynomial.
     * @throws IllegalArgumentException If there is a negative power in the Polynomial.
     */
    public PolynomialImpl (String PolyString) throws IllegalArgumentException {

        //If Empty string we don't need to check more
        if (PolyString.trim().length() == 0) return;

        //Check If the PolyString is using a different label than x
        String options = PolyString.toLowerCase().replaceAll("[^a-z]","");
        if (options.length() > 0) this.varLabel = options.charAt(0);

        //Check find the terms of this Polynomial
        String[] terms = PolyString
                .toLowerCase()
                .split(" ");

        //Check each term, to verify the validity and add to the three
        Arrays.stream(terms).forEach(term-> {
            try {
                String[] bounds = term.split("\\^");
                int coefficient = Integer.parseInt(bounds[0].replaceAll( "" + this.varLabel,""));
                int power = (bounds.length > 1) ? Integer.parseInt(bounds[1]) :
                        (bounds[0].indexOf(this.varLabel) >= 0 ? 1 : 0);

                this.addTerm(coefficient,power);

            } catch (IllegalArgumentException Error) {
                throw new IllegalArgumentException(Error);
            }
        });
    }

    /**
     * Add this polynomial to another and return the result as another polynomial.
     * Note: As per implementation, any polynomial with different varLabel will be added as equals,
     *      as an abstraction x = [a-z] therefore, 4x^2 + 4y^2 = 8y^2 the varLabel to be used
     *      in the new object will depend on the other Polynomial varLAbel that is added to this.
     * @param other the other polynomial to be added
     * @return A polynomial with the sum of terms of this and other Polynomial.
     * @throws IllegalArgumentException If other is not a concrete class of the same type Polynomial.
     */
    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {

        //Only Add objects of the same concrete class
        if (!(other instanceof PolynomialImpl)) throw new IllegalArgumentException();

        Polynomial merge = new PolynomialImpl(other.toString());

        //Add other terms
        for (Map.Entry<Integer,Integer> term: this.PolynomialTree.entrySet()) {
            merge.addTerm(term.getValue(),term.getKey());
        }

        return merge;
    }

    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {

        //If Power is negative throw an error.
        if(power < 0) throw new IllegalArgumentException("No negative power allowed");

        //This class should store only terms with non-zero coefficients.
        if(coefficient == 0) return;

        //Add coefficients if needed
        int preCoefficient = this.getCoefficient(power) + coefficient;

        //Remove 0 coefficient from the treeMap
        if (preCoefficient == 0) this.PolynomialTree.remove(power);
        //If different from 0 update the power
        else this.PolynomialTree.put(power, preCoefficient);

    }

    @Override
    public boolean isSame(Polynomial poly) {
        return this.equals(poly);
    }

    @Override
    public double evaluate(double x) {

        double evaluator = 0    ;
        //Add other terms
        for (Map.Entry<Integer,Integer> term: this.PolynomialTree.entrySet()) {
            evaluator += term.getValue() * (Math.pow(x,term.getKey()));
        }

        return evaluator;
    }

    @Override
    public int getCoefficient(int power) {
        return this.PolynomialTree.get(power) == null ? 0 : this.PolynomialTree.get(power);
    }

    @Override
    public int getDegree() {
        return this.PolynomialTree.firstKey();
    }

    /**
     * This will format the MapTree from root to lower key
     * @return string with format from higher key to lower e.g "4x^2 +4x^1 -2"
     */
    @Override
    public String toString() {

        //If its Empty return 0
        if (this.PolynomialTree.isEmpty()) return "0";

        String data = "";

        for(Map.Entry<Integer,Integer> term : this.PolynomialTree.entrySet()) {
            Integer power = term.getKey();
            Integer coefficient = term.getValue();

            //Check if it needs to add a plus symbol
            String addSymbol = (coefficient > 0 && power != this.getDegree())? "+" : "";
            data += addSymbol + coefficient;
            data += power == 0 ? "" : this.varLabel + "^" + power + " " ;
        }

        return data.trim();
    }

    @Override
    public boolean equals(Object other) {

        // If the object is compared with itself then return true
        if (other == this) return true;
        //Check if other is an instance of PolynomialImpl
        if (!(other instanceof PolynomialImpl)) return false;
        // typecast other to Polynomial so that we can compare data members
        Polynomial otherPoly = (Polynomial) other;

        // Compare the data to String which is a representation of this object and its terms
        return this.toString().equals(otherPoly.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.PolynomialTree.toString());
    }
}


