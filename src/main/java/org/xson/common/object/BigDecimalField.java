package org.xson.common.object;

import java.math.BigDecimal;

public class BigDecimalField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private BigDecimal			value;

	public BigDecimalField(String name, BigDecimal value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == BIGDICIMAL_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<M " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(this.value.toString());
		builder.append("\"/>");
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		// builder.append("\"").append(this.name).append("\"").append(":").append(this.value.toString());
		builder.append("\"").append(this.name).append("\"").append(":\"").append(this.value.toString()).append("\"");
	}

	@Override
	public IField cloneSelf() {
		return new BigDecimalField(name, new BigDecimal(value.toString()));
	}

}
