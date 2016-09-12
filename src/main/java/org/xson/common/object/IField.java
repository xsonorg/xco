package org.xson.common.object;

public interface IField extends DataType {

	Object getValue();

	Object getValue(int dataType);

	IField cloneSelf();

	void toXMLString(StringBuilder builder);

	void toJSONString(StringBuilder builder);
}
