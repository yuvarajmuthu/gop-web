package com.gop.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.Party;

public interface PartyRepository extends GraphRepository<Party> {
	
	@Query("MATCH (party:Party) WHERE party.partyName={0} RETURN party")
	public List<Party> findPartyByName(String partyName);
		
}
