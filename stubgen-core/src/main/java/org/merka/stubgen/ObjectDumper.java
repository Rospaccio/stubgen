package org.merka.stubgen;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectDumper
{

	protected ObjectMapper mapper;
	
	public void dump(int primitive, String fileName)
	{
		PrintWriter stream = null;
		try
		{
			stream = new PrintWriter(new FileOutputStream("/temp/mockgen/testfile.json", false));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		stream.print("-1");
	}

}
