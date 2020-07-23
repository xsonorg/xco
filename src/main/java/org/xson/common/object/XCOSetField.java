package org.xson.common.object;

import java.util.HashSet;
import java.util.Set;

public class XCOSetField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private Set<XCO>          value;

	public XCOSetField(String name, Set<XCO> value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == XCO_SET_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<XS " + DataType.PROPERTY_K + "=\"" + this.name + "\">");
		for (XCO xco : value) {
			xco.toXMLString(null, builder);
		}
		builder.append("</XS>");
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		int i = 0;
		for (XCO xco : value) {
			if (i++ > 0) {
				builder.append(",");
			}
			builder.append(xco.toJSON(browserCompatible));
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		Set<XCO> set = new HashSet<XCO>();
		for (XCO xco : value) {
			set.add(xco.clone());
		}
		return new XCOSetField(name, set);
	}

}
