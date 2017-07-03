package de.riftlords.main.service.traderoute.strategy;

import java.util.List;

import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;

public interface RouteStrategy {
	
	/**
	 * finds a route within the maximum distance between different Planets
	 * @return
	 */
	public List<TradeRoute> findPossibleRoute(int maxdist, List<Planet> planets);

	public String getName();

}
