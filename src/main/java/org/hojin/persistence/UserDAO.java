package org.hojin.persistence;

import java.util.Date;

import org.hojin.domain.UserVO;
import org.hojin.dto.LoginDTO;

public interface UserDAO {
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);
	
	
}
