package de.riftlords.main.service;

import de.riftlords.main.persistence.entity.Planet;

/**
 * Service that converts String data into Planet objects
 * @author pasc2de
 *
 */
public class PlanetConversionService {
	
	/**
	 * 
	 * @param theString
	 * @return
	 */
	public static Planet planetRawToEntityService(String theString){

		return PlanetFactory.createPlanet(theString);
	}

}
