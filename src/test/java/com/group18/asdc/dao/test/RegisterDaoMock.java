package com.group18.asdc.dao.test;

import com.group18.asdc.dao.RegisterDao;
import com.group18.asdc.entities.UserRegistartionDetails;

public class RegisterDaoMock implements RegisterDao {

	@Override
	public boolean registeruser(UserRegistartionDetails bean) {
		bean.getBannerid();
		return Boolean.TRUE;
	}

	@Override
	public boolean checkUserWithEmail(String email) {

		return Boolean.TRUE;
	}

	@Override
	public boolean checkUserWithBannerId(String bannerId) {

		return Boolean.TRUE;
	}

}
