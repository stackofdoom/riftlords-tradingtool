package de.riftlords.main.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class ShipEquipment {

	// resource name
	@Column(name = "name")
	private String name;

	@Column(name = "price", nullable = false)
	private int price;

	// The price at which the resource is traded

	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "SE_SEQ", sequenceName = "SE_SEQ", allocationSize = 100)
	private int uid;

	public ShipEquipment(){
		
	}
	
	public ShipEquipment(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}


	public String getName() {
		return name;
	}


	public int getPrice() {
		return price;
	}


	public int getUid() {
		return uid;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}

}
