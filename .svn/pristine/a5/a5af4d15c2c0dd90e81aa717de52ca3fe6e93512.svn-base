package org.merka.stubgen.test.junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.merka.stubgen.ObjectDumper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestObjectDumper
{
	public static final String FILE_NAME = "/temp/mockgen/testfile.json"; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
	}

	@After
	public void tearDown() throws Exception
	{
	}

//	@Test
	public void testPrimitiveDumpedEquals()
	{
		ObjectMapper mapper = new ObjectMapper();
		ObjectDumper dumper = new ObjectDumper();
		int primitive = 2;
		dumper.dump(primitive, FILE_NAME);
		int readPrimitive = -1;
		try
		{
			readPrimitive = (int) mapper.readValue(FILE_NAME, int.class);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		assertEquals(readPrimitive, primitive);
	}

}
