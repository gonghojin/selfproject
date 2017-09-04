package org.hojin.persistence;

import org.hojin.domain.MemberVO;

public interface MemberDAO {
	
	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public MemberVO readMember(String userid) throws Exception;
	
	public MemberVO readWithPw(String userid, String userpw);
	
}
