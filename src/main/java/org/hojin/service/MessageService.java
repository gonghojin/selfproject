package org.hojin.service;

import org.hojin.domain.MessageVO;


public interface MessageService {
	public void insertMessage(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(String uid, Integer mno) throws Exception;
}
