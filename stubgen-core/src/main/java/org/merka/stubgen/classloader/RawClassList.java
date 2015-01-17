package org.merka.stubgen.classloader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class RawClassList implements List<RawClass>
{
	private List<String> names;
	
	private ArrayList<RawClass> classes;
	
	public ArrayList<RawClass> getClasses()
	{
		return classes;
	}

	protected void setClasses(ArrayList<RawClass> classes)
	{
		this.classes = classes;
	}

	public RawClassList()
	{
		this.setClasses(new ArrayList<RawClass>());
	}
	
	public void trimToSize()
	{
		classes.trimToSize();
	}

	public void ensureCapacity(int minCapacity)
	{
		classes.ensureCapacity(minCapacity);
	}

	public int size()
	{
		return classes.size();
	}

	public boolean isEmpty()
	{
		return classes.isEmpty();
	}

	public boolean contains(Object o)
	{
		return classes.contains(o);
	}

	public int indexOf(Object o)
	{
		return classes.indexOf(o);
	}

	public boolean containsAll(Collection<?> c)
	{
		return classes.containsAll(c);
	}

	public int lastIndexOf(Object o)
	{
		return classes.lastIndexOf(o);
	}

	public Object clone()
	{
		return classes.clone();
	}

	public Object[] toArray()
	{
		return classes.toArray();
	}

	public <T> T[] toArray(T[] a)
	{
		return classes.toArray(a);
	}

	public RawClass get(int index)
	{
		return classes.get(index);
	}

	public RawClass set(int index, RawClass element)
	{
		return classes.set(index, element);
	}

	public String toString()
	{
		return classes.toString();
	}

	public boolean add(RawClass e)
	{
		return classes.add(e);
	}

	public void add(int index, RawClass element)
	{
		classes.add(index, element);
	}

	public RawClass remove(int index)
	{
		return classes.remove(index);
	}

	public boolean remove(Object o)
	{
		return classes.remove(o);
	}

	public boolean equals(Object o)
	{
		return classes.equals(o);
	}

	public void clear()
	{
		classes.clear();
	}

	public boolean addAll(Collection<? extends RawClass> c)
	{
		return classes.addAll(c);
	}

	public int hashCode()
	{
		return classes.hashCode();
	}

	public boolean addAll(int index, Collection<? extends RawClass> c)
	{
		return classes.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c)
	{
		return classes.removeAll(c);
	}

	public boolean retainAll(Collection<?> c)
	{
		return classes.retainAll(c);
	}

	public ListIterator<RawClass> listIterator(int index)
	{
		return classes.listIterator(index);
	}

	public ListIterator<RawClass> listIterator()
	{
		return classes.listIterator();
	}

	public Iterator<RawClass> iterator()
	{
		return classes.iterator();
	}

	public List<RawClass> subList(int fromIndex, int toIndex)
	{
		return classes.subList(fromIndex, toIndex);
	}
	
	public List<String> getClassesNames()
	{
		if(this.names == null)
		{
			ArrayList<String> names = new ArrayList<String>(getClasses().size());
			for(RawClass raw : getClasses())
			{
				names.add(raw.getName());
			}
			this.names = names;
		}
		return names;
	}
}
