package org.hojin.service;

import java.util.Date;

import javax.inject.Inject;

import org.hojin.domain.UserVO;
import org.hojin.dto.LoginDTO;
import org.hojin.persistence.UserDAO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO userDAO;
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return userDAO.login(dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) throws Exception {
		userDAO.keepLogin(uid, sessionId, next);
		
	}

	@Override
	public UserVO checkLoginBefore(String value) {
		return userDAO.checkUserWithSessionKey(value);
	}

}
