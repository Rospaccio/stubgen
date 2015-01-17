package org.merka.stubgen.stupiddto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BoxObjectContainer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9083433947015690317L;
	
	protected Integer integerField;
	protected Long longField;
	protected Boolean booleanField;
	protected Byte byteField;
	protected BigDecimal bigDecimelField;
	protected Double doubleField;
	protected Float floatField;
	protected Character charField;
	
	public Integer getIntegerField()
	{
		return integerField;
	}
	public void setIntegerField(Integer integerField)
	{
		this.integerField = integerField;
	}
	public Long getLongField()
	{
		return longField;
	}
	public void setLongField(Long longField)
	{
		this.longField = longField;
	}
	public Boolean getBooleanField()
	{
		return booleanField;
	}
	public void setBooleanField(Boolean booleanField)
	{
		this.booleanField = booleanField;
	}
	public Byte getByteField()
	{
		return byteField;
	}
	public void setByteField(Byte byteField)
	{
		this.byteField = byteField;
	}
	public BigDecimal getBigDecimelField()
	{
		return bigDecimelField;
	}
	public void setBigDecimelField(BigDecimal bigDecimelField)
	{
		this.bigDecimelField = bigDecimelField;
	}
	public Double getDoubleField()
	{
		return doubleField;
	}
	public void setDoubleField(Double doubleField)
	{
		this.doubleField = doubleField;
	}
	public Float getFloatField()
	{
		return floatField;
	}
	public void setFloatField(Float floatField)
	{
		this.floatField = floatField;
	}
	public Character getCharField()
	{
		return charField;
	}
	public void setCharField(Character charField)
	{
		this.charField = charField;
	}
	@Override
	public String toString() {
		return "BoxObjectContainer [integerField=" + integerField
				+ ", longField=" + longField + ", booleanField=" + booleanField
				+ ", byteField=" + byteField + ", bigDecimelField="
				+ bigDecimelField + ", doubleField=" + doubleField
				+ ", floatField=" + floatField + ", charField=" + charField
				+ "]";
	}
	
	
}
