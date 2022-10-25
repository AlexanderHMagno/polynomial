package polynomial;

import java.lang.reflect.Array;
import java.util.*;

public abstract class PolynomialAbstract implements Polynomial {

    TreeMap<Integer, Integer> PolynomialTree = new TreeMap<Integer, Integer >(Collections.reverseOrder());
    public PolynomialAbstract() {

    }

    public PolynomialAbstract (String PolyString) {
        String[] terms = PolyString
                .toLowerCase()
                .replace(" ", "")
                .split("(?=\\+|\\-)");

        for (String term:terms) {
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
        return null;
    }

    @Override
    public void addTerm(int coefficient, int power) throws IllegalArgumentException {

        PolynomialTree.put(power, coefficient);

    }

    @Override
    public boolean isSame(Polynomial poly) {
        return false;
    }

    @Override
    public double evaluate(double x) {
        return 0;
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

        System.out.println(data);
        return data.trim();
    }
}
