package de.riftlords.main.persistence.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.ColumnDefault;

@Entity
public class TradeRoute {	
	
	@Id
	@SequenceGenerator(name="TR_SEQ", sequenceName="TR_SEQ", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long uid;

	@Column(name="commodity")
	private String commodity;
	
	@Column(name="export_price")
	private int exportPrice;
	
	@Column(name="import_price")
	private int importPrice;
	
	@Column(name="export_planet")
	private String exportPlanet;
	
	@Column(name="import_planet")
	private String importPlanet;
	
	@Column(name="total_distance")
	@ColumnDefault("0")
	private int totaldistance;
	
	public void setCommodity(String name) {
		commodity = name;
	}

	public void setExportPrice(int price) {
		exportPrice = price;	
	}

	public void setImportPrice(int price) {
		importPrice = price;
	}
	
	public int getAbsoluteWinningsPerUnit(){
		return importPrice - exportPrice;
	}
	
	public double getRelativeWinningsPerUnit(){
		return ((((double)importPrice)/((double)exportPrice))-1);
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getCommodity() {
		return commodity;
	}

	public int getExportPrice() {
		return exportPrice;
	}

	public int getImportPrice() {
		return importPrice;
	}
	


	public String getExportPlanet() {
		return exportPlanet;
	}

	public void setExportPlanet(String exportPlanet) {
		this.exportPlanet = exportPlanet;
	}

	public String getImportPlanet() {
		return importPlanet;
	}

	public void setImportPlanet(String importPlanet) {
		this.importPlanet = importPlanet;
	}

	public int getTotaldistance() {
		return totaldistance;
	}

	public void setTotaldistance(int totaldistance) {
		this.totaldistance = totaldistance;
	}

	@Override
	public String toString(){
		return "Route ["+exportPlanet.toString()+"-"+importPlanet.toString()+" | " + commodity + " at price " + exportPrice + " with value of " + importPrice + "]";
	}
	
	public int getTotalDistance(){
		return totaldistance;
	}
	
	public void setTotalDistancE(int dist){
		totaldistance = dist;
	}
	
//	public double getWinningsPerTurn(int motors){
//		if(motors >= totaldistance){
//			return getAbsoluteWinningsPerUnit();
//		}else{
//			double dist = totaldistance;
//			int turns = 0;
//			while(dist > motors){
//				dist = dist - motors;
//				turns ++;
//				
//			}
//			return getAbsoluteWinningsPerUnit() / turns;
//		}
//	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commodity == null) ? 0 : commodity.hashCode());
		result = prime * result + ((exportPlanet == null) ? 0 : exportPlanet.hashCode());
		result = prime * result + ((importPlanet == null) ? 0 : importPlanet.hashCode());
		result = prime * result + totaldistance;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeRoute other = (TradeRoute) obj;
		if (commodity == null) {
			if (other.commodity != null)
				return false;
		} else if (!commodity.equals(other.commodity))
			return false;
		if (exportPlanet == null) {
			if (other.exportPlanet != null)
				return false;
		} else if (!exportPlanet.equals(other.exportPlanet))
			return false;
		if (importPlanet == null) {
			if (other.importPlanet != null)
				return false;
		} else if (!importPlanet.equals(other.importPlanet))
			return false;
		if (totaldistance != other.totaldistance)
			return false;
		return true;
	}
	
}
