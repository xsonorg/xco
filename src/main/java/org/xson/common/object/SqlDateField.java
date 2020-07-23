package org.xson.common.object;

public class SqlDateField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private java.sql.Date     value;

	public SqlDateField(String name, java.sql.Date value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == SQLDATE_TYPE) {
			return value;
		}
		if (dataType == STRING_TYPE) {
			return XCOUtil.getDateString(value);
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<E " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(XCOUtil.getDateString(this.value));
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":\"").append(XCOUtil.getDateString(this.value)).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new SqlDateField(name, new java.sql.Date(this.value.getTime()));
	}
}
