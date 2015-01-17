package org.merka.stubgen;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.merka.stubgen.exception.MockGenException;
import org.merka.stubgen.exception.NotBlockingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockObjectGenerator
{
	public static final int DEFAULT_NESTING_VALUE = 10;
	public static final int DEFAULT_ARRAY_LENGTH = 3;
	public static final String DEFAULT_DTO_PACKAGE = "org.merka.stubgen.test";
	
	private static transient Logger logger = LoggerFactory.getLogger(MockObjectGenerator.class);

	protected Package dtoPackage;

	protected Package getDtoPackage()
	{
		return dtoPackage;
	}

	protected void setDtoPackage(Package dtoPackage)
	{
		this.dtoPackage = dtoPackage;
	}

	public MockObjectGenerator()
	{
		Package dtoPack = Package.getPackage(DEFAULT_DTO_PACKAGE);
		setDtoPackage(dtoPack);
	}

	public Object generate(Class<?> theClass) throws MockGenException
	{
		return generate(theClass, DEFAULT_NESTING_VALUE);
	}
	
	public <GeneratedType> List<GeneratedType> generateList(Class<?> GeneratedType, int length) throws MockGenException
	{
		ArrayList<GeneratedType> list = new ArrayList<GeneratedType>();
		for(int i = 0; i < length; i++)
		{
			GeneratedType stub = (GeneratedType) generate(GeneratedType);
			list.add(stub);
		}
		
		return list;
	}

	protected <GeneratedType> GeneratedType generateInstance(Class<?> GeneratedType, int nestingLevel) throws MockGenException
	{
		try
		{
			Object mock = null;
			// switch through the possible cases of T
			if(GeneratedType.isPrimitive())
			{
				if(GeneratedType.equals(boolean.class))
				{
					mock = true;
				}
				else if(GeneratedType.equals(int.class) )
				{
					mock = (int) 0;
				}
				else if(GeneratedType.equals(long.class))
				{
					mock = (long) 0L;
				}
				else if (GeneratedType.equals(short.class))
				{
					mock = (short) 0;
				}
				else if (GeneratedType.equals(byte.class)) 
				{
					mock = (byte) 0;
				}
				else if (GeneratedType.equals(char.class))
				{
					mock = (char) 0;
				}
				else if (GeneratedType.equals(double.class))
				{
					mock = (double) 0;
				}
				else if (GeneratedType.equals(float.class))
				{
					mock = (float) 0;
				}
				return (GeneratedType)mock;
			}
			
			if(nestingLevel == 0)
			{
				return (GeneratedType)mock;
			}
			
			else if(GeneratedType.isEnum())
			{
				mock = GeneratedType.getEnumConstants()[0];
			}
			else if(GeneratedType.isArray())
			{
				Object[] array = (Object[])Array.newInstance(GeneratedType.getComponentType(), DEFAULT_ARRAY_LENGTH);
				for(int i = 0; i < DEFAULT_ARRAY_LENGTH; i++)
				{
					Object element = generate(GeneratedType.getComponentType(), nestingLevel - 1);
//					array[i] = element; // this works too
					Array.set(array, i, element);
				}
				mock = array;
			}
			else if (MockGenUtils.isList(GeneratedType))
			{
				
				throw new MockGenException("Impossible to get an instance of a List type. Please use the method generateList instead.");
				
//				ArrayList<Object> list = new ArrayList<Object>();
//				for(int i = 0; i < DEFAULT_ARRAY_LENGTH; i++)
//				{
//					Object element = generate( (Class<?>)(((ParameterizedType)GeneratedType.getGenericInterfaces()[0]).getActualTypeArguments()[0]) );
//					list.add(element);
//				}
				
//				Type genericInterface =  GeneratedType.getGenericInterfaces()[0];
//				if(genericInterface instanceof ParameterizedType)
//				{
//					
//					ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
//					Class<?> componentType = (Class<?>) parameterizedType.getActualTypeArguments()[0]; // (Class<?>)parameterizedType.getActualTypeArguments()[0];
//					
//					Object element = generate(componentType, nestingLevel - 1);
//					
//					list.add(element);
//					
//					mock = list;
//					
//				}
			}
			else 
			{
				IInstantiator instantiator = InstantiatorFactory.getInstantiator(GeneratedType);
				try
				{
					mock = instantiator.newInstance(GeneratedType);
				}
				catch(NotBlockingException e)
				{
					String message = "Exception of type: " + e.getClass().getName() + " in generateInstance.";
					logger.warn("Exception of type: " + e.getClass().getName() + " in generateInstance.");
					if (logger.isDebugEnabled())
					{
						logger.debug("Full exception: ", e);
					}
				}
			}
			
			return (GeneratedType)mock;
		}
		catch(Exception e)
		{
			throw new MockGenException(e);
		}		
	}

	@SuppressWarnings("unchecked")
	protected void setFields(Object mock, int nestingLevel) throws MockGenException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (mock == null)
		{
			return;
		}
		Method[] methodArray = mock.getClass().getDeclaredMethods();
		for (Method method : methodArray)
		{
			int modifiers = method.getModifiers();
			
			if (MockGenUtils.isSetter(method) && Modifier.isPublic(modifiers))
			{
				Class<?> paramType = method.getParameterTypes()[0];
				try
				{
					Object param = null;
					if (MockGenUtils.isList(paramType))
					{									
						ArrayList list = new ArrayList();
						for(int i = 0; i < DEFAULT_ARRAY_LENGTH; i++)
						{
							Object element = generate( (Class<?>)(((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0]) );
							list.add(element);
						}
						param = list;
					}
					else if (isMockable(paramType))
					{
						param = generate(paramType, nestingLevel - 1);
					}
					else
					{
						param = generateInstance(paramType, nestingLevel - 1);
					}
					try
					{
						method.invoke(mock, param);
					}
					catch (Exception e)
					{
						System.err.println("\n\n\n Class: " + paramType.getName() + "; method: " + method.getName());
						throw new MockGenException(e);
					}
				}
				catch (NotBlockingException e)
				{
					// TODO how to handle this exception?
				}
			}
		}
	}

	private boolean isMockable(Class<?> generatedType)
	{
		if (generatedType != null && generatedType.getPackage() != null && generatedType.getPackage().getName() != null && getDtoPackage() != null)
		{
			return generatedType.getPackage().getName().equals(getDtoPackage().getName());
		}
		return false;
	}

	public <T> T generate(Class<?> T, int nestingLevel) throws MockGenException
	{
		try
		{
			T mock = generateInstance(T, nestingLevel);
			setFields(mock, nestingLevel);

			return mock;
		}
		catch (Exception t)
		{
			throw new MockGenException(t);
		}
	}
	
	public <T> T generate(T prototype) throws MockGenException
	{
		try
		{
			Class<?> theClass = prototype.getClass();

			T mock = (T) generate(theClass);

			Method[] protoMethodArray = theClass.getDeclaredMethods();
			for (Method prototypeMethod : protoMethodArray)
			{
				if (MockGenUtils.isGetter(prototypeMethod))
				{
					// find the correspondent setter method
					String namePart;
					if (prototypeMethod.getName().startsWith("is"))
					{
						namePart = prototypeMethod.getName().substring(2);
					}
					else
					{
						namePart = prototypeMethod.getName().substring(3);
					}
					String setterName = "set" + namePart;
					Class<?> returnType = prototypeMethod.getReturnType();

					try
					{
						Method setterMethod = theClass.getMethod(setterName, returnType);
						Object param = prototypeMethod.invoke(prototype, (Object[]) null);

						if (param != null)
						{
							setterMethod.invoke(mock, prototypeMethod.invoke(prototype, (Object[]) null));
						}
					}
					catch (NoSuchMethodError ne)
					{

					}
				}
			}

			return mock;
		}
		catch (Exception t)
		{
			throw new MockGenException(t);
		}
	}

//	protected Object loadJSON() throws MockGenException
//	{
//		try
//		{
//			ObjectMapper mapper = new ObjectMapper();
//			TestObject prototype = mapper.readValue("json/mockgen-templates.json", TestObject.class);
//			return generate(prototype);
//		}
//		catch (Throwable t)
//		{
//			throw new MockGenException(t);
//		}
//	}

}
