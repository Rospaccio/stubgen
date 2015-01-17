package org.merka.stubgen.web.controller;

import org.merka.stubgen.web.session.MessageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MulticastCommandController
{
	@Autowired
	private MessageManager messageManager;

	public MessageManager getMessageManager()
	{
		return messageManager;
	}

	public void setMessageManager(MessageManager messageManager)
	{
		this.messageManager = messageManager;
	}
	
	public MulticastCommandController()
	{
		
	}
	
	@RequestMapping("/send-all")
	public String sendAll()
	{
		getMessageManager().notifyAllControllers();
		return LongPollingController.LONG_POLLING_VIEW;
	}
}
