/**
 * 
 */
package de.riftlords.main.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.repository.PlanetRepository;


/**
 * @author pasc2de
 *
 */
@Service
public class PlanetViewService{

	@Autowired
	private PlanetRepository planetRepo;
	
	@Autowired
	private ComputeRouteService crs;

	@Transactional
	public List<Planet> getPlanets(){
		List<Planet> planets = planetRepo.findAllRecent();
		System.out.println("retrieving " + planets.size() + " planets from repository");
		return planets;
//		return planetRepo.findAllRecent();
	}
	
	@Transactional
	public void importPlanet(String splanet){
		Planet planet = PlanetConversionService.planetRawToEntityService(splanet);
		
		System.out.println(planet.getCoordinates().toString());
		Planet planetInDB = planetRepo.findByCoordinatesAndLastVisit(planet.getCoordinates(), planet.getLastVisit());
		
		if(planetInDB != null){
			planet = planetInDB;
		}
		
		planet = planetRepo.save(planet);
		
		
		crs.computeRoutesSelectively(10000, Arrays.asList(planet));
	}
	
	@Transactional
	public Planet getPlanet(int theID){
		return planetRepo.findByUid(theID);
	}
	
}
