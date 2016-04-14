package com.gop.domain;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Position {

	@GraphId
	Long nodeId;
	String positionName;
	String reponsibilities;
	
	public Long getNodeId() {
		return nodeId;
	}
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getReponsibilities() {
		return reponsibilities;
	}
	public void setReponsibilities(String reponsibilities) {
		this.reponsibilities = reponsibilities;
	}
	
	
}
