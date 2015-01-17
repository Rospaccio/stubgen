package org.merka.stubgen;

import java.lang.reflect.Constructor;

import org.merka.stubgen.exception.MockGenException;

public class NumberInstantiator implements IInstantiator
{

	@SuppressWarnings(value = { "unchecked" })
	public <T> T newInstance(Class<?> T) throws MockGenException
	{
		try
		{
//			if (!T.isAssignableFrom(Number.class))
//			{
//				throw new InvalidParameterException("Parameter must be of type " + Number.class.getName() + " or one of its subclasses");
//			}
			T castedInstance = null;
			Constructor<?> constructor = T.getConstructor(String.class);
			castedInstance = (T) constructor.newInstance(pickRandomFromDomain());
			return castedInstance;
		}
		catch (Throwable t)
		{
			throw new MockGenException(t);
		}
	}

	protected String pickRandomFromDomain()
	{
		return "0";
	}
}
