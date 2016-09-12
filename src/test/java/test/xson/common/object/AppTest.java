package test.xson.common.object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.xson.common.object.XCO;

public class AppTest {

	@Test
	public void test01() {
		XCO xco = new XCO();
		xco.setIntegerValue("a", 2);

		int[] intarray = { 1, 2, 3, 3333 };
		xco.setIntegerArrayValue("intarray", intarray);

		XCO xco2 = new XCO();
		xco2.setStringValue("b", "hello");

		xco.setXCOValue("xx", xco2);

		System.out.println(xco.toXMLString());
		String xml = xco.toXMLString();

		XCO xco1 = XCO.fromXML(xml);

		System.out.println(xco1.toXMLString());
	}

	@Test
	public void test02() {
		XCO xco = new XCO();

		XCO[] array = new XCO[2];
		array[0] = new XCO();
		array[1] = new XCO();

		array[0].setIntegerValue("i", 1);
		array[1].setStringValue("x", "b");

		xco.setXCOArrayValue("array", array);

		String xml = xco.toXMLString();
		System.out.println(xml);

		XCO xco1 = XCO.fromXML(xml);
		System.out.println(xco1.toXMLString());
	}

	@Test
	public void test03() {
		XCO xco = new XCO();

		XCO[] array = new XCO[2];
		array[0] = new XCO();
		array[1] = new XCO();

		array[0].setIntegerValue("i", 1);
		array[1].setStringValue("x", "b");

		List<XCO> list = new ArrayList<XCO>();
		list.add(array[0]);
		list.add(array[1]);

		// xco.setXCOArrayValue("array", array);
		xco.setXCOListValue("list", list);

		String xml = xco.toXMLString();
		System.out.println(xml);

		XCO xco1 = XCO.fromXML(xml);
		System.out.println(xco1.toXMLString());
	}

	@Test
	public void test04() {
		XCO xco = new XCO();

		XCO[] array = new XCO[2];
		array[0] = new XCO();
		array[1] = new XCO();

		array[0].setIntegerValue("i", 1);
		array[1].setStringValue("x", "a>bc\"cc");

		Set<XCO> set = new HashSet<XCO>();
		set.add(array[0]);
		set.add(array[1]);

		// xco.setXCOArrayValue("array", array);
		xco.setXCOSetValue("set", set);

		String xml = xco.toXMLString();
		System.out.println(xml);

		XCO xco1 = XCO.fromXML(xml);
		System.out.println(xco1.toXMLString());

	}

	@Test
	public void test05() {
		XCO xco = new XCO();

		xco.setStringValue("s", "a>bc\"cc");

		String xml = xco.toXMLString();
		System.out.println(xml);

		System.out.println(xco.toJSON());

		XCO xco1 = XCO.fromXML(xml);
		System.out.println(xco1.toXMLString());
		System.out.println(xco1.getStringValue("s"));
	}

	@Test
	public void test06() {
		// XCO xco = new XCO();
	}

}
