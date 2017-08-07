package com.gop.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.Constituency;

public interface ConstituencyRepository extends GraphRepository<Constituency> {

	@Query("MATCH (constituency:Constituency) WHERE constituency.constituencyName={0} RETURN constituency")
	public List<Constituency> findConstituencyByName(String constituencyName);
}
