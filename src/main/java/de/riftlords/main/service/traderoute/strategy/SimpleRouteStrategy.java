/**
 * 
 */
package de.riftlords.main.service.traderoute.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import de.riftlords.main.persistence.entity.Coordinate;
import de.riftlords.main.persistence.entity.MarketEntry;
import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;

/**
 * @author pasc2de
 *
 */
@Component
public class SimpleRouteStrategy implements RouteStrategy {
	
	public SimpleRouteStrategy(){
		
	}
	
	private static String NAME="SimpleRouteStrategy";
	

	/* (non-Javadoc)
	 * @see com.riftlords.service.traderoute.strategy.FindRouteStrategy#findPossibleRoute(int, java.util.List)
	 */
	@Override
	public List<TradeRoute> findPossibleRoute(int maxdist, List<Planet> planets) {
		List<TradeRoute> routes = new ArrayList<>();
		
		//This method lies within complexity class O(N²)
		
		for (Iterator<Planet> oiterator = planets.iterator(); oiterator.hasNext();) {
			Planet origin = oiterator.next();
			
			routes.addAll(compareOriginPlanetToTargetPlanets(planets, origin));
			
		}
		System.out.println("complete list of routes: " + Arrays.toString(routes.toArray()));
		return routes;
	}

	private List<TradeRoute> compareOriginPlanetToTargetPlanets(List<Planet> planets, Planet origin) {
		List<TradeRoute> result = new ArrayList<>();
		
		for (Iterator<Planet> titerator = planets.iterator(); titerator.hasNext();) {
			Planet target = titerator.next();
			
			int dist = (int) Math.pow(origin.getCoordinates().getXcoordinate() - target.getCoordinates().getXcoordinate(),2) + 
					(int) Math.pow(origin.getCoordinates().getYcoordinate() - target.getCoordinates().getYcoordinate(),2);
			List<MarketEntry> omarkets = origin.getPrices();
			List<MarketEntry> tmarkets = target.getPrices();
			
			Coordinate targetCoord = target.getCoordinates();
			Coordinate originCoord = origin.getCoordinates();
			result.addAll(compareOriginMarketstoTargetMarkets(omarkets, tmarkets, originCoord.toString(), targetCoord.toString(), dist));
			
		}
		return result;
	}

	private List<TradeRoute> compareOriginMarketstoTargetMarkets(List<MarketEntry> omarkets, List<MarketEntry> tmarkets, String originid, String targetid, int dist) {
		
		List<TradeRoute> result = new ArrayList<>();
		
		for (Iterator<MarketEntry> oMarketIterator = omarkets.iterator(); oMarketIterator.hasNext();) {
			MarketEntry oMarketEntry = oMarketIterator.next();
			
			result.addAll(compareOriginToTargetMarkets(tmarkets, oMarketEntry, originid, targetid, dist));	
		}
		
		return result;
	}

	private List<TradeRoute> compareOriginToTargetMarkets(List<MarketEntry> tmarkets, MarketEntry oMarketEntry, String originid, String targetid, int dist) {
		
		List<TradeRoute> result = new ArrayList<>();
		
		for (Iterator<MarketEntry> tMarketIterator = tmarkets.iterator(); tMarketIterator.hasNext();) {
			MarketEntry tMarketEntry = (MarketEntry) tMarketIterator.next();
			
			//check if the markets are selling and buying
			if(oMarketEntry.isSelling() && tMarketEntry.isBuying()){
				//check if the wares have the same type
				if(oMarketEntry.getName().equals(tMarketEntry.getName())){
					TradeRoute r = new TradeRoute();
					
					r.setCommodity(oMarketEntry.getName());
					r.setExportPrice(oMarketEntry.getPrice());
					r.setImportPrice(tMarketEntry.getPrice());
					r.setExportPlanet(originid);
					r.setImportPlanet(targetid);
					r.setTotalDistancE(dist);
					
					if(r.getAbsoluteWinningsPerUnit() > 0){
						result.add(r);
						System.out.println("Adding...: " + r.toString());
					}else{
						System.out.println("Discarding... : " + r.toString());
					}
					
				}
			}
			
		}
		return result;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
