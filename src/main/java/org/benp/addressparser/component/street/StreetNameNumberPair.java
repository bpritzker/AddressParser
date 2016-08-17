package org.benp.addressparser.component.street;


/**
 * I didn't want to just return a pair because well... that is lazy and doesn't tell you about 
 * the result. Created this class just for that.
 * @author Ben P
 *
 */
public class StreetNameNumberPair {
	

	private int streetNumber;
	private String streetNumberSecondPart;

	public StreetNameNumberPair(int inStreetNumber, String inStreetNumberSecondPart) {
		streetNumber = inStreetNumber;
		streetNumberSecondPart = inStreetNumberSecondPart;
	}

	public int getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getStreetNumberSecondPart() {
		return streetNumberSecondPart;
	}

	public void setStreetNumberSecondPart(String streetNumberSecondPart) {
		this.streetNumberSecondPart = streetNumberSecondPart;
	}


	

}
