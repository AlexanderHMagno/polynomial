package polynomial;

public class PolynomialImpl extends PolynomialAbstract {
    public PolynomialImpl(String PolyString) {
        super(PolyString);
    }
    public PolynomialImpl() {
        super();
    }


    public static void main(String[] args) {

        Polynomial test = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1 + 10x");


        System.out.println(test.toString());
    }
}


