package org.xson.common.object;

import java.util.ArrayList;
import java.util.List;

public class XCOListField implements IField {

	private static final long	serialVersionUID	= 4848636595224033221L;

	protected String			name;

	private List<XCO>			value;

	public XCOListField(String name, List<XCO> value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == XCO_LIST_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<XL " + DataType.PROPERTY_K + "=\"" + this.name + "\">");
		for (int i = 0, length = value.size(); i < length; i++) {
			value.get(i).toXMLString(null, builder);
		}
		builder.append("</XL>");
	}

	@Override
	public void toJSONString(StringBuilder builder) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.size(); i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append(this.value.get(i).toJSON());
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		List<XCO> list = new ArrayList<XCO>();
		for (XCO xco : value) {
			list.add(xco.clone());
		}
		return new XCOListField(name, list);
	}
}
