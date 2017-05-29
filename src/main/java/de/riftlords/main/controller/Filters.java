package de.riftlords.main.controller;

public class Filters {

	private boolean motorFilter;
	private int num_motors;
	private boolean absoluteFilter;
	private int absolute_value;
	private boolean relativeFilter;
	private double relative_value;
	
	public Filters(){
		motorFilter = false;
		num_motors = 1;
		absoluteFilter=false;
		relativeFilter=false;
		absolute_value=1;
		relative_value=0.05;
	}

	public boolean isAbsoluteFilter() {
		return absoluteFilter;
	}

	public int getAbsolute_value() {
		return absolute_value;
	}

	public boolean isRelativeFilter() {
		return relativeFilter;
	}

	public double getRelative_value() {
		return relative_value;
	}

	public void setAbsoluteFilter(boolean absoluteFilter) {
		this.absoluteFilter = absoluteFilter;
	}

	public void setAbsolute_value(int absolute_value) {
		this.absolute_value = absolute_value;
	}

	public void setRelativeFilter(boolean relativeFilter) {
		this.relativeFilter = relativeFilter;
	}

	public void setRelative_value(double relative_value) {
		this.relative_value = relative_value;
	}

	public boolean isMotorFilter() {
		return motorFilter;
	}

	public int getNum_motors() {
		return num_motors;
	}

	public void setMotorFilter(boolean motorFilter) {
		this.motorFilter = motorFilter;
	}

	public void setNum_motors(int num_motors) {
		this.num_motors = num_motors;
	}

	@Override
	public String toString() {
		return "Filters [motorFilter=" + motorFilter + ", num_motors=" + num_motors + ", absoluteFilter="
				+ absoluteFilter + ", absolute_value=" + absolute_value + ", relativeFilter=" + relativeFilter
				+ ", relative_value=" + relative_value + "]";
	}


}
