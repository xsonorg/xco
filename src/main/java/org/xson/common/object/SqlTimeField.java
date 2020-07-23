package org.xson.common.object;

import java.sql.Time;

public class SqlTimeField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private Time              value;

	public SqlTimeField(String name, Time value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == SQLTIME_TYPE) {
			return value;
		}
		if (dataType == STRING_TYPE) {
			return XCOUtil.getTimeString(value);
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<G " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.getTimeString(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.getTimeString(this.value)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new SqlTimeField(name, new java.sql.Time(this.value.getTime()));
	}
}
