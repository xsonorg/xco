package org.xson.common.object;

import org.xson.core.WriterModel;
import org.xson.core.serializer.DefaultSerializer;

public class XCOSerializer extends DefaultSerializer {

	public final static XCOSerializer instance = new XCOSerializer();

	@Override
	public void write(Object target, WriterModel model) {
		model.appendCreateUserObject(target.getClass());
		XCO xco = (XCO) target;
		for (String key : xco.keysList()) {
			model.writeObject(key);
			model.writeObject(xco.getObjectValue(key));
		}
		model.writeEnd();
	}
}
