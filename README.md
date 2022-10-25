### Polynomials
1. Introduction

A polynomial is made of several terms, each term having a coefficient and a variable raised to a power. Polynomials are one-variable if all their terms contain only one variable. An example of such a polynomial is  𝑓(𝑥) = 3𝑥4−5𝑥3+2𝑥−4. This polynomial has four terms.

The degree of a polynomial is defined as the highest power of the variable in a term. In the above example, the degree of the polynomial is 4. The polynomials we deal with have only positive, integral powers. The coefficients of our polynomials are also integral numbers.

Two polynomials are the same if they contain the same terms.

Several algebraic operations are possible with polynomials. The simplest one is evaluating the polynomial for a specific value of the variable. For example, for a polynomial 𝑓(𝑥) = 3𝑥4−5𝑥3+2𝑥−4, its value at 𝑥 = 2.5 is defined as 𝑓(2.5)=3∗(2.5)4−5∗(2.5)3+2∗(2.5)−4 which is 40.0625.

Polynomials can be added together to create a new polynomial. The addition is performed by combining all the terms and adding the coefficients of the terms with the same power. For example 3𝑥4−5𝑥3+2𝑥−4 + 2𝑥3+2𝑥2+4 = 3𝑥4−3𝑥3+2𝑥2+2𝑥. The degree of the sum is the maximum of the degrees of the two polynomials.

 

2. What to do

Package

Name of package polynomial

 
This implementation contains the following methods

A method addTerm that takes a coefficient and a power (both integral numbers) and adds the resulting term to the polynomial. (This will enable you to build a polynomial term-by-term.) It should throw an IllegalArgumentException if a negative power is passed to it.
A method getDegree that returns the degree of this polynomial.
A method getCoefficient that takes a power and returns the coefficient for the term with that power.
A method evaluate that takes a double-precision decimal number and returns a double-precision result.
A method isSame that checks if another polynomial belongs to the same concrete type and has the same terms.
A method add that takes another Polynomial object and returns the polynomial obtained by adding the two polynomials. Any implementation should ensure that this method does not mutate either polynomial.
 

This class:
- Stores only terms with non-zero coefficients.
- Stores the polynomial terms in decreasing order of their powers.
- Has a constructor with no parameters that creates a polynomial with no terms, i.e. the polynomial 0.
- Has another constructor that takes a polynomial as a string, parses it and creates the polynomial accordingly. The string contains the polynomial, with each term separated by a space. The following examples should work with your constructor:

  *  "4x^3 +3x^1 -5"
  * "-3x^4 -2x^5 -5 +11x^1"



ToString Method: 

A polynomial of nothing should return "0". The following examples should help you infer the required format (pay attention to spaces):
5x2 + 4x - 2 creates the string "5x^2 +4x^1 -2"
-50x3 + x2 + 3 creates the string "-50x^3 +1x^2 +3"
4x + 2x5 - 3x2 -10 creates the string "2x^5 -3x^2 +4x^1 -10"


 
