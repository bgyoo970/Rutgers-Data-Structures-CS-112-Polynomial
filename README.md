# Rutgers-Data-Structures-CS-112-Polynomial
Fall 2013 with Andrew Tjang

This polynomial project can perform addition or multiplication operations on two polynomials or evaluate a polynomial expression given a x-value input. The code will read a polynomial from an input stream (from a file). The storage format of the polynomial is:

	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * with the GUARENTEE that degrees will be in descending order. For example:
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * will represent the polynomial:
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 
	 
	 The user must provide the name of the text files along with an input to indicate a specific instruction/operation to be performed on the polynomials. The input must be a numeric value between 1 to 4.
	 1. ADD
	 2. MULTIPLY
	 3. EVALUATE
	 4. EXIT
	 
Some example textfiles are provided: p1.txt, p2.txt, ptest1.txt, ptest2.txt, ptest1opp.txt, save1.txt, save2.txt

ALL USE OF THIS CODE MUST COMPLY WITH THE RUTGERS UNIVERSITY CODE OF STUDENT CONDUCT. http://eden.rutgers.edu/%7Epmj34/media/AcademicIntegrity.pdf
