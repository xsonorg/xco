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
		XCO xco = new XCO();
		xco.setStringValue("a", "av");
		xco.setStringValue("a", null);

		XCO xco1 = new XCO();
		xco1.setStringValue("b", "bv");

		XCO xco2 = new XCO();
		xco2.setStringValue("c", "cv");

		XCO[] array = new XCO[2];
		array[0] = new XCO();
		array[1] = new XCO();

		array[0].setIntegerValue("i", 1);
		array[1].setStringValue("x", "acc");

		Set<XCO> set = new HashSet<XCO>();
		set.add(array[0]);
		set.add(array[1]);

		List<XCO> list = new ArrayList<XCO>();
		list.add(array[0]);
		list.add(array[1]);

		xco.setXCOValue("xco1", xco1);
		xco.setXCOSetValue("set", set);

		xco1.setXCOValue("xco2", xco2);

		xco1.setXCOArrayValue("array", array);
		xco2.setXCOListValue("list", list);

		System.out.println(xco);
		System.out.println(xco.get("xco1.xco2.c"));

		System.out.println(xco.get("set"));
		System.out.println(xco.get("set[0]"));
		System.out.println(xco.get("set[1]"));

		System.out.println(xco.get("xco1.array[1].x"));
	}

	@Test
	public void test07() {
		XCO xco = new XCO();
		xco.setStringValue("a", "中国");
		xco.setStringValue("b", "日本");

		System.out.println(xco);

		XCO xco1 = new XCO();
		xco1.setStringValue("a", "英国");
		xco1.setStringValue("d", "美国");
		xco1.setStringValue("e", "韩国");

		System.out.println(xco1);

		// xco.append(xco1);
		// System.out.println(xco);

		xco1.append(xco);
		System.out.println(xco1);
	}

	@Test
	public void test08() {
		XCO xco = new XCO();
		xco.setStringValue("www.baidu.com", "中国");

		XCO xco1 = new XCO();
		xco1.setStringValue("name", "日本");

		xco.setXCOValue("x", xco1);

		System.out.println(xco);
		System.out.println(xco.get("www.baidu.com"));
		System.out.println(xco.get("x.name"));

		System.out.println(xco.get("wwww"));
	}

	@Test
	public void test09() {
		XCO xco = new XCO();
		xco.setStringValue("name", "日本");
		xco.setStringValue("name1", "日本");
		xco.setStringValue("name2", "日本");
		xco.setStringValue("name3", "日本");
		String[] filters = { "name*" };
		String xml = xco.toXMLString(filters);
		System.out.println(xml);
	}

	@Test
	public void testAll() {
		test01();
		test02();
		test03();
		test04();
		test05();
		test06();
		test07();
		test08();
		test09();
	}
}
