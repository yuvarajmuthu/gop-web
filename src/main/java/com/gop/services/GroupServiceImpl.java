package com.gop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gop.domain.Group;
import com.gop.repository.GroupRepository;
import com.gop.util.GopUtils;

@Service
public class GroupServiceImpl {
	
	@Autowired
	GroupRepository groupRepository;
	
	public String addGroup(String jsonGroup) throws Exception
	{
		String jsonGroupReturn = null;
		Group group = null;
		try
		{
			group = GopUtils.generateClassFromJson(jsonGroup, Group.class);
			groupRepository.save(group);
			jsonGroupReturn = findGroupByName(group.getGroupName());
		}
		catch(Exception e)
		{
			System.out.println("Error in addGroup:"+e.getMessage());
			throw new Exception();
		}
		return jsonGroupReturn;
	}
	
	public String findGroupByName(String groupName) throws Exception
	{
		List<Group> groupList = null;
		String jsonGroupReturn = null;
		try
		{
			groupList = groupRepository.findGroupByName(groupName);
			jsonGroupReturn = GopUtils.generateJsonFromClass(groupList.get(0));
		}
		catch(Exception e)
		{
			System.out.println("Error in findGroupByName:"+e.getMessage());
			throw new Exception();
		}
		return jsonGroupReturn;
	}
}
