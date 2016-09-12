package org.xson.common.object;

public class XCOField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private XCO					value;

	public XCOField(String name, XCO value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == XCO_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		value.toXMLString(this.name, builder);
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(name).append("\"").append(":").append(this.value.toJSON());
	}

	@Override
	public IField cloneSelf() {
		return new XCOField(name, value.clone());
	}
}
