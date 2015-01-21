package poly;

import java.io.*;
import java.util.StringTokenizer;

class Term {

	public float coeff;
	public int degree;
	

	// Initializes an instance with given coefficient and degree.
	public Term(float coeff, int degree) {
		this.coeff = coeff;
		this.degree = degree;
	}
	
	
	public boolean equals(Object other) {				//@see java.lang.Object#equals(java.lang.Object)
		return other != null &&
		other instanceof Term &&
		coeff == ((Term)other).coeff &&
		degree == ((Term)other).degree;
	}
	
	public String toString() {							//@see java.lang.Object#toString()
		if (degree == 0) {
			return coeff + "";
		} else if (degree == 1) {
			return coeff + "x";
		} else {
			return coeff + "x^" + degree;
		}
	}
}

//This class implements a linked list node that contains a Term instance.
class Node {
	
	Term term;
	Node next;
	
//Initializes this node with a term with given coefficient and degree, pointing to the given next node.
	public Node(float coeff, int degree, Node next) {
		term = new Term(coeff, degree);
		this.next = next;
	}
}

//This class implements a polynomial.
public class Polynomial {
	
	Node poly;											// Assigns pointer to front of the list
	public Polynomial() {								// Initializes this polynomial to empty, i.e. there are no terms.
		poly = null;
	}
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * with the GUARENTEE that degrees will be in descending order. For example:
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * which represents the polynomial:
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * 
	 * @param br BufferedReader from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 */
	public Polynomial(BufferedReader br) throws IOException {
		String line;
		StringTokenizer tokenizer;
		float coeff;
		int degree;
		
		poly = null;
		
		while ((line = br.readLine()) != null) {
			tokenizer = new StringTokenizer(line);
			coeff = Float.parseFloat(tokenizer.nextToken());
			degree = Integer.parseInt(tokenizer.nextToken());
			poly = new Node(coeff, degree, poly);
		}
	}
	
	
	public Polynomial add(Polynomial p) {
			Node curr = poly;
			Node pcurr = p.poly;
			
			// New third poly, the sum
			Polynomial polySum = new Polynomial();
			Node temp = null;
			float currCoefSum;
			
			
			// The while loop if either polynomial runs out of terms. This avoids nullpointerexception
			while (curr != null) {
				
				if (pcurr == null){
					temp = new Node(curr.term.coeff, curr.term.degree, temp);
					curr = curr.next;
					continue;
				}
				
				else if (curr.term.degree == pcurr.term.degree) {
					currCoefSum = curr.term.coeff + pcurr.term.coeff;
					temp = new Node(currCoefSum, curr.term.degree, temp);
					
					curr = curr.next;
					pcurr = pcurr.next;
					
				}
				
				else if (curr.term.degree < pcurr.term.degree)
				{
					temp = new Node(curr.term.coeff, curr.term.degree, temp);
					curr = curr.next;
				}
				
				else if (curr.term.degree > pcurr.term.degree)
				{
					temp = new Node(pcurr.term.coeff, pcurr.term.degree, temp);
					pcurr = pcurr.next;
				}
			}
				
			
			
			// The while loop if the second polynomial has null shortcomings.
			while (pcurr != null) {
				
				if (curr == null){
					temp = new Node(pcurr.term.coeff, pcurr.term.degree, temp);
					pcurr = pcurr.next;
					continue;
				}
				
				
				else if (curr.term.degree == pcurr.term.degree) {
					currCoefSum = curr.term.coeff + pcurr.term.coeff;
					temp = new Node(currCoefSum, curr.term.degree, temp);
					
					curr = curr.next;
					pcurr = pcurr.next;
					
				}
				
				else if (curr.term.degree < pcurr.term.degree)
				{
					temp = new Node(curr.term.coeff, curr.term.degree, temp);
					curr = curr.next;
				}
				
				else if (curr.term.degree > pcurr.term.degree)
				{
					temp = new Node(pcurr.term.coeff, pcurr.term.degree, temp);
					pcurr = pcurr.next;
				}
			}
			
			// Reverses the Polynomial into the correct sequence
			Node flip = temp;
			Node ans = null;
			while (flip != null)
			{
				if (flip.term.coeff == 0)
				{
					flip = flip.next;
					continue;
				}
				else 
				{
					ans = new Node(flip.term.coeff, flip.term.degree, ans);
					flip = flip.next;
				}
			}
			
			if (ans == null)
				return null;
			else
			{
				polySum.poly = ans;
				return polySum;
			}	
	}

	
	
	public Polynomial multiply(Polynomial p) {
		Node curr = poly;
		Node pcurr = p.poly;
		
		// Check if either polynomial is empty. If so, simply return 0. The answer will result in 0.
		if (poly == null || p.poly == null)
		{
			Polynomial autozero = new Polynomial();
			Node zeroprod = null;
			zeroprod = new Node(0, 0, zeroprod);
			autozero.poly = zeroprod;
			return autozero;
		}
		
		float currProdCoef;
		int currProdDegree;
		
		// Serves for the following linked list
		Node prodtemp = null;
		Polynomial polytemp = new Polynomial();
		Polynomial polyfinalsum = new Polynomial();

		// Add all of the terms in the correct sequence, get the final answer, sorted.
		while (curr != null)
		{
			while (pcurr != null)
			{
				currProdCoef = curr.term.coeff * pcurr.term.coeff;
				currProdDegree = curr.term.degree + pcurr.term.degree;
				prodtemp = new Node(currProdCoef, currProdDegree, prodtemp);				
				pcurr = pcurr.next;
			}
			
			// Reverses the Polynomial into the correct order
			Node flip = prodtemp;
			Node prodtempflipped = null;
			while (flip != null)
			{
				if (flip.term.coeff == 0)
				{
					flip = flip.next;
					continue;
				}
				else 
				{
					prodtempflipped = new Node(flip.term.coeff, flip.term.degree, prodtempflipped);
					flip = flip.next;
				}
			} // end of reversal code
			
			polytemp.poly = prodtempflipped;
			
			//System.out.println(polytemp);
			polyfinalsum = polyfinalsum.add(polytemp);					// Utilize add method, adds the short prodtemp poly that was created in the
																		//pcurr while loop. Since that poly is already sorted, just add these multiple
			curr = curr.next;											// polynomials, bit by bit at a time. Add these results to a finalpolynomial.
			pcurr = p.poly;												// p.poly next reference reset.
			prodtemp = null;											// prodtemp poly reset. So it eliminates the previous short-segment of the larger multiply-polynomial and allows for a new short-segment
		}
		return polyfinalsum;
	}
	
	public float evaluate(float x) {
		double sum = 0;
		float currCoef;
		int currdegree;
		Node curr = poly;
		
		while (curr != null) {
			currCoef = curr.term.coeff;
			currdegree = curr.term.degree;
			
			sum = sum + currCoef * Math.pow(x, currdegree);
			curr = curr.next;
		}
		
		return (float) sum;
	}
	

	public String toString() {
		String retval;
		
		if (poly == null) {
			return "0";
		} else {
			retval = poly.term.toString();
			for (Node current = poly.next ;
			current != null ;
			current = current.next) {
				retval = current.term.toString() + " + " + retval;
			}
			return retval;
		}
	}
}
