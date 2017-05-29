package de.riftlords.main.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.riftlords.main.persistence.entity.WayPoint;

public interface WayPointRepository extends JpaRepository<WayPoint, Long>{

}
