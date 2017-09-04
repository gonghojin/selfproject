package org.hojin.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hojin.domain.MessageVO;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "org.hojin.mappers.MessageMapper";
	
	
	@Override
	public void insert(MessageVO vo) throws Exception {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public MessageVO read(Integer mno) throws Exception {
		return session.selectOne(namespace + ".read", mno);
		
	}

	@Override
	public void update(Integer mno) throws Exception {
		session.update(namespace + ".update", mno);
	}

}
