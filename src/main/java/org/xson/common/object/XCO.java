package org.xson.common.object;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.xson.core.XSON;

import nanoxml.XMLElement;

/**
 * XSON Common Object
 */
public class XCO implements Serializable, Cloneable {

	private static final long		serialVersionUID	= 1L;

	private HashMap<String, IField>	dateMap;
	private ArrayList<String>		fieldList;
	private ArrayList<IField>		fieldValueList;
	private Object					attachObject;

	public XCO() {
		this.dateMap = new HashMap<String, IField>();
		this.fieldList = new ArrayList<String>();
		this.fieldValueList = new ArrayList<IField>();
	}

	public Integer getCode() {
		IField fieldValue = getField("$$CODE");
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.INT_TYPE);
		return ((Integer) value).intValue();
	}

	public String getMessage() {
		IField fieldValue = getField("$$MESSAGE");
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.STRING_TYPE);
		return (String) value;
	}

	@SuppressWarnings("unchecked")
	public <T> T getData() {
		IField fieldValue = getField("$$DATA");
		if (null == fieldValue) {
			return null;
		}
		return (T) fieldValue.getValue();
	}

	public Object getAttachObject() {
		return attachObject;
	}

	public void setAttachObject(Object attachObject) {
		this.attachObject = attachObject;
	}

	/**
	 * 可能存在并发问题
	 */
	protected final void putItem(String key, IField fieldValue) {
		if (null == key) {
			throw new XCOException("Fields are not allowed to be empty");
		}
		IField oldValue = this.dateMap.put(key, fieldValue);
		if (oldValue == null) {
			this.fieldList.add(key);
			this.fieldValueList.add(fieldValue);
		} else {
			int index = this.fieldList.indexOf(key);
			this.fieldValueList.set(index, fieldValue);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setObjectValue(String field, Object value) {
		if (null == value) {
			remove(field);// 删除
			return;
		}

		if (value instanceof Byte) {
			putItem(field, new ByteField(field, ((Byte) value).byteValue()));
		} else if (value instanceof Short) {
			putItem(field, new ShortField(field, ((Short) value).shortValue()));
		} else if (value instanceof Integer) {
			putItem(field, new IntegerField(field, ((Integer) value).intValue()));
		} else if (value instanceof Long) {
			putItem(field, new LongField(field, ((Long) value).longValue()));
		} else if (value instanceof Float) {
			putItem(field, new FloatField(field, ((Float) value).floatValue()));
		} else if (value instanceof Double) {
			putItem(field, new DoubleField(field, ((Double) value).doubleValue()));
		} else if (value instanceof Character) {
			putItem(field, new CharField(field, ((Character) value).charValue()));
		} else if (value instanceof Boolean) {
			putItem(field, new BooleanField(field, ((Boolean) value).booleanValue()));
		} else if (value instanceof String) {
			putItem(field, new StringField(field, (String) value));
		} else if (value instanceof XCO) {
			putItem(field, new XCOField(field, (XCO) value));
		} else if (value instanceof java.sql.Date) {
			putItem(field, new SqlDateField(field, (java.sql.Date) value));
		} else if (value instanceof java.sql.Time) {
			putItem(field, new SqlTimeField(field, (java.sql.Time) value));
		} else if (value instanceof java.sql.Timestamp) {
			putItem(field, new TimestampField(field, (java.sql.Timestamp) value));
		} else if (value instanceof java.util.Date) {// fix bug
			putItem(field, new DateField(field, (java.util.Date) value));
		} else if (value instanceof BigInteger) {
			putItem(field, new BigIntegerField(field, (BigInteger) value));
		} else if (value instanceof BigDecimal) {
			putItem(field, new BigDecimalField(field, (BigDecimal) value));
		}

		else if (value instanceof byte[]) {
			putItem(field, new ByteArrayField(field, (byte[]) value));
		} else if (value instanceof short[]) {
			putItem(field, new ShortArrayField(field, (short[]) value));
		} else if (value instanceof int[]) {
			putItem(field, new IntegerArrayField(field, (int[]) value));
		} else if (value instanceof long[]) {
			putItem(field, new LongArrayField(field, (long[]) value));
		} else if (value instanceof float[]) {
			putItem(field, new FloatArrayField(field, (float[]) value));
		} else if (value instanceof double[]) {
			putItem(field, new DoubleArrayField(field, (double[]) value));
		} else if (value instanceof char[]) {
			putItem(field, new CharArrayField(field, (char[]) value));
		} else if (value instanceof boolean[]) {
			putItem(field, new BooleanArrayField(field, (boolean[]) value));
		} else if (value instanceof String[]) {
			putItem(field, new StringArrayField(field, (String[]) value));
		} else if (value instanceof XCO[]) {
			putItem(field, new XCOArrayField(field, (XCO[]) value));
		}

		else if (value instanceof List) {
			List list = (List) value;
			if (list.size() > 0) {
				Object item = list.get(0);
				if (item instanceof String) {
					putItem(field, new StringListField(field, (List<String>) value));
				} else if (item instanceof XCO) {
					putItem(field, new XCOListField(field, (List<XCO>) value));
				}
			}
		}

		else if (value instanceof Set) {
			Set set = (Set) value;
			for (Object item : set) {
				if (item instanceof String) {
					putItem(field, new StringSetField(field, (Set<String>) value));
				} else if (item instanceof XCO) {
					putItem(field, new XCOSetField(field, (Set<XCO>) value));
				}
				break;
			}
		}

		else {
			throw new XCOException("Unsupported data type: " + value.getClass());
		}

	}

	public Object getObjectValue(String field) {
		// IField fieldValue = this.dateMap.get(field);
		IField fieldValue = getField(field);
		if (null != fieldValue) {
			return fieldValue.getValue();
		}
		return null;
	}

	public Object get(String field) {
		return getObjectValue(field);
	}

	public void remove(String field) {
		if (exists(field)) {
			// int i = 0;
			// for (String fieldItem : fieldList) {
			// if (field.equals(fieldItem)) {
			// this.fieldList.remove(i);
			// this.fieldValueList.remove(i);
			// }
			// i++;
			// }
			int i = this.fieldList.indexOf(field);
			this.fieldList.remove(i);
			this.fieldValueList.remove(i);
			this.dateMap.remove(field);
		}
	}

	protected void setField(String field, IField fieldValue) {
		putItem(field, fieldValue);
	}

	protected IField getField(String field) {
		return XCOOgnl.getField(this, field);
	}

	/**
	 * OgnlXCO专用
	 */
	protected IField getField0(String field) {
		return this.dateMap.get(field);
	}

	public final boolean exists(String field) {
		if (this.dateMap.containsKey(field)) {
			return true;
		}
		return false;
	}

	public final boolean isEmpty() {
		if (this.dateMap.size() == 0) {
			return true;
		}
		return false;
	}

	public final Set<String> keys() {
		return this.dateMap.keySet();
	}

	public final int size() {
		return this.dateMap.size();
	}

	public final List<String> keysList() {
		return this.fieldList;
	}

	// ///set....

	public final void setByteValue(String field, byte var) {
		setField(field, new ByteField(field, var));
	}

	public final void setByteArrayValue(String field, byte[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new ByteArrayField(field, var));
	}

	public final void setShortValue(String field, short var) {
		setField(field, new ShortField(field, var));
	}

	public final void setShortArrayValue(String field, short[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new ShortArrayField(field, var));
	}

	public final void setLongValue(String field, long var) {
		setField(field, new LongField(field, var));
	}

	public final void setLongArrayValue(String field, long[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new LongArrayField(field, var));
	}

	public final void setFloatValue(String field, float var) {
		setField(field, new FloatField(field, var));
	}

	public final void setFloatArrayValue(String field, float[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new FloatArrayField(field, var));
	}

	public final void setDoubleValue(String field, double var) {
		setField(field, new DoubleField(field, var));
	}

	public final void setDoubleArrayValue(String field, double[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new DoubleArrayField(field, var));
	}

	public final void setCharValue(String field, char var) {
		setField(field, new CharField(field, var));
	}

	public final void setCharArrayValue(String field, char[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new CharArrayField(field, var));
	}

	public final void setBooleanValue(String field, boolean var) {
		setField(field, new BooleanField(field, var));
	}

	public final void setBooleanArrayValue(String field, boolean[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new BooleanArrayField(field, var));
	}

	public final void setDateTimeValue(String field, java.util.Date var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new DateField(field, var));
	}

	public final void setDateValue(String field, java.sql.Date var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new SqlDateField(field, var));
	}

	public final void setTimeValue(String field, java.sql.Time var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new SqlTimeField(field, var));
	}

	public final void setTimestampValue(String field, java.sql.Timestamp var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new TimestampField(field, var));
	}

	public final void setBigIntegerValue(String field, BigInteger var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new BigIntegerField(field, var));
	}

	public final void setBigDecimalValue(String field, BigDecimal var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new BigDecimalField(field, var));
	}

	public final void setIntegerValue(String field, int var) {
		setField(field, new IntegerField(field, var));
	}

	public final void setIntegerArrayValue(String field, int[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new IntegerArrayField(field, var));
	}

	public final void setStringValue(String field, String var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new StringField(field, var));
	}

	public final void setStringArrayValue(String field, String[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new StringArrayField(field, var));
	}

	public final void setStringListValue(String field, List<String> var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new StringListField(field, var));
	}

	public final void setStringSetValue(String field, Set<String> var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new StringSetField(field, var));
	}

	public final void setXCOValue(String field, XCO var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new XCOField(field, var));
	}

	public final void setXCOArrayValue(String field, XCO[] var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new XCOArrayField(field, var));
	}

	public final void setXCOListValue(String field, List<XCO> var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new XCOListField(field, var));
	}

	public final void setXCOSetValue(String field, Set<XCO> var) {
		if (null == var) {
			remove(field);
			return;
		}
		setField(field, new XCOSetField(field, var));
	}

	// //get...

	public final byte getByteValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.BYTE_TYPE);
		return ((Byte) value).byteValue();
	}

	public final Byte getByte(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BYTE_TYPE);
		return (Byte) value;
	}

	public final byte[] getByteArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BYTE_ARRAY_TYPE);
		return (byte[]) value;
	}

	public final short getShortValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.SHORT_TYPE);
		return ((Short) value).shortValue();
	}

	public final Short getShort(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.SHORT_TYPE);
		return (Short) value;
	}

	public final short[] getShortArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.SHORT_ARRAY_TYPE);
		return (short[]) value;
	}

	public final long getLongValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.LONG_TYPE);
		return ((Long) value).longValue();
	}

	public final Long getLong(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.LONG_TYPE);
		return (Long) value;
	}

	public final long[] getLongArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.LONG_ARRAY_TYPE);
		return (long[]) value;
	}

	public final float getFloatValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.FLOAT_TYPE);
		return ((Float) value).floatValue();
	}

	public final Float getFloat(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.FLOAT_TYPE);
		return (Float) value;
	}

	public final float[] getFloatArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.FLOAT_ARRAY_TYPE);
		return (float[]) value;
	}

	public final double getDoubleValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.DOUBLE_TYPE);
		return ((Double) value).doubleValue();
	}

	public final Double getDouble(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.DOUBLE_TYPE);
		return (Double) value;
	}

	public final double[] getDoubleArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.DOUBLE_ARRAY_TYPE);
		return (double[]) value;
	}

	public final char getCharValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.CHAR_TYPE);
		return ((Character) value).charValue();
	}

	public final Character getChar(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.CHAR_TYPE);
		return (Character) value;
	}

	public final char[] getCharArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.CHAR_ARRAY_TYPE);
		return (char[]) value;
	}

	public final boolean getBooleanValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.BOOLEAN_TYPE);
		return ((Boolean) value).booleanValue();
	}

	public final Boolean getBoolean(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BOOLEAN_TYPE);
		return (Boolean) value;
	}

	public final boolean[] getBooleanArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BOOLEAN_ARRAY_TYPE);
		return (boolean[]) value;
	}

	public final java.util.Date getDateTimeValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.DATE_TYPE);
		return (java.util.Date) value;
	}

	public final java.sql.Date getDateValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.SQLDATE_TYPE);
		return (java.sql.Date) value;
	}

	public final java.sql.Time getTimeValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.SQLTIME_TYPE);
		return (java.sql.Time) value;
	}

	public final java.sql.Timestamp getTimestampValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.TIMESTAMP_TYPE);
		return (java.sql.Timestamp) value;
	}

	public final BigInteger getBigIntegerValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BIGINTEGER_TYPE);
		return (BigInteger) value;
	}

	public final BigDecimal getBigDecimalValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.BIGDICIMAL_TYPE);
		return (BigDecimal) value;
	}

	public final int getIntegerValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			throw new XCOException("The field does not exist: " + field);
		}
		Object value = fieldValue.getValue(DataType.INT_TYPE);
		return ((Integer) value).intValue();
	}

	public final int[] getIntegerArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.INT_ARRAY_TYPE);
		return (int[]) value;
	}

	public final String getStringValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.STRING_TYPE);
		return (String) value;
	}

	public final String[] getStringArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.STRING_ARRAY_TYPE);
		return (String[]) value;
	}

	@SuppressWarnings("unchecked")
	public final List<String> getStringListValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.STRING_LIST_TYPE);
		return (List<String>) value;
	}

	@SuppressWarnings("unchecked")
	public final Set<String> getStringSetValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.STRING_SET_TYPE);
		return (Set<String>) value;
	}

	public final XCO getXCOValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.XCO_TYPE);
		return (XCO) value;
	}

	public final XCO[] getXCOArrayValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.XCO_ARRAY_TYPE);
		return (XCO[]) value;
	}

	@SuppressWarnings("unchecked")
	public final List<XCO> getXCOListValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.XCO_LIST_TYPE);
		return (List<XCO>) value;
	}

	@SuppressWarnings("unchecked")
	public final Set<XCO> getXCOSetValue(String field) {
		IField fieldValue = getField(field);
		if (null == fieldValue) {
			return null;
		}
		Object value = fieldValue.getValue(DataType.XCO_SET_TYPE);
		return (Set<XCO>) value;
	}

	// //////////////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	private void fromXML0(XMLElement xmlNode) {
		for (Object obj : xmlNode.getChildren()) {
			XMLElement node = (XMLElement) obj;
			String tag = node.getName();

			if ("B".equals(tag)) { // byte
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new ByteField(k, Byte.parseByte(v)));
			} else if ("H".equals(tag)) { // short
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new ShortField(k, Short.parseShort(v)));
			} else if ("I".equals(tag)) { // int
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new IntegerField(k, Integer.parseInt(v)));
			} else if ("L".equals(tag)) { // long
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new LongField(k, Long.parseLong(v)));
			} else if ("F".equals(tag)) { // float
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new FloatField(k, Float.parseFloat(v)));
			} else if ("D".equals(tag)) { // double
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new DoubleField(k, Double.parseDouble(v)));
			} else if ("C".equals(tag)) { // char
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new CharField(k, v.charAt(0)));
			} else if ("O".equals(tag)) { // boolean
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new BooleanField(k, Boolean.parseBoolean(v)));
			} else if ("S".equals(tag)) { // string
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new StringField(k, v));
			} else if ("X".equals(tag)) { // xco
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				XCO xco = new XCO();
				xco.fromXML0(node);
				putItem(k, new XCOField(k, xco));
			} else if ("A".equals(tag)) { // date
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new DateField(k, XCOUtil.parseDateTime(v)));
			} else if ("E".equals(tag)) { // sql.date
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new SqlDateField(k, XCOUtil.parseDate(v)));
			} else if ("G".equals(tag)) { // sql.time
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new SqlTimeField(k, XCOUtil.parseTime(v)));
			} else if ("J".equals(tag)) { // sql.timestamp
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new TimestampField(k, XCOUtil.parseTimestamp(v)));
			} else if ("K".equals(tag)) { // bigInteger
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new BigIntegerField(k, new BigInteger(v)));
			} else if ("M".equals(tag)) { // bigDicimal
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				putItem(k, new BigDecimalField(k, new BigDecimal(v)));
			} else if ("SL".equals(tag)) { // string list
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				List<String> list = new ArrayList<String>();
				for (XMLElement child : childList) {
					String childTag = child.getName();
					if (!childTag.equals("S")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					list.add(child.getStringAttribute(DataType.PROPERTY_V));
				}
				StringListField fieldValue = new StringListField(k, list);
				putItem(k, fieldValue);
			} else if ("SS".equals(tag)) { // string set
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				Set<String> set = new HashSet<String>();
				for (XMLElement child : childList) {
					String childTag = child.getName();
					if (!childTag.equals("S")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					set.add(child.getStringAttribute(DataType.PROPERTY_V));
				}
				StringSetField fieldValue = new StringSetField(k, set);
				putItem(k, fieldValue);
			} else if ("XL".equals(tag)) { // xco list
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				List<XCO> xcos = new ArrayList<XCO>();
				for (XMLElement child : childList) {
					String childTag = child.getName();
					if (!childTag.equals("X")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					XCO xco = new XCO();
					xco.fromXML0(child);
					xcos.add(xco);
				}
				XCOListField fieldValue = new XCOListField(k, xcos);
				putItem(k, fieldValue);
			} else if ("XS".equals(tag)) { // xco set
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				Set<XCO> xcos = new HashSet<XCO>();
				for (XMLElement child : childList) {
					String childTag = child.getName();
					if (!childTag.equals("X")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					XCO xco = new XCO();
					xco.fromXML0(child);
					xcos.add(xco);
				}
				XCOSetField fieldValue = new XCOSetField(k, xcos);
				putItem(k, fieldValue);
			} else if ("BA".equals(tag)) { // byte[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				ByteArrayField fieldValue = new ByteArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("HA".equals(tag)) { // short[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				ShortArrayField fieldValue = new ShortArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("IA".equals(tag)) { // int[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				IntegerArrayField fieldValue = new IntegerArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("LA".equals(tag)) { // long[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				LongArrayField fieldValue = new LongArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("FA".equals(tag)) { // float[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				FloatArrayField fieldValue = new FloatArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("DA".equals(tag)) { // double[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				DoubleArrayField fieldValue = new DoubleArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("CA".equals(tag)) { // char[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				CharArrayField fieldValue = new CharArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("OA".equals(tag)) { // boolean[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				String v = node.getStringAttribute(DataType.PROPERTY_V);
				BooleanArrayField fieldValue = new BooleanArrayField(k, null);
				fieldValue.setValue(v);
				putItem(k, fieldValue);
			} else if ("SA".equals(tag)) { // string[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				String[] array = new String[childList.size()];
				int i = 0;
				for (XMLElement child : childList) {
					String childTag = child.getName();
					if (!childTag.equals("S")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					array[i++] = child.getStringAttribute(DataType.PROPERTY_V);
				}
				StringArrayField fieldValue = new StringArrayField(k, array);
				putItem(k, fieldValue);
			} else if ("XA".equals(tag)) { // xco[]
				String k = node.getStringAttribute(DataType.PROPERTY_K);
				Vector<XMLElement> childList = node.getChildren();
				XCO[] xcos = new XCO[childList.size()];
				for (int i = 0; i < xcos.length; i++) {
					XMLElement child = childList.get(i);
					String childTag = child.getName();
					if (!childTag.equals("X")) {
						throw new XCOException("Parse xml error: unexpected Tag name " + childTag + " under " + tag);
					}
					XCO xco = new XCO();
					xco.fromXML0(child);
					xcos[i] = xco;
				}
				XCOArrayField fieldValue = new XCOArrayField(k, xcos);
				putItem(k, fieldValue);
			}
		}
	}

	protected void toXMLString(String key, StringBuilder builder) {
		if (null == key) {
			builder.append("<X>");
		} else {
			builder.append("<X " + DataType.PROPERTY_K + "=\"" + key + "\">");
		}
		for (int i = 0, size = fieldValueList.size(); i < size; i++) {
			fieldValueList.get(i).toXMLString(builder);
		}
		builder.append("</X>");
	}

	public String toJSON() {
		StringBuilder builder = new StringBuilder(1024);
		builder.append("{");
		for (int i = 0, size = fieldValueList.size(); i < size; i++) {
			if (i > 0) {
				builder.append(",");
			}
			fieldValueList.get(i).toJSONString(builder);
		}
		builder.append("}");
		return builder.toString();
	}

	public String toXMLString() {
		StringBuilder builder = new StringBuilder(1024);
		builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		toXMLString(null, builder);
		return builder.toString();
	}

	public void copyFrom(XCO source) {
		for (String field : source.fieldList) {
			IField fieldValue = source.dateMap.get(field).cloneSelf();
			this.putItem(field, fieldValue);
		}
	}

	public XCO clone() {
		XCO xco = new XCO();
		xco.copyFrom(this);
		return xco;
	}

	@Override
	public String toString() {
		return toXMLString();
	}

	public static XCO fromXML(String xml) {
		XMLElement xmlNode = new XMLElement();
		xmlNode.parseString(xml);
		XCO xco = new XCO();
		xco.fromXML0(xmlNode);
		return xco;
	}

	public byte[] toBytes() {
		return toBytes(0);
	}

	public byte[] toBytes(int offsetLength) {
		return XSON.encode(this, offsetLength);
	}

	public static XCO fromBytes(byte[] buffer) {
		return fromBytes(buffer, 0);
	}

	public static XCO fromBytes(byte[] buffer, int offsetLength) {
		return XSON.decode(buffer, offsetLength);
	}

	public void append(XCO xco) {
		int size = xco.fieldList.size();
		for (int i = 0; i < size; i++) {
			this.putItem(xco.fieldList.get(i), xco.fieldValueList.get(i));
		}
	}

}
