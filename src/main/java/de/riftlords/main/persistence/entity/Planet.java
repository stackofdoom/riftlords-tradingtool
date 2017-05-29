package de.riftlords.main.persistence.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@NamedQueries({
	@NamedQuery(name="Planet.findAllRecent", query="FROM Planet p WHERE (p.coordinates.xcoordinate, p.coordinates.ycoordinate, p.lastVisit) in"
				+ "(SELECT p1.coordinates.xcoordinate, p1.coordinates.ycoordinate, max(p1.lastVisit) FROM Planet p1 group by p1.coordinates)"),
	@NamedQuery(name="Planet.findRecent", query="FROM Planet p"),
	@NamedQuery(name="findAllByCoordinatesAndMaxDistance", query="FROM Planet p WHERE (p.coordinates.xcoordinate, p.coordinates.ycoordinate, p.lastVisit) in"
				+ "(SELECT p1.coordinates.xcoordinate, p1.coordinates.ycoordinate, max(p1.lastVisit) FROM Planet p1 group by p1.coordinates)")
})
@Entity
@Table(name="planet")
public class Planet {

	@Embedded
	@Column(name="coordinates")
	private Coordinate coordinates;
	
	@Id
	@SequenceGenerator(name="PL_SEQ", sequenceName="PL_SEQ", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int uid;

	@Column(name="type", nullable=false)
	private String type;
	
	// 	the name of the planet, may be null in case there are planets marked only by position
	@Column(name="name")
	private String name;
	
	//prices on this planet
	@JoinColumn(name="planetid")
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MarketEntry> prices;
	
	@JoinColumn(name="planetid")
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ShipEquipment> techs;
	
	
	@Column(name="lastVisit")
	private int lastVisit;
	
	//true, if this is not the latest info
	@Column(name="archived")
	private boolean archived;
	
	/*
	 * CONSTRUCTORS
	 */

	
	
	public Planet(String coordinates, String name, List<MarketEntry> prices, int lastVisit, String type) {
		
		this.coordinates = new Coordinate(coordinates);
		this.name = name;
		this.prices = prices;
		this.lastVisit = lastVisit;
		this.type = type;
	}
	
	public Planet(){
		//default constructor
	}
	
	/*
	 * GETTERS & SETTERS
	 */
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}	
	
	public Coordinate getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinate coordinates) {
		this.coordinates = coordinates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MarketEntry> getPrices() {
		return prices;
	}

	public void setPrices(List<MarketEntry> prices) {
		this.prices = prices;
	}

	public int getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(int lastVisit) {
		this.lastVisit = lastVisit;
	}

	@Override
	public String toString() {
		return "Planet [coordinates=" + coordinates.toString() + ", name=" + name + ", prices=" + prices.toString() + ", lastVisit="
				+ lastVisit + "]";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<ShipEquipment> getTechs() {
		return techs;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setTechs(List<ShipEquipment> techs) {
		this.techs = techs;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	

}
