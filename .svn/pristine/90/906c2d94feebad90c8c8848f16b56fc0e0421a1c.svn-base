package org.merka.stubgen;

import java.security.InvalidParameterException;

import org.merka.stubgen.exception.MockGenException;

public class CharacterInstantiator extends GenericInstantiator implements IInstantiator
{
	@Override
	public <T> T newInstance(Class<?> T) throws MockGenException
	{
		try
		{
			if(!T.isAssignableFrom(Character.class))
			{
				throw new InvalidParameterException("The parameter must be of type " + Character.class.getName() + " or one of its subclasses");
			}
			@SuppressWarnings("unchecked")
			T casted = (T)new Character('a');
			return casted;
		}
		catch(Throwable t)
		{
			throw new MockGenException(t);
		}
	}
}
