package org.merka.stubgen;

import org.merka.stubgen.exception.MockGenException;
import org.merka.stubgen.exception.NotBlockingException;

public class GenericInstantiator implements IInstantiator
{

	@SuppressWarnings(value = { "unchecked" })
	public <T> T newInstance(Class<?> T) throws MockGenException
	{
		try
		{
			T instance = (T) T.newInstance();
			return instance;
		}
		catch(InstantiationException ie)
		{
			throw new NotBlockingException(ie);
		}
		catch(Throwable t)
		{
			System.err.println("\n\n\nerror for Class " + T.getName() + "\n\n\n");
			throw new MockGenException(t);
		}
	}

}