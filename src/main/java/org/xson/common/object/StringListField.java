package org.xson.common.object;

import java.util.ArrayList;
import java.util.List;

public class StringListField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private List<String>      value;

	public StringListField(String name, List<String> value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == STRING_LIST_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<SL " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\">");
		for (int i = 0, length = this.value.size(); i < length; i++) {
			builder.append("<S " + PROPERTY_V + "=\"" + XCOUtil.encodeTextForXML(this.value.get(i)) + "\"/>");
		}
		builder.append("</SL>");
	}

	protected void setValue(List<String> var) {
		this.value = var;
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.size(); i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append("\"");
			builder.append(XCOUtil.encodeTextForJSON(this.value.get(i), browserCompatible));
			builder.append("\"");
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		List<String> list = new ArrayList<String>();
		for (String str : value) {
			list.add(str);
		}
		return new StringListField(name, list);
	}

}
