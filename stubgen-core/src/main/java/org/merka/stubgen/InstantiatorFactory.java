package org.merka.stubgen;

import java.math.BigDecimal;

public class InstantiatorFactory
{
	public static IInstantiator getInstantiator(Class<?> theClass)
	{
		if (theClass.equals(Integer.class) 
				|| theClass.equals(Double.class)
				|| theClass.equals(Float.class)
				|| theClass.equals(BigDecimal.class)
				|| theClass.equals(Long.class)
				|| theClass.equals(Byte.class))
		{
			return new NumberInstantiator();
		}
		else if(theClass.equals(Boolean.class))
		{
			return new BooleanInstantiator();
		}
		else if(theClass.equals(Character.class))
		{
			return new CharacterInstantiator();
		}
		else
		{
			return new GenericInstantiator();
		}
	}
	
//	private static boolean isList(Class<?> theClass)
//	{
//		return MockGenUtils.isList(theClass);
//	}
}
