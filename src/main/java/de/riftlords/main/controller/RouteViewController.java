package de.riftlords.main.controller;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import de.riftlords.main.persistence.entity.Coordinate;
import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;
import de.riftlords.main.service.ComputeRouteService;
import de.riftlords.main.service.PlanetViewService;
import de.riftlords.main.service.RouteViewService;




@Controller
@RequestMapping("/routes")
public class RouteViewController {
	
	@Autowired
	private RouteViewService routeViewService;
	@Autowired
	private ComputeRouteService computeRouteService;
	
	@Autowired
	private PlanetViewService planetViewService;
	
	
//	@GetMapping("/list-routes")
//	public String viewTradeRoutes(Model theModel){
//		
//		List<TradeRoute> routes =routeViewService.getTradeRoutes();
//		theModel.addAttribute("routes", routes);
//		
//		Set<String> planetCoords = computeRouteService.getCoordinatesFromRoutes(routes);
//		System.out.println("Getting coordinates from route service: "+ planetCoords.toString());
//		planetCoords = computeRouteService.getCoordinatesFromRoutes(routes);
//		
//		Map<String, String> planetMap = computeRouteService.getPlanetMap(planetCoords);
//		System.out.println(planetMap.toString());
//		
//		theModel.addAttribute("planets", planetMap);
//		
//		return "list-routes";
//		
//	}
	
	@GetMapping("/list-routes")
	public String viewTradeRoutes(@NotNull Filters filter, Model theModel){
		List<TradeRoute> routes = new ArrayList<>();
		if( filter == null){
			theModel.addAttribute("filter", new Filters());
			routes = routeViewService.getTradeRoutes();
		}else{
			System.out.println("Returning filtered results... " + filter.toString());
			routes = routeViewService.getTradeRoutesByDistance(filter.isMotorFilter(), filter.getNum_motors());
		}
		
		theModel.addAttribute("routes", routes);
		
		Set<String> planetCoords = computeRouteService.getCoordinatesFromRoutes(routes);
		System.out.println("Getting coordinates from route service: "+ planetCoords.toString());
		planetCoords = computeRouteService.getCoordinatesFromRoutes(routes);
		
		Map<String, String> planetMap = computeRouteService.getPlanetMap(planetCoords);
		System.out.println(planetMap.toString());

		
		theModel.addAttribute("planets", planetMap);
		if(!theModel.containsAttribute("filter")){
			theModel.addAttribute("filter", new Filters());
			System.out.println(theModel.asMap().get("filter").toString());
		}else{
			theModel.addAttribute("filter", filter);
		}
		
		return "list-routes";
	}
	
	
	
	
	@GetMapping("/compute-routes")
	public String computeRoutes(Model theModel){
		System.out.println("Compute-routes was accessed");
		List<Planet> planets = planetViewService.getPlanets(); 
		System.out.println("Planets received...: " + planets);
		
		List<TradeRoute> routes = computeRouteService.computeRoutes(20, planets);
		
		routeViewService.storeTradeRoutes(routes);
		
		return "redirect:/routes/list-routes";
	}

}
