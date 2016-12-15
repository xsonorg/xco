package org.xson.common.object;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class XCOOgnl {

	/**
	 * 是否是简单属性
	 */
	private static boolean isSimpleField(String text) {
		int dotIndex = text.indexOf(".");
		if (dotIndex > -1) {
			return false;
		}
		int squareBracketsIndex = text.indexOf("[");
		if (squareBracketsIndex > -1) {
			return false;
		}
		return true;
	}

	private static List<Object> parseField(String text) {
		// 0:属性, 1:索引
		List<Object> list = new ArrayList<Object>();
		StringBuilder builder = new StringBuilder();

		char[] src = text.toCharArray();
		int srcLength = src.length;

		for (int i = 0; i < srcLength; i++) {
			char key = src[i];
			switch (key) {
			case '.':
				if (builder.length() > 0) {
					list.add(builder.toString());
					builder = new StringBuilder();
				}
				break;
			case '[':
				if (builder.length() > 0) {
					list.add(builder.toString());
					builder = new StringBuilder();
				}
				// find ']'
				int endTagPos = findEndTag(text, i, text.length(), ']');
				if (endTagPos > -1) {
					String temp = new String(src, i + 1, endTagPos - i - 1);
					if ((temp.startsWith("'") && temp.endsWith("'")) || (temp.startsWith("\"") && temp.endsWith("\""))) {
						list.add(temp.substring(1, temp.length() - 1));
					} else {
						list.add(Integer.parseInt(temp));
					}
					i = endTagPos;
				} else {
					throw new XCOException("Illegal field name: " + text);
				}
				break;
			default:
				builder.append(key);
			}
		}
		if (builder.length() > 0) {
			list.add(builder.toString());
		}
		return list;
	}

	private static int findEndTag(String text, int start, int end, char endTag) {
		for (int i = start + 1; i < end; i++) {
			if (endTag == text.charAt(i)) {
				return i;
			}
		}
		return -1;
	}

	protected static IField getField(XCO xco, String field) {
		if (isSimpleField(field)) {
			return xco.getField0(field);
		} else {
			List<Object> fieldItemList = parseField(field);
			int size = fieldItemList.size();
			IField returnObj = new XCOField(null, xco);
			for (int i = 0; i < size; i++) {
				boolean hasNext = (i + 1) < size;
				Object fieldItem = fieldItemList.get(i);
				if (returnObj instanceof XCOField) {
					returnObj = getFieldFromXCO(returnObj, fieldItem);
				} else if (returnObj instanceof XCOArrayField) {
					returnObj = getFieldFromXCOArray(returnObj, fieldItem);
				} else if (returnObj instanceof XCOListField) {
					returnObj = getFieldFromXCOList(returnObj, fieldItem);
				} else if (returnObj instanceof XCOSetField) {
					returnObj = getFieldFromXCOSet(returnObj, fieldItem);
				}

				else if (returnObj instanceof StringArrayField) {
					returnObj = getFieldFromStringArray(returnObj, fieldItem);
				} else if (returnObj instanceof StringListField) {
					returnObj = getFieldFromStringList(returnObj, fieldItem);
				} else if (returnObj instanceof StringSetField) {
					returnObj = getFieldFromStringSet(returnObj, fieldItem);
				}

				else if (returnObj instanceof IntegerArrayField) {
					returnObj = getFieldFromIntegerArray(returnObj, fieldItem);
				} else if (returnObj instanceof LongArrayField) {
					returnObj = getFieldFromLongArray(returnObj, fieldItem);
				} else if (returnObj instanceof FloatArrayField) {
					returnObj = getFieldFromFloatArray(returnObj, fieldItem);
				} else if (returnObj instanceof DoubleArrayField) {
					returnObj = getFieldFromDoubleArray(returnObj, fieldItem);
				} else if (returnObj instanceof ByteArrayField) {
					returnObj = getFieldFromByteArray(returnObj, fieldItem);
				} else if (returnObj instanceof ShortArrayField) {
					returnObj = getFieldFromShortArray(returnObj, fieldItem);
				} else if (returnObj instanceof BooleanArrayField) {
					returnObj = getFieldFromBooleanArray(returnObj, fieldItem);
				} else if (returnObj instanceof CharArrayField) {
					returnObj = getFieldFromCharArray(returnObj, fieldItem);
				}

				else {
					throw new XCOException("Get value error from XCO: " + field);
				}

				if (null == returnObj && hasNext) {
					return null;
				}
			}
			return returnObj;
		}
	}

	private static IField getFieldFromXCO(Object target, Object fieldItem) {
		XCOField fieldVo = (XCOField) target;
		String key = (String) fieldItem;
		XCO xco = (XCO) fieldVo.getValue();
		return xco.getField0(key);
	}

	private static IField getFieldFromXCOArray(Object target, Object fieldItem) {
		XCOArrayField fieldVo = (XCOArrayField) target;
		Integer key = (Integer) fieldItem;
		XCO[] value = (XCO[]) fieldVo.getValue();
		return new XCOField(null, value[key.intValue()]);
	}

	@SuppressWarnings("unchecked")
	private static IField getFieldFromXCOList(Object target, Object fieldItem) {
		XCOListField fieldVo = (XCOListField) target;
		Integer key = (Integer) fieldItem;
		List<XCO> value = (List<XCO>) fieldVo.getValue();
		return new XCOField(null, value.get(key.intValue()));
	}

	@SuppressWarnings("unchecked")
	private static IField getFieldFromXCOSet(Object target, Object fieldItem) {
		XCOSetField fieldVo = (XCOSetField) target;
		Integer key = (Integer) fieldItem;
		Set<XCO> value = (Set<XCO>) fieldVo.getValue();
		int i = 0;
		for (XCO xco : value) {
			if (i++ == key.intValue()) {
				return new XCOField(null, xco);
			}
		}
		return null;
	}

	private static IField getFieldFromStringArray(Object target, Object fieldItem) {
		StringArrayField fieldVo = (StringArrayField) target;
		Integer key = (Integer) fieldItem;
		String[] value = (String[]) fieldVo.getValue();
		return new StringField(null, value[key.intValue()]);
	}

	@SuppressWarnings("unchecked")
	private static IField getFieldFromStringList(Object target, Object fieldItem) {
		StringListField fieldVo = (StringListField) target;
		Integer key = (Integer) fieldItem;
		List<String> value = (List<String>) fieldVo.getValue();
		return new StringField(null, value.get(key.intValue()));
	}

	@SuppressWarnings("unchecked")
	private static IField getFieldFromStringSet(Object target, Object fieldItem) {
		StringSetField fieldVo = (StringSetField) target;
		Integer key = (Integer) fieldItem;
		Set<String> value = (Set<String>) fieldVo.getValue();
		int i = 0;
		for (String str : value) {
			if (i++ == key.intValue()) {
				return new StringField(null, str);
			}
		}
		return null;
	}

	private static IField getFieldFromIntegerArray(Object target, Object fieldItem) {
		IntegerArrayField fieldVo = (IntegerArrayField) target;
		Integer key = (Integer) fieldItem;
		int[] value = (int[]) fieldVo.getValue();
		return new IntegerField(null, value[key.intValue()]);
	}

	private static IField getFieldFromLongArray(Object target, Object fieldItem) {
		LongArrayField fieldVo = (LongArrayField) target;
		Integer key = (Integer) fieldItem;
		long[] value = (long[]) fieldVo.getValue();
		return new LongField(null, value[key.intValue()]);
	}

	private static IField getFieldFromFloatArray(Object target, Object fieldItem) {
		FloatArrayField fieldVo = (FloatArrayField) target;
		Integer key = (Integer) fieldItem;
		float[] value = (float[]) fieldVo.getValue();
		return new FloatField(null, value[key.intValue()]);
	}

	private static IField getFieldFromDoubleArray(Object target, Object fieldItem) {
		DoubleArrayField fieldVo = (DoubleArrayField) target;
		Integer key = (Integer) fieldItem;
		double[] value = (double[]) fieldVo.getValue();
		return new DoubleField(null, value[key.intValue()]);
	}

	private static IField getFieldFromByteArray(Object target, Object fieldItem) {
		ByteArrayField fieldVo = (ByteArrayField) target;
		Integer key = (Integer) fieldItem;
		byte[] value = (byte[]) fieldVo.getValue();
		return new ByteField(null, value[key.intValue()]);
	}

	private static IField getFieldFromShortArray(Object target, Object fieldItem) {
		ShortArrayField fieldVo = (ShortArrayField) target;
		Integer key = (Integer) fieldItem;
		short[] value = (short[]) fieldVo.getValue();
		return new ShortField(null, value[key.intValue()]);
	}

	private static IField getFieldFromBooleanArray(Object target, Object fieldItem) {
		BooleanArrayField fieldVo = (BooleanArrayField) target;
		Integer key = (Integer) fieldItem;
		boolean[] value = (boolean[]) fieldVo.getValue();
		return new BooleanField(null, value[key.intValue()]);
	}

	private static IField getFieldFromCharArray(Object target, Object fieldItem) {
		CharArrayField fieldVo = (CharArrayField) target;
		Integer key = (Integer) fieldItem;
		char[] value = (char[]) fieldVo.getValue();
		return new CharField(null, value[key.intValue()]);
	}

}
