package com.gop.domain;

import java.util.Date;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Party {
	@GraphId
    Long nodeId;
	String partyName;
	//@DateString("yy-MM-dd")
	Date establishedDate;
	String registeredAddress;
	String ideology;
	
	
	public Long getNodeId() {
		return nodeId;
	}
	
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public Date getEstablishedDate() {
		return establishedDate;
	}
	public void setEstablishedDate(Date establishedDate) {
		this.establishedDate = establishedDate;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public String getIdeology() {
		return ideology;
	}
	public void setIdeology(String ideology) {
		this.ideology = ideology;
	}
	
	
}
