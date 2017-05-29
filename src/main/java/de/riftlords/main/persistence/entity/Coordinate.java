package de.riftlords.main.persistence.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class Coordinate {
	
	private static String REGEX = "^(\\d{3}):(\\d{3})$";
	
	/*
	 * FIELDS
	 */
	
	@Column(name="xcoordinate")
	private int xcoordinate;
	
	@Column(name="ycoordinate")
	private int ycoordinate;
	
	/*
	 * CONSTRUCTORS
	 */
	
	public Coordinate(){
		//empty standard constructor
	}
	
	public Coordinate(int x, int y){
		xcoordinate = x;
		ycoordinate = y;
	}
	
	public Coordinate(String coordinate){
		this();
		int x,y;
		
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(coordinate);
		if(matcher.matches()){
			x = Integer.valueOf(matcher.group(1));
			y = Integer.valueOf(matcher.group(2));
		}else{
			x = -500;
			y = -500;
		}
		this.xcoordinate = x;
		this.ycoordinate = y;
		
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public int getXcoordinate() {
		return xcoordinate;
	}

	public void setXcoordinate(int xcoordinate) {
		this.xcoordinate = xcoordinate;
	}

	public int getYcoordinate() {
		return ycoordinate;
	}

	public void setYcoordinate(int ycoordinate) {
		this.ycoordinate = ycoordinate;
	}
	
	/*
	 * CLASS METHODS
	 */
	
	@Override
	public String toString(){
		return "(" + xcoordinate + ":" + ycoordinate + ")";
	}
	
	
	
	

}
