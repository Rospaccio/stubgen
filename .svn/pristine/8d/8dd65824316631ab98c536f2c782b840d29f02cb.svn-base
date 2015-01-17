package org.merka.stubgen.web.session;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.merka.stubgen.classloader.JarInputStreamClassLoader;

public class JarManager
{
	private Map<String, JarInputStreamClassLoader> jarSessions;
	
	public int size()
	{
		return jarSessions.size();
	}

	public boolean isEmpty()
	{
		return jarSessions.isEmpty();
	}

	public boolean containsKey(Object key)
	{
		return jarSessions.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return jarSessions.containsValue(value);
	}

	public JarInputStreamClassLoader get(Object key)
	{
		return jarSessions.get(key);
	}

	public JarInputStreamClassLoader put(String key, JarInputStreamClassLoader value)
	{
		return jarSessions.put(key, value);
	}

	public JarInputStreamClassLoader remove(Object key)
	{
		return jarSessions.remove(key);
	}

	public void putAll(Map<? extends String, ? extends JarInputStreamClassLoader> m)
	{
		jarSessions.putAll(m);
	}

	public void clear()
	{
		jarSessions.clear();
	}

	public Set<String> keySet()
	{
		return jarSessions.keySet();
	}

	public Collection<JarInputStreamClassLoader> values()
	{
		return jarSessions.values();
	}

	public Set<Entry<String, JarInputStreamClassLoader>> entrySet()
	{
		return jarSessions.entrySet();
	}

	protected Map<String, JarInputStreamClassLoader> getJarSessions()
	{
		return jarSessions;
	}

	protected void setJarSessions(Map<String, JarInputStreamClassLoader> jarSessions)
	{
		this.jarSessions = jarSessions;
	}

	public JarManager()
	{
		this.jarSessions = new HashMap<String, JarInputStreamClassLoader>();
	}
}
