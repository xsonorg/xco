package org.xson.common.object;

import org.xson.core.XsonReader;
import org.xson.core.XsonWriter;

public class XCOForXSON {

	public static Class<?> getXCOClass() {
		return XCO.class;
	}

	public static XsonWriter getSerializer() {
		return new XCOSerializer();
	}

	public static XsonReader getDeserializer() {
		return new XCODeserializer();
	}
}
