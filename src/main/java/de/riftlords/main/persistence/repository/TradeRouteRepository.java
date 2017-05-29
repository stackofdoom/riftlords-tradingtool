package de.riftlords.main.persistence.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import de.riftlords.main.persistence.entity.TradeRoute;

public interface TradeRouteRepository extends JpaRepository<TradeRoute, Long>{

	@Override
	List<TradeRoute> findAll();
	
	public Set<TradeRoute> save(Set<TradeRoute> routes);

	public List<TradeRoute> findByExportPlanetAndImportPlanet(String exp, String imp);
	
	public List<TradeRoute> findByExportPlanet(String exp);
	
	public List<TradeRoute> findByImportPlanet(String imp);
	
	public List<TradeRoute> findByExportPlanetInOrImportPlanetIn(List<String> planets, List<String> planets2);

	public List<TradeRoute> findByTotaldistanceLessThanEqual(int motors);
}
