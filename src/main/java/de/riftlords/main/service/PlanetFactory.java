/**
 * 
 */
package de.riftlords.main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.riftlords.main.persistence.entity.Coordinate;
import de.riftlords.main.persistence.entity.MarketEntry;
import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.ShipEquipment;

/**
 * @author pasc2de
 *
 */
public class PlanetFactory{
	
	
	private static String unwantedCharsRegex = "[+\\-|]";
	private static String HEADER_GROUP_REGEX = "\\s*(\\w*\\s*\\D*)\\s*(\\d*:\\d*)\\s*(\\w*\\s*\\w)\\s*(Riftstern)?\\s*Runde\\s*(\\d*)\\s*";
	private static PlanetFactory instance;

	public static Planet createPlanet(String aString) {
		Planet result = parseStringVariant(aString);
		return result;
	}

	public static PlanetFactory getInstance() {
		if(instance == null){
			instance = new PlanetFactory();
		}
		return instance;
	}
	
	private static Planet parseStringVariant(String s){
		Planet result = new Planet();
		
		ArrayList<MarketEntry> market = new ArrayList<>();
		ArrayList<ShipEquipment> shipEquipment = new ArrayList<>();
		
		String workString = s.replaceAll(unwantedCharsRegex, "");
		//reduce all whitespaces to a single whitespace
		workString = workString.trim();
		String[] rows = workString.split("\\n");
		
		ArrayList<String> rowslist = new ArrayList<>();
		
		for (int i = 0; i < rows.length; i++) {
			if(!rows[i].trim().equals("")){
				rowslist.add(rows[i]);
			}
		}
		
		System.out.println(Arrays.toString(rowslist.toArray()));
		String toParse;
		
		/*
		 * PARSE HEADER TO GENERAL PLANET INFO
		 */
		extractHeaderInformation(result, rowslist);
		
		//parse technologies:
		toParse = rowslist.get(1) + " " + rowslist.get(2);
		toParse = toParse.trim();
		String[] temp;
		temp = toParse.split("\\s+");
		System.out.println(Arrays.toString(temp));
		
		/*
		 * EXTRACT TECHNOLOGIES FROM DATA
		 */
		if(!"Export".equals(temp[0])){
			extractTechnologyInformation(shipEquipment, temp);			
		}
		
	
		//find out which index
		int index = 0;
		for (Iterator<String> iterator = rowslist.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			
			if(string.contains("Solar")){		
				index = rowslist.indexOf(string);
				break;
			}
		}
		
		/*
		 * PARSE MARKET INFORMATION
		 */
		extractMarketInformation(market, rowslist, index);
		
		
		/*
		 * SET PRICES IN PLANET
		 */
		
		result.setPrices(market);
		result.setTechs(shipEquipment);
		
		return result;
	}

	/**
	 * extracts the information on what is sold and what is bought on the planet in terms of commodities
	 * @param market
	 * @param rowslist
	 * @param index
	 */
	private static void extractMarketInformation(ArrayList<MarketEntry> market, ArrayList<String> rowslist, int index) {
		for (int i = index+1; i < rowslist.size(); i++) {
			
			String row = rowslist.get(i);
			
			String[] tmp = row.trim().split("\\s+");
			System.out.println(Arrays.toString(tmp));
			if (tmp.length == 4){
				String product = tmp[0];
				int price = Integer.valueOf(tmp[1]);
				boolean sells = true;
				boolean once = false;
				boolean buys = false;
				
				market.add(new MarketEntry(product, buys, sells, once, price));
				
				product = tmp[2];
				price = Integer.valueOf(tmp[3]);
				sells = false;
				buys = true;
				
				market.add(new MarketEntry(product, buys, sells, once, price));
				
				
			}else{
				System.out.println("Why? There are not 4 rows in the import/export section, or are there?");
			}
		}
	}

	/**
	 * extracts the information on what ship technologies are available at this planet
	 * @param market
	 * @param temp
	 */
	private static void extractTechnologyInformation(ArrayList<ShipEquipment> shipEq, String[] temp) {
		for (int i = 0; i < temp.length-1; i+=2) {
			String product = temp[i];
			if(product.equalsIgnoreCase("export")){
				break;
			}
			int price = Integer.valueOf(temp[i+1]);
//			if(product.equals("Schilde")){
//				once = false;
//			}
			
			shipEq.add(new ShipEquipment(product, price));
		}
	}

	/**
	 * extracts the basic planet info from the header line; i.e. name, coords, type and last visit
	 * @param result
	 * @param rowslist
	 */
	private static void extractHeaderInformation(Planet result, ArrayList<String> rowslist) {
		String coordinates;
		String type;
		String name;
		int last_visit;
		String toParse;
		toParse = rowslist.get(0);
		
		Pattern pattern = Pattern.compile(HEADER_GROUP_REGEX);
		Matcher matcher = pattern.matcher(toParse);
		int riftstar_index = 0;
		if (matcher.matches()) {
			System.out.println("match!!!");
			System.out.println(matcher.group(1));
		    name = matcher.group(1).trim();
		    name.replaceAll("\\s*", " ");
		    System.out.println(matcher.group(2));
		    coordinates = matcher.group(2).trim();
		    System.out.println(matcher.group(3));
		    type = matcher.group(3).trim();
		    type.replaceAll("\\s*", " ");
		    if(matcher.group(4) != null){
		    	type = type + " Rift";
		    }
		    System.out.println(matcher.group(5));
		    last_visit = Integer.valueOf(matcher.group(5).trim());
		    
		    System.out.println("Name: " + name + ", Coordinates: " + coordinates + 
		    		", Type: " + type + ", Runde: " + last_visit);
		    
		    result.setName(name);
			result.setType(type);
			result.setCoordinates(new Coordinate(coordinates));
			result.setLastVisit(last_visit);
		}
	}
	

	
	

}
