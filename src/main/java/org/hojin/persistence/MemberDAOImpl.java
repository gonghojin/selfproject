package org.hojin.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hojin.domain.MemberVO;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO{
	private final String namespace = "org.hojin.mapper.MemberMapper";
	
	@Inject
	private SqlSession session;
	
	@Override
	public String getTime() {
		return session.selectOne(namespace + ".getTime"); 
	}

	@Override
	public void insertMember(MemberVO vo) {
		session.insert(namespace + ".insertMember", vo);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {
		return session.selectOne(namespace + ".selectMember", userid);
	}

	@Override
	public MemberVO readWithPw(String userid, String userpw) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("userpw", userpw);
		
		return session.selectOne(namespace + ".readWithPw", map);	}
	
}
