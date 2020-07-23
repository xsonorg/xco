package org.xson.common.object;

import java.util.HashSet;
import java.util.Set;

public class StringSetField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private Set<String>       value;

	public StringSetField(String name, Set<String> value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == STRING_SET_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<SS " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\">");
		for (String str : value) {
			builder.append("<S " + PROPERTY_V + "=\"" + XCOUtil.encodeTextForXML(str) + "\"/>");
		}
		builder.append("</SS>");
	}

	protected void setValue(Set<String> var) {
		this.value = var;
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		int i = 0;
		for (String str : value) {
			if (i++ > 0) {
				builder.append(",");
			}
			builder.append("\"");
			builder.append(XCOUtil.encodeTextForJSON(str, browserCompatible));
			builder.append("\"");
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		Set<String> set = new HashSet<String>();
		for (String str : value) {
			set.add(str);
		}
		return new StringSetField(name, set);
	}

}
