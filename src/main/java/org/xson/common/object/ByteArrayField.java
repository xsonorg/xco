package org.xson.common.object;

public class ByteArrayField implements IField {

	private static final long serialVersionUID = 4848636595224033221L;

	protected String          name;

	private byte[]            value;

	public ByteArrayField(String name, byte[] value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public Object getValue(int dataType) {
		if (dataType == BYTE_ARRAY_TYPE) {
			return value;
		}
		throw new XCOException("Type mismatch for field: " + this.name);
	}

	@Override
	public void toXMLString(StringBuilder builder) {
		builder.append("<BA " + PROPERTY_K + "=\"");
		builder.append(this.name);
		builder.append("\" " + PROPERTY_V + "=\"");
		builder.append(arrayToString(this.value));
		builder.append("\"/>");
	}

	private String arrayToString(byte[] a) {
		StringBuilder b = new StringBuilder();
		for (int i = 0, length = a.length; i < length; i++) {
			b.append(byteToHexString(a[i]));
		}
		return b.toString();
	}

	public static String byteToHexString(byte b) {
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex.toUpperCase();
	}

	protected void setValue(String var) {
		if (null == var) {
			return;
		}
		char[] src = var.toCharArray();
		value = new byte[src.length / 2];
		int j = 0;
		for (int i = 0; i < src.length; i = i + 2) {
			value[j++] = Integer.valueOf("" + src[i] + src[i + 1], 16).byteValue();
		}
	}

	@Override
	public void toJSONString(StringBuilder builder, boolean browserCompatible) {
		builder.append("\"").append(this.name).append("\"").append(":").append("[");
		for (int i = 0; i < this.value.length; i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append(this.value[i]);
		}
		builder.append("]");
	}

	@Override
	public IField cloneSelf() {
		return new ByteArrayField(name, value.clone());
	}

}
