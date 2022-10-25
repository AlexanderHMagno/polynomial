package polynomial;

import java.lang.reflect.Array;
import java.util.*;

public abstract class PolynomialAbstract implements Polynomial {

    TreeMap<Integer, Integer> PolynomialTree = new TreeMap<Integer, Integer >(Collections.reverseOrder());
    public PolynomialAbstract() {
            this.addTerm(0,0);
    }

    public PolynomialAbstract (String PolyString) throws IllegalArgumentException {
        String[] terms = PolyString
                .toLowerCase()
                .replace(" ", "")
                .replace("^-","!")
                .split("(?=\\+|\\-)");

        for (String term:terms) {

            //If power is negative we throw an error
            if(term.contains("!")) {
                throw new IllegalArgumentException();
            }

            String[] bounds = term.split("\\^");
            int coefficient = Integer.parseInt(bounds[0].replaceAll("[^0-9\\-]",""));
            int power;

            if (bounds.length > 1) {
                power = Integer.parseInt(bounds[1]);
            } else {
                //it contains a variable?
                power = bounds[0]
                        .replaceAll("[a-z]", "x")
                        .contains("x") ? 1 : 0 ;
            }

            this.addTerm(coefficient,power);
        }

    }

    @Override
    public Polynomial add(Polynomial other) throws IllegalArgumentException {

        Polynomial merge = new PolynomialImpl(other.toString());

        //Add other terms
        for (Map.Entry<Integer,Integer> term: this.PolynomialTree.entrySet()) {
            Integer power = term.getKey();
            Integer coefficient = term.getValue();
            merge.addTerm(coefficient,power);
        }

        return merge;
    }

    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {
        if(power < 0) {
            throw new IllegalArgumentException("No negative power allowed");
        }

        //Add coefficients if needed
        int preCoefficient = this.getCoefficient(power) + coefficient;

        PolynomialTree.put(power, preCoefficient);
    }

    @Override
    public boolean isSame(Polynomial poly) {

        if (this == poly) return true;
        if (!(poly instanceof PolynomialImpl)) return false;

        return this.toString().equals(poly.toString());
    }

    @Override
    public double evaluate(double x) {

        double evaluator = 0;

        //Add other terms
        for (Map.Entry<Integer,Integer> term: this.PolynomialTree.entrySet()) {
            Integer power = term.getKey();
            Integer coefficient = term.getValue();

            evaluator += coefficient * (Math.pow(x,power));
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

    @Override
    public String toString() {

        String data = "";
        for(Map.Entry<Integer,Integer> term : this.PolynomialTree.entrySet()) {
            Integer key = term.getKey();
            Integer value = term.getValue();

            //Check if it needs to add a plus symbol
            String addSymbol = (value > 0 && key != this.getDegree())? "+" : "";
            data += addSymbol + value;
            data += key == 0 ? "" : "x" + "^" + key + " " ;
        }

        return data.trim();
    }
}
