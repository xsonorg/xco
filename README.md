# XCO

------

### 1. XCO简介

XCO(XSON common object)是一种通用的数据对象, 底层采用一种类似Map的数据结构进行数据的存储访问，
能够方便的以XML和Byte[]的方式对数据对象进行序列化和反序列化，适合同构、异构系统之间的数据传输和交换。

### 2. XCO生态圈

![XCO生态圈](http://www.xson.org/project/xco/1.0.2/images/xco-ecology.png)

1. xco-java：XCO Java版本；
2. xco-js：XCO JavaScript版本，具体请参考<http://xson.org/project/xco-js/>
3. xco-c	：XCO C语言版本；

### 3. 支持的数据类型

01. 8种基本类型(byte, boolean, short, int, long, float, double, char)和其包装类型
02. 8种基本类型数组
03. String
04. String数组
05. String集合
06. java.util.Date
07. java.sql.Date
08. java.sql.Time
09. java.sql.Timestamp
10. BigInteger
11. BigDecimal
12. XCO
13. XCO数组
14. XCO集合

### 4. 版本maven使用

当前最新版本：1.0.5

> maven中使用

	<dependency>
		<groupId>org.xson</groupId>
		<artifactId>common-object</artifactId>
		<version>1.0.5</version>
	</dependency>

### 5. 更新说明

> 1.0.5

1. 增加默认值的支持
2. 增加类型兼容模式的支持
3. 增加链式写法的支持
4. 修正toJSON方法
5. 

> 1.0.4

1. 调整取值策略，先整体取值，后OGNL取值
2. 增加序列化时的字段过滤

> 1.0.3版本

1. 增加append方法

> 1.0.2版本

1. 支持Ognl表达式取值
2. 增加getValue相关方法
3. 增加remove方法
4. 增加byte[]相关的序列化和反序列化操作
5. 增加getData方法

### 6. 常用方法

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

		public byte[] toBytes() {
			把XCO对象以byte[]方式进行序列化

		public byte[] toBytes(int offsetLength) 
			把XCO对象以byte[]方式进行序列化，并保留偏移内容

		public static XCO fromBytes(byte[] buffer) 
			从byte[]反序列化为XCO对象
			
		public static XCO fromBytes(byte[] buffer, int offsetLength) 
			从byte[]的指定偏移长度开始，反序列化为XCO对象

### 7. 整合XSON

XCO对象的`toBytes`和`fromBytes`方法需要`XSON`框架支持，具体的使用操作如下：

> a. 添加Maven依赖

	<dependency>
	    <groupId>org.xson</groupId>
	    <artifactId>xson</artifactId>
	    <version>1.0.2</version>
	</dependency>

> b. 编辑xson.properties配置文件

	# Support for XCO
	xco=true

**提示** 关于`XSON`具体可参考<http://xson.org/project/xson/1.0.2/>

### 7. 使用示例

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

		// 序列化为XML字符串
		String xml = xco.toXMLString();
		// 从XML字符串反序列化
		XCO newXco = XCO.fromXML(xml);

		// 序列化为byte[]
		byte[] buffer = xco.toBytes();
		// 从byte[]反序列化
		XCO newXco1 = XCO.fromBytes(buffer);

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

### 8. XML表示

上面使用示例中的XCO对象`xco`的XML表示如下：

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
		
		
### 9. 类图

![XCO设计图](http://www.xson.org/project/xco/1.0.2/images/xco.png)

### 10. 官方网站

<http://xson.org/>

### 11. 沟通交流

QQ群：518522232**（请备注关注的项目）**

邮箱：xson_org@126.com
