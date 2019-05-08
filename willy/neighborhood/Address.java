package me.willyb.neighborhood;

public class Address {
	
	public int x;
	public int y;
	
	/**
	 * Generates a random address, within certain bounds.
	 * @param range The maximum value that can be had in the neighborhood the address is in. Address x and y values will be between 0 and range.
	 */
	public Address(int range) {
		
		int valueOne = (int) (Math.random() * range/10) * 10;
		int valueTwo = (int) (Math.random() * range/100) * 100;
		
		if(Math.random() > 0.5) {
			
			this.x = valueOne;
			this.y = valueTwo;
			
		} else {
			
			this.x = valueTwo;
			this.y = valueOne;
			
		}
		
	}
	
	/**
	 * Creates an Address object with predefined coordinates.
	 * @param x The x location of the Address.
	 * @param y The y location of the Address.
	 */
	public Address(int x, int y) {
		
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Creates an Address object using the String format used to store addresses.
	 * @param stringAddress The string Address to convert into an Address object.
	 */
	public Address(String stringAddress) {
		
		String[] addressParts = stringAddress.split(" ");
		
		/* 
		 * Need to trim 'st', 'nd', 'rd', 'th' from the end of the street number string for integer parsing
		 * So, the last two characters of the string are just removed here.
		 */
		char[] streetNumberChars = addressParts[2].toCharArray();
		streetNumberChars[streetNumberChars.length - 1] = ' ';
		streetNumberChars[streetNumberChars.length - 2] = ' ';
		String streetNumberString = new String(streetNumberChars);
		streetNumberString = streetNumberString.replaceAll("  ", "");
		
		int streetAddress = Integer.parseInt(addressParts[0]);
		int streetNumber = Integer.parseInt(streetNumberString) * 100;
		
		if(addressParts[1].equals("South")) {
			
			int placeHolder = streetAddress;
			streetAddress = streetNumber;
			streetNumber = placeHolder;
			
		}
		
		this.x = streetAddress;
		this.y = streetNumber;
		
	}
	
	/**
	 * Returns this Address in String format, for storage in a text file or for looking at by humans.
	 * @return A String containing this Address' information
	 */
	public String getStringFormat() {
		
		String direction = " East ";
		int streetAddress = x;
		int streetNumber = y;
		
		if(this.x % 100 == 0) {
			
			direction = " South ";
			streetAddress = y;
			streetNumber = x;
			
		}
		
		String ending = "th";
		
		switch(streetNumber) {
			
			case 1:
				ending = "st";
				break;
			case 2:
				ending = "nd";
				break;
			case 3:
				ending = "rd";
				break;
			default:
				break;
			
		}
		
		return Integer.toString(streetAddress) + direction + Integer.toString(streetNumber/100) + ending;
		
	}
	
	@Override
	public String toString() {
		
		return "(" + Integer.toString(x) + ", " + Integer.toString(y) + "), " + this.getStringFormat();
		
	}
	
}
