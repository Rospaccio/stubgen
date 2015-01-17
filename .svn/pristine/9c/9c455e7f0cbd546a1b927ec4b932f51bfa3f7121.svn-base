package org.merka.stubgen.web.entity;

import org.merka.stubgen.MockObjectGenerator;
import org.merka.stubgen.classloader.JarInputStreamClassLoader;

public class StubClass
{
	/**
	 * Fully qualified name of the class
	 */
	private String name;
	
	/**
	 * Tells if the class is loadable without errors by the owning {@link JarInputStreamClassLoader}.
	 */
	private boolean loadable;
	
	/**
	 * Tells if stub instance of the class can be generated with a {@link MockObjectGenerator}.
	 */
	private boolean instantiable;

	/**
	 * If the represented class is loadable, contains a reference to the class.
	 */
	private Class<?> loadedClass;
	
	public Class<?> getLoadedClass()
	{
		return loadedClass;
	}

	public void setLoadedClass(Class<?> loadedClass)
	{
		this.loadedClass = loadedClass;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isLoadable()
	{
		return loadable;
	}

	public void setLoadable(boolean loadable)
	{
		this.loadable = loadable;
	}

	public boolean isInstantiable()
	{
		return instantiable;
	}

	public void setInstantiable(boolean instantiable)
	{
		this.instantiable = instantiable;
	}
	
	public StubClass(String name, boolean loadable, boolean instantiable, Class<?> clazz)
	{
		super();
		this.name = name;
		this.loadable = loadable;
		this.instantiable = instantiable;
		this.loadedClass = clazz;
	}
}
