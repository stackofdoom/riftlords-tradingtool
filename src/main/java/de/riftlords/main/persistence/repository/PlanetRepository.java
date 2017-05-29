package de.riftlords.main.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.riftlords.main.persistence.entity.Coordinate;
import de.riftlords.main.persistence.entity.Planet;


/**
 * Data access object for planet datastructures
 * @author pasc2de
 *
 */
public interface PlanetRepository extends JpaRepository<Planet, Long>{

	public Planet findByUid(int theID);
	
	/**
	 * returns a list containing only the most recent planets,
	 * where recent means that only those will be displayed that only have
	 * prior last visits, but no later ones.
	 * @return
	 */
	@Query("FROM Planet p WHERE (p.coordinates.xcoordinate, p.coordinates.ycoordinate, p.lastVisit) in"
				+ "(SELECT p1.coordinates.xcoordinate, p1.coordinates.ycoordinate, max(p1.lastVisit) FROM Planet p1 group by p1.coordinates)")
	public List<Planet> findAllRecent();
	
	@Query("FROM Planet p WHERE (p.coordinates.xcoordinate, p.coordinates.ycoordinate, p.lastVisit) in"
				+ "(SELECT p1.coordinates.xcoordinate, p1.coordinates.ycoordinate, max(p1.lastVisit) FROM Planet p1 group by p1.coordinates)")
	public List<Planet> findAllByCoordinatesAndMaxDistance(Coordinate coordinate, int maxDistance);
	
	public Planet findByCoordinatesAndLastVisit(Coordinate coordinates, int lastVisit);
	
	public Planet findByCoordinatesAndArchived(Coordinate coordinates, boolean archived);

	
	

}
