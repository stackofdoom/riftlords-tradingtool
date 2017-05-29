package de.riftlords.main.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class WayPoint {
	
	@Id
	@SequenceGenerator(name="WP_SEQ", sequenceName="WP_SEQ", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;
	//the id of the planet to fly towards
	@Embedded
	private Coordinate waypoint;
	//the order in which the planet should be flown towards.
	@Column(name="order")
	private int order;
	
	
	public WayPoint(){
		
	}
	
	
	public Coordinate getWaypoint() {
		return waypoint;
	}
	public void setWaypoint(Coordinate waypoint) {
		this.waypoint = waypoint;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

}
