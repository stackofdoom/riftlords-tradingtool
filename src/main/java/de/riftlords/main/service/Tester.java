package de.riftlords.main.service;

import java.io.InputStream;

import de.riftlords.main.persistence.entity.Planet;



public class Tester {
	
	public static void main(String[] args) {
		String text;
				// From Class, the path is relative to the package of the class unless
				// you include a leading slash, so if you don't want to use the current
				// package, include a slash like this:
		try {
			InputStream in = Tester.class.getResourceAsStream("parsertest.txt");
			text = convertStreamToString(in);
			System.out.println(text);
			System.out.println("\nStart parsing \n");
			Planet test = PlanetFactory.createPlanet(text);
			test.toString();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				

	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}
