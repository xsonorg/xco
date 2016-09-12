package org.xson.common.object;

public class IntegerField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private int					value;

	public IntegerField(String name, int value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == INT_TYPE) {
			return value;
		}
		// 向上兼容, 向下会造成数据丢失
		if (dataType == LONG_TYPE) {
			return Long.valueOf(value);
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<I " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(this.value);
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(this.name).append("\"").append(":").append(this.value);
	}

	@Override
	public IField cloneSelf() {
		return new IntegerField(name, value);
	}
}
