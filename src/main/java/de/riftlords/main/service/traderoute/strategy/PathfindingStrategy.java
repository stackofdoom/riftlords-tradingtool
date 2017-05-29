/**
 * 
 */
package de.riftlords.main.service.traderoute.strategy;

import java.util.ArrayList;
import java.util.List;

import de.riftlords.main.persistence.entity.Planet;
import de.riftlords.main.persistence.entity.TradeRoute;


/**
 * Provides methods necessary to compute trade routes
 * @author pasc2de
 *
 */
public interface PathfindingStrategy {
	
	/**
	 * Calculates the trade routes according to the chosen strategy and
	 * the given maximum distance supplied via the parameters. The position parameter supplies the
	 * starting point from which the distance is measured.
	 * <p>
	 * Maxdistance is required to limit the scope of the different calculation strategies
	 * 
	 * @param maxdistance the maximum distance between coordinates to be able to traverse them
	 * @param position String a String representation of the starting points position
	 * @return
	 */
	public ArrayList<TradeRoute> calculateRoutes(int maxdistance, String position, List<Planet> planets);

}
