package de.riftlords.main.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class MarketEntry {

	/*
	 * FIELDS
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name="MaEn_SEQ", sequenceName="MaEn_SEQ", allocationSize=100)
	@Column(name = "uid")
	private int uid;

	//resource name
	@Column(name = "name")
	private String name;

	//true if the planet buys the product for the given price
	@Column(name="buys", nullable=false)
	private boolean buying;
	

//	 The planet sells this resource

	@Column(name = "sells", nullable = false)
	private boolean selling;
	
//	True, if the item can only be bought limited times per turn
	@Column(name = "limited", nullable = false)
	private boolean limitedPurchase;


//	The price at which the resource is traded

	@Column(name = "price", nullable = false)
	private int price;

	/*
	 * CONSTRUCTORS
	 */
	
	public MarketEntry() {
		// default constructor
	}
	
	public MarketEntry(String name, boolean buys, boolean sells, boolean limited, int price) {
		super();
		this.name = name;
		this.buying = buys;
		this.selling = sells;
		this.limitedPurchase = limited;
		this.price = price;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelling() {
		return selling;
	}

	public void setSelling(boolean selling) {
		this.selling = selling;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}


	public boolean isBuying() {
		return buying;
	}

	public void setBuying(boolean buying) {
		this.buying = buying;
	}

	public boolean isLimitedPurchase() {
		return limitedPurchase;
	}

	public void setLimitedPurchase(boolean limitedPurchase) {
		this.limitedPurchase = limitedPurchase;
	}

	@Override
	public String toString() {
		return "MarketEntry [uid=" + uid + ", name=" + name + ", buying=" + buying + ", selling=" + selling
				+ ", limitedPurchase=" + limitedPurchase + ", price=" + price + "]";
	}
	

}
