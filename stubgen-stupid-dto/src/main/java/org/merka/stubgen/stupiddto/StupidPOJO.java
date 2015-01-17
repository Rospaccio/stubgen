package org.merka.stubgen.stupiddto;

import java.io.Serializable;

public class StupidPOJO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7556381275509303108L;
	
	private int stupidInt;
	private String stupidString = "This is a stupid String";
	public int getStupidInt()
	{
		return stupidInt;
	}
	public void setStupidInt(int stupidInt)
	{
		this.stupidInt = stupidInt;
	}
	public String getStupidString()
	{
		return stupidString;
	}
	public void setStupidString(String stupidString)
	{
		this.stupidString = stupidString;
	}
	
	public StupidPOJO()
	{
		
	}
	@Override
	public String toString() {
		return "StupidPOJO [stupidInt=" + stupidInt + ", stupidString="
				+ stupidString + "]";
	}
	
	
}
