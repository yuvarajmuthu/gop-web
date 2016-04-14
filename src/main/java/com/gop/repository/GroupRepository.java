package com.gop.repository;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.Group;

public interface GroupRepository extends GraphRepository<Group> {

	@Query("MATCH (group:Group) WHERE group.groupName={0} RETURN group")
	public List<Group> findGroupByName(String groupName);
}
