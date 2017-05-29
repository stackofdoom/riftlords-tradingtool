/**
 * 
 */
package de.riftlords.main.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.riftlords.main.persistence.entity.Coordinate;
import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;
import de.riftlords.main.persistence.repository.TradeRouteRepository;

/**
 * @author pasc2de
 *
 */
@Service
public class RouteViewService {
	
	@Autowired
	private TradeRouteRepository tradeRouteDao;

	public void storeTradeRoutes(List<TradeRoute> routes) {
		tradeRouteDao.save(routes);
		
	}

	public List<TradeRoute> getTradeRoutes() {
		return tradeRouteDao.findAll();
	}
	
	public List<TradeRoute> getTradeRoutes(List<Planet> newPlanets) {
		List<String> planets = newPlanets.stream()
				.map(Planet::getCoordinates)
				.map(Coordinate::toString)
				.collect(Collectors.toList());

		return tradeRouteDao.findByExportPlanetInOrImportPlanetIn(planets, planets);
	}

	public List<TradeRoute> getTradeRoutes(Coordinate exp, Coordinate imp) {
		return getTradeRoutes(exp.toString(), imp.toString());
	}
	
	public List<TradeRoute> getTradeRoutes(String exp, String imp){
		return tradeRouteDao.findByExportPlanetAndImportPlanet(exp, imp);
	}

	public List<TradeRoute> getTradeRoutes(Planet exportPlanet, Planet importPlanet) {
		return getTradeRoutes(exportPlanet.getCoordinates(), importPlanet.getCoordinates());
	}

	public List<TradeRoute> getTradeRoutesByDistance(boolean motorfilter, int motors) {
		if(motorfilter){
			return tradeRouteDao.findByTotaldistanceLessThanEqual(motors);
		}else{
			return getTradeRoutes();
		}
	}
	

	

}
