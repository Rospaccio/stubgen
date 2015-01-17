package org.merka.stubgen.classloader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Loads a class from a byte array representing the content of a jar file
 * @author merka
 *
 */
public class JarInputStreamClassLoader extends ClassLoader
{
	private JarInputStream inputStream;
	protected RawClassList rawClasses;

	protected RawClassList getRawClasses()
	{
		return rawClasses;
	}

	protected void setRawClasses(RawClassList rawClasses)
	{
		this.rawClasses = rawClasses;
	}

	protected JarInputStream getInputStream()
	{
		return inputStream;
	}

	protected void setInputStream(JarInputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	public JarInputStreamClassLoader(JarInputStream jarStream) throws IOException
	{
		try
		{
			JarEntry entry = null;
			// JarInputStream stream = getInputStream();
			setRawClasses(new RawClassList());
			while ((entry = jarStream.getNextJarEntry()) != null)
			{
				String entryName = entry.getName();
				int lastIndexOf = entryName.lastIndexOf(".class");
				String classCandidateName = "";
				if(lastIndexOf != -1)
				{
					classCandidateName = entryName.replace('/', '.').substring(0, entryName.lastIndexOf(".class"));
				}
				if (!classCandidateName.isEmpty())
				{
					ByteArrayOutputStream classBytesStream = new ByteArrayOutputStream();
					byte[] read = new byte[256];
					int readNum = -1;
					while ((readNum = jarStream.read(read)) != -1)
					{
						classBytesStream.write(read, 0, readNum);
					}

					byte[] rawClassBytes = classBytesStream.toByteArray();
					RawClass rawClass = new RawClass(rawClassBytes, classCandidateName);
					getRawClasses().add(rawClass);
				}
			}
		}
		finally
		{
			if(jarStream != null)
			{
				jarStream.close();
			}
		}
	}

//	@Override
//	public Class<?> loadClass(String name) throws ClassNotFoundException
//	{
//		// TODO Auto-generated method stub
//		return findClass(name);
//	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		Class<?> clazz = null;

		// while( (entry = stream.getNextJarEntry()) != null )
		for (RawClass rawClass : getRawClasses())
		{
			String className = rawClass.getName();
			if (name.equals(className))
			{
				byte[] rawClassBytes = rawClass.getRawClassBytes();
				clazz = defineClass(name, rawClassBytes, 0, rawClassBytes.length);
				break;
			}
		}
		if (clazz == null)
		{
			throw new ClassNotFoundException("Class " + name + " not found.");
		}
		return clazz;
	}
	
	public List<String> getAvailableClasses()
	{
		return getRawClasses().getClassesNames();
	}
}
