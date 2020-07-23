package org.xson.common.object;

import java.sql.Timestamp;

public class TimestampField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private Timestamp         value;

	public TimestampField(String name, Timestamp value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == TIMESTAMP_TYPE) {
			return value;
		}
		if (dataType == STRING_TYPE) {
			return XCOUtil.getTimestampString(value);
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<J " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.getTimestampString(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.getTimestampString(this.value)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new TimestampField(name, new java.sql.Timestamp(this.value.getTime()));
	}

}
