# XCO

------

### 1. XCO简介

XCO(XSON common object)是一种通用的数据对象, 底层采用一种类似Map的数据结构进行数据的存储访问，能够方便的以XML方式对数据对象进行序列化和反序列化，适合同构、异构系统之间的数据传输和交换。

### 2. 支持的数据类型

	a. 8种基本类型(byte, boolean, short, int, long, float, double, char)
	b. 8种基本类型数组
	e. String, String数组, String集合
	c. Date, sql.Date, sql.Time
	d. BigInteger, BigDecimal
	e. XCO, XCO数组, XCO集合

### 3. XML格式

	<?xml version="1.0" encoding="UTF-8"?>
	<X>
		<B K="byteVal" V="3"/>
		<O K="booleanVal" V="true"/>
		<H K="shortVal" V="5"/>
		<I K="intVal" V="2"/>
		<L K="longVal" V="2"/>
		<F K="floatVal" V="2.0"/>
		<D K="doubleVal" V="-0.3"/>
		<C K="charVal" V="x"/>
		<S K="stringVal" V="hello world"/>
		<A K="dateTimeVal" V="2016-09-02 16:58:25"/>
		<E K="dateVal" V="2016-09-02"/>
		<G K="TimeVal" V="16:58:25"/>
		<K K="bigIntegerVal" V="1380000"/>
		<M K="bigDecimal" V="1380000.9999"/>
		<X K="xcoVal"/>
		<IA K="intArray" V="1,3,5,8"/>
		<SA K="stringArray">
			<S V="aa"/>
			<S V="bb"/>
			<S V="cc"/>
		</SA>
		<SL K="stringList"/>
		<SS K="stringSet"/>
	</X>

> 说明

	a. 以此为例：<H K="shortVal" V="5"/>
		H: 数据类型标识，当前标示short类型
		K: key
		V: 具体数值
	b. 数据类型标识说明
		B: byte
		H: short
		I: int
		L: Long
		F: float
		D: double
		C: char
		O: boolean
		S: String
		X: xco
		A: date
		E: sql.date
		G: sql.time
		J: sql.timestamp
		K: bigInteger
		M: bigDecimal
		..
		其他详见：org.xson.common.object.DataType
		
### 4. 常用方法

> a. 赋值

		public final void setIntegerValue(String field, int var)
			设置一个int类型的值, field为key
		public final void setStringValue(String field, String var)
			设置一个String类型的值, field为key
		//setXxx

> b. 取值

		public final int getIntegerValue(String field)
			获取一个int类型的值, field为key
		public final String getStringValue(String field)
			获取一个String类型的值, field为key
		//getXxx

> c. 序列化

		public String toXMLString()	
			把XCO对象以XML方式进行序列化
		public static XCO fromXML(String xml)
			从一个XML字符串反序列化为XCO对象
		public String toJSON()
			把XCO对象以JSON方式进行序列化

### 5. 使用示例

		XCO xco = new XCO();

		// 设置基本类型
		xco.setByteValue("byteVal", (byte) 3);
		xco.setBooleanValue("booleanVal", true);
		xco.setShortValue("shortVal", (short) 5);
		xco.setIntegerValue("intVal", 2);
		xco.setLongValue("longVal", 2L);
		xco.setFloatValue("floatVal", 2.0F);
		xco.setDoubleValue("doubleVal", -0.3D);
		xco.setCharValue("charVal", 'x');

		// 设置对象类型
		xco.setStringValue("stringVal", "hello world");
		xco.setDateTimeValue("dateTimeVal", new java.util.Date());
		xco.setDateValue("dateVal", new java.sql.Date(System.currentTimeMillis()));
		xco.setTimeValue("TimeVal", new java.sql.Time(System.currentTimeMillis()));
		xco.setBigIntegerValue("bigIntegerVal", new BigInteger("1380000"));
		xco.setBigDecimalValue("bigDecimal", new BigDecimal("1380000.9999"));
		xco.setXCOValue("xcoVal", new XCO());

		// 设置数组
		xco.setIntegerArrayValue("intArray", new int[] { 1, 3, 5, 8 });
		xco.setStringArrayValue("stringArray", new String[] { "aa", "bb", "cc" });

		// 设置集合
		List<String> list = new ArrayList<String>();
		xco.setStringListValue("stringList", list);
		Set<String> set = new TreeSet<String>();
		xco.setStringSetValue("stringSet", set);

		// XML序列化
		String xml = xco.toXMLString();
		// 反序列化
		XCO newXco = XCO.fromXML(xml);

		// 取值
		byte byteVal = xco.getByteValue("byteVal");
		boolean booleanVal = xco.getBooleanValue("booleanVal");
		short shortVal = xco.getShortValue("shortVal");
		int intVal = xco.getIntegerValue("intVal");
		long longVal = xco.getLongValue("longVal");
		float floatVal = xco.getFloatValue("floatVal");
		double doubleVal = xco.getDoubleValue("doubleVal");
		String stringVal = xco.getStringValue("stringVal");
		XCO xcoVal = xco.getXCOValue("xcoVal");	
		
### 6. 设计图

![XCO设计图](https://github.com/xsonorg/imagedoc/blob/master/img/xco.png)


### 7. Maven引用

	<dependency>
		<groupId>org.xson</groupId>
		<artifactId>common-object</artifactId>
		<version>1.0.0</version>
	</dependency>

### 8. 新版本说明

> 最新版本：1.0.1

1. 支持Ognl表达式取值

2. 增加getValue方法

3. 增加remove方法