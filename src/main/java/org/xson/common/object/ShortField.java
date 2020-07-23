package org.xson.common.object;

public class ShortField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private short				value;

	public ShortField(String name, short value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == SHORT_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<H " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(this.value);
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append(this.value);
	}

	@Override
	public IField cloneSelf() {
		return new ShortField(name, value);
	}
}
