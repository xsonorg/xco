package org.xson.common.object;

import org.xson.core.ReaderModel;
import org.xson.core.XsonReader;

public class XCODeserializer implements XsonReader {

	@Override
	public Object read(ReaderModel model) {
		model.incrementIndex(1);// fix bug
		XCO returnValue = new XCO();
		model.appendObject(returnValue);
		readXCO(model, returnValue);
		model.endObject();
		return returnValue;
	}

	private void readXCO(ReaderModel model, XCO xco) {
		while (!model.isEnd() && model.isBound()) {
			Object key = model.getObject();
			Object value = model.getObject();
			xco.setObjectValue((String) key, value);
		}
	}
}