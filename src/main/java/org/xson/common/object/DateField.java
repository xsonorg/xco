package org.xson.common.object;

public class DateField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private java.util.Date    value;

	public DateField(String name, java.util.Date value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == DATE_TYPE) {
			return value;
		}

		if (dataType == STRING_TYPE) {
			return XCOUtil.getDateTimeString(value);
		}

		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<A " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.getDateTimeString(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.getDateTimeString(this.value)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new DateField(name, (java.util.Date) value.clone());
	}
}
