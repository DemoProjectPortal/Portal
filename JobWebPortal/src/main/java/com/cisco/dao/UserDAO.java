package com.cisco.dao;

import java.util.List;
import com.cisco.models.User;

public interface UserDAO {
	public long addUser(User p);

	public void updateUser(User p);

	public List<User> listUser();

	public User getUserById(int id);

	public void removeUser(int id);

}

