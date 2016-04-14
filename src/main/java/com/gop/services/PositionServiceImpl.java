package com.gop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gop.domain.Position;
import com.gop.repository.PositionRepository;
import com.gop.util.GopUtils;

@Service
public class PositionServiceImpl {

	@Autowired
	PositionRepository positionRepository;
	public String addPosition(String jsonPosition) throws Exception
	{
		String jsonPositionReturn = null;
		Position position = null;
		try
		{
			position = GopUtils.generateClassFromJson(jsonPosition, Position.class);
			positionRepository.save(position);
			jsonPositionReturn = getPosition(position.getPositionName());
		}
		catch(Exception e)
		{
			System.out.println("Error in getPosition:"+e);
			throw new Exception();
		}
		return jsonPositionReturn;
	}
	
	public String getPosition(String positionName) throws Exception
	{
		String jsonPositionReturn = null;
		List<Position> positionList = null;
		try
		{
			positionList = positionRepository.findPositionByName(positionName);
			jsonPositionReturn = GopUtils.generateJsonFromClass(positionList.get(0));
		}
		catch(Exception e)
		{
			System.out.println("Error in getPosition:"+e.getMessage());
			throw new Exception();
		}
		return jsonPositionReturn;
	}
}
