package com.gop.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.Position;

public interface PositionRepository extends GraphRepository<Position>{

	@Query("MATCH (position:Position) WHERE position.positionName={0} RETURN position")
	public List<Position> findPositionByName(String positionName);
}
