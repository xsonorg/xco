package org.xson.common.object;

public class StringField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private String				value;

	public StringField(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == STRING_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<S " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.encodeTextForXML(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.encodeTextForJSON(this.value)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new StringField(name, new String(value));
	}
}
