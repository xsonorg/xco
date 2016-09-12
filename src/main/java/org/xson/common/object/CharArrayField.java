package org.xson.common.object;

public class CharArrayField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private char[]				value;

	public CharArrayField(String name, char[] value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == CHAR_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<CA " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.encodeTextForXML(this.value));
		builder.append("\"/>");
	}

	protected void setValue(String var) {
		if (null == var) {
			return;
		}
		this.value = var.toCharArray();
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.encodeTextForJSON(new String(this.value))).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new CharArrayField(name, value.clone());
	}

}
