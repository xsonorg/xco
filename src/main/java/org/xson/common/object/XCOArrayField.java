package org.xson.common.object;

public class XCOArrayField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private XCO[]				value;

	public XCOArrayField(String name, XCO[] value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == XCO_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<XA " + DataType.PROPERTY_K + "=\"" + this.name + "\">");
		for (int i = 0, length = value.length; i < length; i++) {
			value[i].toXMLString(null, builder);
		}
		builder.append("</XA>");
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.length; i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append(this.value[i].toJSON());
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		XCO[] array = new XCO[this.value.length];
		for (int i = 0; i < this.value.length; i++) {
			array[i] = this.value[i].clone();
		}
		return new XCOArrayField(name, array);
	}
}
