package org.xson.common.object;

public class StringArrayField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private String[]          value;

	public StringArrayField(String name, String[] value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == STRING_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<SA " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\">");
		for (int i = 0, length = this.value.length; i < length; i++) {
			builder.append("<S " + PROPERTY_V + "=\"" + XCOUtil.encodeTextForXML(this.value[i]) + "\"/>");
		}
		builder.append("</SA>");
	}

	protected void setValue(String[] var) {
		this.value = var;
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.length; i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append("\"");
			builder.append(XCOUtil.encodeTextForJSON(this.value[i], browserCompatible));
			builder.append("\"");
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		String[] array = new String[this.value.length];
		for (int i = 0; i < this.value.length; i++) {
			array[i] = new String(this.value[i]);
		}
		return new StringArrayField(name, array);
	}

}
