package org.xson.common.object;

public class BooleanArrayField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private boolean[]			value;

	public BooleanArrayField(String name, boolean[] value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == BOOLEAN_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<OA " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(arrayToString(this.value));
		builder.append("\"/>");
	}

	private String arrayToString(boolean[] a) {
		StringBuilder b = new StringBuilder();
		for (int i = 0, length = a.length; i < length; i++) {
			if (a[i]) {
				b.append('T');
			} else {
				b.append('F');
			}
		}
		return b.toString();
	}

	protected void setValue(String var) {
		if (null == var) {
			return;
		}
		char[] src = var.toCharArray();
		value = new boolean[src.length];
		for (int i = 0; i < src.length; i++) {
			if (src[i] == 'T') {
				value[i] = true;
			} else {
				value[i] = false;
			}
		}
	}

	@Override
	public void toJSONString(StringBuilder builder) {
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
		return new BooleanArrayField(name, value.clone());
	}

}
