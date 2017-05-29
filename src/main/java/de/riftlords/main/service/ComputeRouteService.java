package de.riftlords.main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;
import de.riftlords.main.persistence.repository.TradeRouteRepository;
import de.riftlords.main.service.traderoute.strategy.RouteStrategy;
import de.riftlords.main.service.traderoute.strategy.SimpleRouteStrategy;

@Service
public class ComputeRouteService {

	private RouteStrategy strategy = new SimpleRouteStrategy();

	@Autowired
	private PlanetViewService planetViewService;

	@Autowired
	private RouteViewService routeviewService;

	@Autowired
	private TradeRouteRepository trRepository;
	
	public List<TradeRoute> computeRoutes(int maxdist, List<Planet> planets) {

		List<TradeRoute> result;
		System.out.println("Selecting Strategy for determining routes...." + strategy.getName());
		result = strategy.findPossibleRoute(maxdist, planets);

		List<TradeRoute> previousRoutes = trRepository.findAll();
		for (TradeRoute tr : previousRoutes) {
			for (TradeRoute str : result) {
				System.out.println("comparing routes " + str.toString() + " and " + tr.toString());
				if (sameRoute(tr, str)) {
					update(tr, str);
				}
				System.out.println("Identity is " + sameRoute(tr, str));
			}
		}

		return result;
	}

	public List<TradeRoute> computeRoutesSelectively(int maxdist, List<Planet> newPlanetInfo) {
		List<TradeRoute> currentPlanetRoutes = routeviewService.getTradeRoutes(newPlanetInfo);
		List<TradeRoute> result = new ArrayList<>();

		List<TradeRoute> preliminaries = strategy.findPossibleRoute(maxdist, newPlanetInfo);
		// update old calculations and calculate new, if routes have not been
		// found
		for (TradeRoute route : currentPlanetRoutes) {
			result.add(updateAll(route, new ArrayList<TradeRoute>(preliminaries)));
		}

		return result;
	}

	private TradeRoute updateAll(TradeRoute origin, List<TradeRoute> traderoutes) {
		TradeRoute result = origin;
		for (TradeRoute route : traderoutes) {
			result = update(origin, route);
		}

		return result;
	}

	private TradeRoute update(final TradeRoute tr, final TradeRoute str) {
		TradeRoute result = tr;
		if (sameRoute(tr, str)) {
			if (tr.getRelativeWinningsPerUnit() != str.getAbsoluteWinningsPerUnit()) {
				result = str;
				result.setUid(tr.getUid());
			}
		}
		return result;
	}

	private boolean sameRoute(TradeRoute tr, TradeRoute str) {
		if (tr.getCommodity().equals(str.getCommodity()) && tr.getExportPlanet().equals(str.getExportPlanet())
				&& tr.getImportPlanet().equals(str.getImportPlanet())) {
			return true;
		}
		return false;
	}

	public Set<String> getCoordinatesFromRoutes(List<TradeRoute> routes) {
		Set<String> ids = new HashSet<>();
		for (TradeRoute tr : routes) {
			ids.add(tr.getExportPlanet());
			ids.add(tr.getImportPlanet());
		}
		
		return ids;
	}

	public Map<String, String> getPlanetMap(Set<String> planetCoords) {
		Map<String, String> planetMap = new HashMap<>(planetCoords.size());

		System.out.println("About to create planet map...");
		List<Planet> planets = planetViewService.getPlanets();

		for (String coord : planetCoords) {
			for (Planet p : planets) {
				if (p.getCoordinates().toString().equals(coord)) {
					planetMap.put(p.getCoordinates().toString(), p.getName());
					break;
				}
			}
		}

		return planetMap;
	}

}
