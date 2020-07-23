package org.xson.common.object;

import java.util.ArrayList;
import java.util.List;

public class LongArrayField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private long[]				value;

	public LongArrayField(String name, long[] value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == LONG_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<LA " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(arrayToString(this.value));
		builder.append("\"/>");
	}

	private String arrayToString(long[] a) {
		StringBuilder b = new StringBuilder();
		for (int i = 0, length = a.length; i < length; i++) {
			if (i > 0) {
				b.append(",");
			}
			b.append(a[i]);
		}
		return b.toString();
	}

	protected void setValue(String var) {
		if (null == var) {
			return;
		}
		List<String> list = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();
		char[] src = var.toCharArray();
		for (int i = 0; i < src.length; i++) {
			char key = src[i];
			switch (key) {
			case ',':
				if (builder.length() > 0) {
					list.add(builder.toString());
					builder = new StringBuilder();
				}
				break;
			default:
				builder.append(key);
				break;
			}
		}
		if (builder.length() > 0) {
			list.add(builder.toString());
		}
		int size = list.size();
		value = new long[size];
		for (int i = 0; i < size; i++) {
			value[i] = Long.parseLong(list.get(i));
		}
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.length; i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append(this.value[i]);
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		return new LongArrayField(name, value.clone());
	}

}
