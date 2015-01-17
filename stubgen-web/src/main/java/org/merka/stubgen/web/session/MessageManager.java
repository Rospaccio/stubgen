package org.merka.stubgen.web.session;

import java.util.ArrayList;
import java.util.List;

import org.merka.stubgen.web.entity.MessageFuture;

public class MessageManager
{
	protected List<MessageFuture> scheduledSynchMessges = new ArrayList<MessageFuture>();

	public List<MessageFuture> getScheduledSynchMessges()
	{
		return scheduledSynchMessges;
	}

	public void setScheduledSynchMessges(List<MessageFuture> scheduledSynchMessges)
	{
		this.scheduledSynchMessges = scheduledSynchMessges;
	}

	public synchronized void scheduleSynchMessage(MessageFuture synchMessage)
	{
		getScheduledSynchMessges().add(synchMessage);
	}
	
	public void notifyAllControllers()
	{
		for(MessageFuture synchMessage : getScheduledSynchMessges())
		{
			synchronized (synchMessage)
			{
				synchMessage.setMessage("OOOOK!");
				synchMessage.notifyAll();
			}
		}
		getScheduledSynchMessges().clear();
	}
}
