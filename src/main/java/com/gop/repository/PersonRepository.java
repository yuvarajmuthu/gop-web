package com.gop.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.Person;

public interface PersonRepository extends GraphRepository<Person> {
	
	@Query("MATCH (person:Person) WHERE person.firstName={0} RETURN person")
	public List<Person> findPersonByName(String firstName);
}
