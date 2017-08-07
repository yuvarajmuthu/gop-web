package com.gop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gop.domain.User;
import com.gop.repository.UserRepository;
import com.gop.util.GopUtils;

@Service
public class UserServiceImpl {

	@Autowired
	UserRepository userRepository;
	
	public String getUser(String userName) throws Exception{
		String jsonUser = null;
		User user = null;
		try
		{
			user = userRepository.findByUserName(userName);
			jsonUser = GopUtils.generateJsonFromClass(user);
			System.out.println("Retrieved the user using userName " + userName + ": " + jsonUser);			
		}
		catch(Exception e)
		{
			System.out.println("Error in getUser:"+e.getMessage());
			throw new Exception();
		}
		return jsonUser;
	}

	public String addUser(String jsonUser) throws Exception {
		String jsonUserReturn = null;
		User user = null;
		try
		{
			user = GopUtils.generateClassFromJson(jsonUser, User.class);
			userRepository.save(user);
			jsonUserReturn = getUser(user.getUserName());
			System.out.println("New user got added :" + jsonUserReturn);			
		}
		catch(Exception e)
		{
			System.out.println("Error in addUser:"+e.getMessage());
			throw new Exception();
		}
		return jsonUserReturn;
	}

}
