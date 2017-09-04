package org.hojin.persistence;

import org.hojin.domain.MessageVO;

public interface MessageDAO {
	public void insert(MessageVO vo) throws Exception;
	
	public MessageVO read(Integer mno) throws Exception;
	
	public void update(Integer mno) throws Exception;
	
	
}
