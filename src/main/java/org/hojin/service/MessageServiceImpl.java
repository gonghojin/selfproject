package org.hojin.service;

import javax.inject.Inject;

import org.hojin.domain.MessageVO;
import org.hojin.persistence.MessageDAO;
import org.hojin.persistence.PointDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService{
	@Inject
	private MessageDAO messageDAO;
	
	@Inject
	private PointDAO pointDAO;
	
	@Transactional
	@Override
	public void insertMessage(MessageVO vo) throws Exception {
		messageDAO.insert(vo);
		pointDAO.updatePoint(vo.getSender(), 10);
		
	}

	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		messageDAO.update(mno);
		pointDAO.updatePoint(uid, 5);
		
		return messageDAO.read(mno);
	}
}
