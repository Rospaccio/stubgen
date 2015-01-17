package org.merka.stubgen.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.merka.stubgen.MockObjectGenerator;
import org.merka.stubgen.exception.MockGenException;
import org.merka.stubgen.stupiddto.ComplexTestObject;
import org.merka.stubgen.stupiddto.StupidPOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;

public class TestMockObjectGenerator {
	
	private static transient Logger logger = LoggerFactory.getLogger(TestMockObjectGenerator.class);
	
	protected MockObjectGenerator gen;
	
	@Before
	public void setUp()
	{
		gen = new MockObjectGenerator();
	}
	
	@After
	public void tearDown()
	{
		gen = null;
	}
	
	@Test
	public void testGeneratePrimitiveTypes() throws MockGenException 
	{
		Object stub = generateForClass(int.class);
		assertTrue(stub instanceof Integer);
		
		stub = generateForClass(long.class);
		assertTrue(stub instanceof Long);
		
		stub = generateForClass(char.class);
		assertTrue(stub instanceof Character);
		
		stub = generateForClass(byte.class);
		assertTrue(stub instanceof Byte);
		
		stub = generateForClass(boolean.class);
		assertTrue(stub instanceof Boolean);
		
		stub = generateForClass(float.class);
		assertTrue(stub instanceof Float);
	}
	
	@Test
	public void testGenerateObject() throws MockGenException
	{
		Object stub = gen.generate(StupidPOJO.class);
		assertNotNull(stub);
		assertTrue(stub instanceof StupidPOJO);
		StupidPOJO stupid = (StupidPOJO) stub;
		assertEquals(stupid.getStupidInt(), 0);
		assertNotNull(stupid.getStupidString());
	}
	
	@Test
	public void testGenerateComplexObject() throws MockGenException
	{
		Object stub = gen.generate(ComplexTestObject.class);
		assertNotNull(stub);
		logger.info(stub.toString());
		assertTrue(stub instanceof ComplexTestObject);
		ComplexTestObject complex = (ComplexTestObject) stub;
		
		assertNotNull(complex.getBigDecimalArray());
		assertTrue(complex.getBigDecimalArray().length > 0);
		assertNotNull(complex.getObjectArray());
		assertTrue(complex.getObjectArray().length > 0);
		assertNotNull(complex.getObjectList());
		assertTrue(complex.getObjectList().size() > 0);
		assertNotNull(complex.getSingleObject());
		assertNotNull(complex.getType());
	}
	
	@Test(expected = MockGenException.class)
	public void testGenerateListDirectly() throws MockGenException
	{
		List<ComplexTestObject> pilot = new ArrayList<ComplexTestObject>();
		Object stublist = (List<ComplexTestObject>) gen.generate( (Class<?>) pilot.getClass() );
	}
	
	@Test
	public void testGenerateList() throws MockGenException
	{
		Object stub = gen.generateList(ComplexTestObject.class, 3);
		assertNotNull(stub);
		assertTrue(stub instanceof List<?>);
		List<?> list = (List<?>) stub;
		assertTrue(list.size() > 0);
		Object element = list.get(0);
		assertNotNull(element);
		assertTrue(element instanceof ComplexTestObject);
	}
	
	protected Object generateForClass(Class<?> clazz) throws MockGenException
	{
		Object stub = this.gen.generate(clazz);
		return stub;
	}
}