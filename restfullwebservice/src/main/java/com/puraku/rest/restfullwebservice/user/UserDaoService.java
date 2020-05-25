package com.puraku.rest.restfullwebservice.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();
	private static Integer count= 3;
	static {
		users.add(new User(1,"Puneet",new Date()));
		users.add(new User(2,"Pragati",new Date()));
		users.add(new User(3,"Maya",new Date()));
	}
	
	
	public List<User> findAllUser()
	{
		return users;
		
	}


	public User save(User user) {
		
		if(user.getId()==0)
			user.setId(++count);
		
		users.add(user);
		return user;
	}


	public User findUser(int id) {
		
		for(User u : users)
		{
			if(u.getId()==id)
				return u;
		}
		return null;
	}


	public User deleteUser(int id) {
		
		Iterator<User> it = users.iterator();
		while(it.hasNext())
		{
			User u = it.next();
			if(u.getId()==id)
			{
				it.remove();
				return u;
			}
				
		}
		return null;
	}
}
