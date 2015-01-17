package org.merka.stubgen;

import java.util.ArrayList;

import org.merka.stubgen.exception.MockGenException;

public class ListInstantiator extends GenericInstantiator implements IInstantiator
{
	@SuppressWarnings("unchecked")
	@Override
	public <T> T newInstance(Class<?> T) throws MockGenException
	{
		ArrayList<T> list = new ArrayList<T>();
		
		for(int i = 0; i < MockObjectGenerator.DEFAULT_ARRAY_LENGTH; i++)
		{
			MockObjectGenerator localGenerator = new MockObjectGenerator();
			T element = localGenerator.generate(T, 1);
			list.add(element);
		}
		
		return (T)list;
	}
}
