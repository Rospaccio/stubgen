package org.merka.stubgen.web.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageFuture
{
	private static Logger logger = LoggerFactory.getLogger(MessageFuture.class);
	private String message = null;

	public String consumeMessageSynch()
	{
		String copy = null;
		synchronized (this)
		{
			while (message == null)
			{
				try
				{
					wait();
				}
				catch (InterruptedException ie)
				{
					logger.error("Interrupted exception in thread" + Thread.currentThread().getId() + "-" + Thread.currentThread().getName(), ie);
				}
			}
			copy = new String(message);
			message = null;
		}
		return copy;
	}

	public synchronized void setMessage(String message)
	{
		this.message = message;
	}
}
