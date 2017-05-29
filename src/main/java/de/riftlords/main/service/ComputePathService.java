/**
 * 
 */
package de.riftlords.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.riftlords.main.persistence.repository.WayPointRepository;

/**
 * @author Patrick Schmolke (patrick.schmolke@acando.de)
 *
 */
@Service
public class ComputePathService {

	@Autowired
	private WayPointRepository wprepo;
	
}
