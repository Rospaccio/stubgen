package org.merka.stubgen.exception;

public class MockGenException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5047088297110909769L;

	public MockGenException(Throwable cause)
	{
		super(cause);
	}
	
	public MockGenException(String arg)
	{
		super(arg);
	}
	
	public MockGenException(String arg, Throwable cause)
	{
		super(arg, cause);
	}
}
