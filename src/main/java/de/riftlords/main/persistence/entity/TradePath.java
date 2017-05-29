/**
 * 
 */
package de.riftlords.main.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author pasc2de
 *
 */
@Entity
public class TradePath {
	
	@Id
	@SequenceGenerator(name="TP_SEQ", sequenceName="TP_SEQ", allocationSize=100)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long uid;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="uid")
	private TradeRoute route;
	
	@JoinColumn(name="pathid")
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<WayPoint> waypoints;
	
	@Column(name="min_drive")
	private int minDrive;
	
	
	public TradePath(){
		
	}
	
	public TradePath(TradeRoute route) {
		this.route = route;
	}

	public TradeRoute getRoute() {
		return route;
	}

	public void setRoute(TradeRoute route) {
		this.route = route;
	}

	public List<WayPoint> getWaypoints() {
		return waypoints;
	}

	public void setWaypoints(List<WayPoint> waypoints) {
		this.waypoints = waypoints;
	}
	
	public double getEarningsPerTurn(){
		int result = 0;
				
		if(waypoints.size() > 0){
			result = route.getAbsoluteWinningsPerUnit()/waypoints.size();
		}
		
		return result;
	}
	
	public int getMinDrive(){
		return minDrive;
	}
	
	public void setMinDrive(int mindrive){
		this.minDrive = mindrive;
	}
	
	
	
	

}
