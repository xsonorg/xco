package org.xson.common.object;

public class CharField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private char              value;

	public CharField(String name, char value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == CHAR_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<C " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.encodeTextForXML(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.encodeTextForJSON(String.valueOf(this.value), browserCompatible)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new CharField(name, value);
	}

}
