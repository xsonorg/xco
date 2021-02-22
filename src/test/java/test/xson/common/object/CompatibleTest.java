package test.xson.common.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Test;
import org.xson.common.object.XCO;

public class CompatibleTest {

	public static final String ANSI_RESET  = "u001B[0m";
	public static final String ANSI_BLACK  = "u001B[30m";
	public static final String ANSI_RED    = "u001B[31m";
	public static final String ANSI_GREEN  = "u001B[32m";
	public static final String ANSI_YELLOW = "u001B[33m";
	public static final String ANSI_BLUE   = "u001B[34m";
	public static final String ANSI_PURPLE = "u001B[35m";
	public static final String ANSI_CYAN   = "u001B[36m";
	public static final String ANSI_WHITE  = "u001B[37m";

	public static void printColor() {
		// 背景颜色代号(41-46)
		// 前景色代号(31-36)
		// 前景色代号和背景色代号可选，就是或可以写，也可以不写
		// 数字+m：1加粗；3斜体；4下划线
		// 格式：System.out.println("\33[前景色代号;背景色代号;数字m");
		Random backgroundRandom = new Random();
		Random fontRandom       = new Random();
		for (int i = 1; i <= 50; i++) {
			int font       = fontRandom.nextInt(6) + 31;
			int background = backgroundRandom.nextInt(6) + 41;
			System.out.format("前景色是%d,背景色是%d------\33[%d;%d;4m我是博主%n", font, background, font, background);
		}
	}

	private void printOut(String str) {
		System.out.println(str);
		System.out.flush();
	}

	private void printErr(String str) {
		System.err.println(str);
	}

	private void print(XCO xco1, Map<String, Boolean> flagMap) {
		int max = 55;

		printOut("\n\n");

		if (flagMap.containsKey("byte")) {
			// byte
			printOut("\n=========================byte==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getByteValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("short")) {
			// short
			printOut("\n=========================short==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getShortValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("int")) {
			// int 
			printOut("\n=========================int==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getIntegerValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("long")) {
			// long
			printOut("\n=========================long==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getLongValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("float")) {
			// float
			printOut("\n=========================float==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getFloatValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("double")) {
			// double
			printOut("\n=========================double==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getDoubleValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		// char 

		if (flagMap.containsKey("boolean")) {
			// boolean
			printOut("\n=========================boolean==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getBooleanValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("String")) {
			// String
			printOut("\n=========================String==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getStringValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("bigInteger")) {
			// bigInteger
			printOut("\n=========================bigInteger==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getBigIntegerValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("BigDecimal")) {
			// BigDecimal
			printOut("\n=========================BigDecimal==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getBigDecimalValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("date")) {
			// date
			printOut("\n=========================date==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getDateTimeValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("sql.date")) {
			// sql.date
			printOut("\n=========================sql.date==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getDateValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("sql.time")) {
			// sql.time
			printOut("\n=========================sql.time==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getTimeValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

		if (flagMap.containsKey("sql.Timestamp")) {
			// sql.Timestamp
			printOut("\n=========================sql.Timestamp==========================");
			for (int i = 1; i < max; i++) {
				String key    = "v" + i;
				Object value1 = xco1.get(key);
				try {
					printOut("OK: KEY[" + key + "]\t V:[" + value1.toString() + "], G[" + xco1.getTimestampValue(key) + "]");
				} catch (Exception e) {
					printErr("ER: KEY[" + key + "]\t V:[" + value1.toString() + "], R:" + e.getMessage());
				}
			}
		}

	}

	@Test
	public void test12() {
		//		printOut(new Date().toString());
		printOut("\n=========================兼容性==========================");
		XCO xco = new XCO(true);
		// byte
		xco.setByteValue("v1", Byte.MIN_VALUE);
		xco.setByteValue("v2", Byte.MAX_VALUE);
		xco.setByteValue("v3", (byte) 10);
		// short
		xco.setShortValue("v4", Short.MIN_VALUE);
		xco.setShortValue("v5", Short.MAX_VALUE);
		xco.setShortValue("v6", (short) 11);
		// int 
		xco.setIntegerValue("v7", Integer.MIN_VALUE);
		xco.setIntegerValue("v8", Integer.MAX_VALUE);
		xco.setIntegerValue("v9", 12);
		// long
		xco.setLongValue("v10", Long.MIN_VALUE);
		xco.setLongValue("v11", Long.MAX_VALUE);
		xco.setLongValue("v12", 13L);
		xco.setLongValue("v13", new Date().getTime());
		// float
		xco.setFloatValue("v14", Float.MIN_VALUE);
		xco.setFloatValue("v15", Float.MAX_VALUE);
		xco.setFloatValue("v16", 14F);

		// double
		xco.setDoubleValue("v17", Double.MIN_VALUE);
		xco.setDoubleValue("v18", Double.MAX_VALUE);
		xco.setDoubleValue("v19", 15D);

		// char

		// boolean
		xco.setBooleanValue("v20", true);
		xco.setBooleanValue("v21", false);

		// String
		xco.setStringValue("v22", "abc");
		xco.setStringValue("v23", Byte.MAX_VALUE + "");
		xco.setStringValue("v24", Short.MAX_VALUE + "");
		xco.setStringValue("v25", Integer.MAX_VALUE + "");
		xco.setStringValue("v26", Long.MAX_VALUE + "");
		xco.setStringValue("v27", Float.MAX_VALUE + "");
		xco.setStringValue("v28", Double.MAX_VALUE + "");
		xco.setStringValue("v29", "true");
		xco.setStringValue("v30", "5.666667");
		xco.setStringValue("v31", "2019-05-06 16:09:28");
		xco.setStringValue("v53", "2019-05-06");
		xco.setStringValue("v54", "16:09:28");
		xco.setStringValue("v32", new Date().getTime() + "");

		// bigInteger
		xco.setBigIntegerValue("v33", new BigInteger("123"));
		xco.setBigIntegerValue("v34", new BigInteger(Byte.MAX_VALUE + ""));
		xco.setBigIntegerValue("v35", new BigInteger(Short.MAX_VALUE + ""));
		xco.setBigIntegerValue("v36", new BigInteger(Integer.MAX_VALUE + ""));
		xco.setBigIntegerValue("v37", new BigInteger(Long.MAX_VALUE + ""));
		xco.setBigIntegerValue("v38", new BigInteger((Long.MAX_VALUE - 1) + ""));
		xco.setBigIntegerValue("v39", new BigInteger((Long.MAX_VALUE - 2) + ""));
		xco.setBigIntegerValue("v40", new BigInteger((Long.MAX_VALUE - 3) + ""));

		// BigDecimal
		xco.setBigDecimalValue("v41", new BigDecimal("123"));
		xco.setBigDecimalValue("v42", new BigDecimal(Byte.MAX_VALUE + ""));
		xco.setBigDecimalValue("v43", new BigDecimal(Short.MAX_VALUE + ""));
		xco.setBigDecimalValue("v44", new BigDecimal(Integer.MAX_VALUE + ""));
		xco.setBigDecimalValue("v45", new BigDecimal(Long.MAX_VALUE + ""));
		xco.setBigDecimalValue("v46", new BigDecimal(Float.MAX_VALUE + ""));
		xco.setBigDecimalValue("v47", new BigDecimal(Double.MAX_VALUE + ""));
		xco.setBigDecimalValue("v48", new BigDecimal("5.666667"));

		long t = new Date().getTime();

		// date
		xco.setDateTimeValue("v49", new Date());

		// sql.date
		xco.setDateValue("v50", new java.sql.Date(t));

		// sql.time
		xco.setTimeValue("v51", new java.sql.Time(t));

		// sql.Timestamp
		xco.setTimestampValue("v52", new java.sql.Timestamp(t));

		String xml = xco.toXMLString();
		printOut(xml);

		XCO xco1 = XCO.fromXML(xml, true);
		printOut(xco1.toString());

		Map<String, Boolean> flagMap = new HashMap<String, Boolean>();
		flagMap.put("byte", true);
		flagMap.put("short", true);
		flagMap.put("int", true);
		flagMap.put("long", true);
		flagMap.put("float", true);
		flagMap.put("double", true);
		//		flagMap.put("boolean", true);
		//		flagMap.put("String", true);
		//		flagMap.put("bigInteger", true);
		//		flagMap.put("BigDecimal", true);
		//		flagMap.put("date", true);
		//		flagMap.put("sql.date", true);
		//		flagMap.put("sql.time", true);
		//		flagMap.put("sql.Timestamp", true);

		print(xco1, flagMap);
	}

	@Test
	public void testAll() {
		test12();
		//		printColor();
	}
}
