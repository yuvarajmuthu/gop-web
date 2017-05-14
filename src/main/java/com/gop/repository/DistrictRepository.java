package com.gop.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.gop.domain.District;

public interface DistrictRepository extends GraphRepository<District> {
	
	@Query("MATCH (district:District) WHERE district.districtName={0} RETURN district")
	public List<District> findDistrictByName(String districtName);
	
	public Page<District> findByDistrictNameLike(String districtName, Pageable page);
	public Page<District> findAll(Pageable page);

}
