package org.merka.stubgen.web.controller;

import org.merka.stubgen.web.entity.MessageFuture;
import org.merka.stubgen.web.session.MessageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/multicast")
public class LongPollingController
{
	private static Logger logger = LoggerFactory.getLogger(LongPollingController.class);
	public static final String LONG_POLLING_VIEW = "longPollingView";
	private MessageFuture synchMessage;
	
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

	public LongPollingController()
	{
		synchMessage = new MessageFuture();
	}
	
	/**
	 * Default request mapping: it is meant to be the entry point of the page.
	 * @return The name of the long polling test view.
	 */
	@RequestMapping
	public String getPage()
	{
		return LONG_POLLING_VIEW;
	}
	
	@RequestMapping("/poll")
	public @ResponseBody String poll()
	{
		getMessageManager().scheduleSynchMessage(synchMessage);
		String message = synchMessage.consumeMessageSynch();
		return message;
	}
	
//	@RequestMapping("/poll")
//	public @ResponseBody String poll()
//	{
//		logger.info("Received a long poll request");
//		getMessageManager().scheduleSynchString(synchObject);
//		
//		synchronized (synchObject)
//		{
//			while(synchObject.equals(""))
//			{
//				try
//				{
//					synchObject.wait();
//				}
//				catch(InterruptedException ie)
//				{
//					logger.warn("Wait interrupted", ie);
//				}
//			}
//		}
//		String returning = new String(synchObject);
//		synchObject = "";
//		
//		return returning;
//	}
}
