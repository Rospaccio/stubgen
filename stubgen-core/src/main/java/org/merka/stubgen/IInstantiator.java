package org.merka.stubgen;

import org.merka.stubgen.exception.MockGenException;

public interface IInstantiator
{
	/**
	 * Creates an instance of type <code>T</code>
	 * @param T Class of which the method creates an instance
	 * @return an instance of Class <code>T</code>
	 */
	public <T> T newInstance(Class<?> T) throws MockGenException;
}
