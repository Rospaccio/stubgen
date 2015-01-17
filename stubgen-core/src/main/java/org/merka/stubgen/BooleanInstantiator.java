package org.merka.stubgen;

import java.security.InvalidParameterException;

import org.merka.stubgen.exception.MockGenException;

public class BooleanInstantiator extends GenericInstantiator implements IInstantiator
{
	@Override
	public <T> T newInstance(Class<?> T) throws MockGenException
	{
		try
		{
			if(!T.isAssignableFrom(Boolean.class))
			{
				throw new InvalidParameterException("Parameter must be of type " + Boolean.class.getName() + " or one of its subclasses");
			}
			
			@SuppressWarnings("unchecked")
			T casted = (T)new Boolean(true);
			return casted;
		}
		catch(Throwable t)
		{
			throw new MockGenException(t);
		}
	}
}
