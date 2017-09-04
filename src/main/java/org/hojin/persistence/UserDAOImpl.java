package org.hojin.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hojin.domain.UserVO;
import org.hojin.dto.LoginDTO;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static final String namespace = "org.hojin.mappers.UserMapper";
	
	@Override
	public UserVO login(LoginDTO dto) throws Exception {
		return session.selectOne(namespace + ".login", dto);
	}

	@Override
	public void keepLogin(String uid, String sessionId, Date next) {
		Map<String, Object> map = new HashMap<>();
		map.put("uid", uid);
		map.put("sessionId", sessionId);
		map.put("next", next);
		
		session.update(namespace + ".keepLogin", map);
	}

	@Override
	public UserVO checkUserWithSessionKey(String value) {
		return session.selectOne(namespace + ".checkUserWithSessionKey", value);
	}

}
