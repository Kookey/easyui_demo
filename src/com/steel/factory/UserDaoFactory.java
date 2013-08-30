package com.steel.factory;

import com.steel.dao.UserDao;
import com.steel.daoImpl.UserDaoImpl;

public class UserDaoFactory {

	public static UserDao getUserDaoIntance(){
		return new UserDaoImpl();
	}
}
