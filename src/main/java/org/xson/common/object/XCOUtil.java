package org.xson.common.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XCOUtil {

	private final static char[] replaceChars = new char[93];

	static {
		replaceChars['\0'] = '0';
		replaceChars['\1'] = '1';
		replaceChars['\2'] = '2';
		replaceChars['\3'] = '3';
		replaceChars['\4'] = '4';
		replaceChars['\5'] = '5';
		replaceChars['\6'] = '6';
		replaceChars['\7'] = '7';
		replaceChars['\b'] = 'b'; // 8
		replaceChars['\t'] = 't'; // 9
		replaceChars['\n'] = 'n'; // 10
		replaceChars['\u000B'] = 'v'; // 11
		replaceChars['\f'] = 'f'; // 12
		replaceChars['\r'] = 'r'; // 13
		replaceChars['\"'] = '"'; // 34
		replaceChars['\''] = '\''; // 39
		replaceChars['/'] = '/'; // 47
		replaceChars['\\'] = '\\'; // 92
	}

	//	public static String encodeTextForJSON(String text) {
	//		if (text == null) {
	//			return null;
	//		}
	//		text = text.replace("\\", "\\\\");
	//		text = text.replaceAll("\"", "\\\\\"");
	//		return text;
	//	}

	//	public static void main(String[] args) {
	//		//		char x = '\n';
	//		//		char x = '\n';
	//		//		char x = '>';
	//		//		int  y = x;
	//		//		System.out.println(y);
	//		//		System.out.println(Integer.toHexString(y));
	//		//		System.out.println("&#x" + Integer.toHexString(y));
	//		char[] chs = { '&', '>', '<', '\'', '"', '\r', '\n' };
	//		for (int i = 0; i < chs.length; i++) {
	//			char x = chs[i];
	//			int  y = x;
	//			//			System.out.println(y);
	//			//			System.out.println(Integer.toHexString(y));
	//			System.out.println("&#x" + Integer.toHexString(y));
	//		}
	//	}

	public static String encodeTextForJSON(String text, boolean browserCompatible) {
		if (text == null) {
			return null;
		}

		int           length  = text.length();
		StringBuilder builder = new StringBuilder();
		if (browserCompatible) {
			for (int i = 0; i < length; i++) {
				char ch = text.charAt(i);
				if (ch == '\b' //
						|| ch == '\f' //
						|| ch == '\n' //
						|| ch == '\r' //
						|| ch == '\t' //
						|| ch == '"' //
						|| ch == '/' //
						|| ch == '\\') {
					builder.append('\\');
					builder.append(replaceChars[(int) ch]);
					continue;
				}
				builder.append(ch);
			}
		} else {
			for (int i = 0; i < length; i++) {
				char ch = text.charAt(i);
				if (ch == '"' || ch == '\\') {
					builder.append('\\');
					builder.append(replaceChars[(int) ch]);
					continue;
				}
				builder.append(ch);
			}
		}
		return builder.toString();
	}

	public static String encodeTextForJSON(char[] text, boolean browserCompatible) {
		if (text == null) {
			return null;
		}

		int           length  = text.length;
		StringBuilder builder = new StringBuilder();
		if (browserCompatible) {
			for (int i = 0; i < length; i++) {
				char ch = text[i];
				if (ch == '\b' //
						|| ch == '\f' //
						|| ch == '\n' //
						|| ch == '\r' //
						|| ch == '\t' //
						|| ch == '"' //
						|| ch == '/' //
						|| ch == '\\') {
					builder.append('\\');
					builder.append(replaceChars[(int) ch]);
					continue;
				}
				builder.append(ch);
			}
		} else {
			for (int i = 0; i < length; i++) {
				char ch = text[i];
				if (ch == '"' || ch == '\\') {
					builder.append('\\');
					builder.append(replaceChars[(int) ch]);
					continue;
				}
				builder.append(ch);
			}
		}
		return builder.toString();
	}

	public static String encodeTextForXML(String text) {
		if (text == null) {
			return null;
		}
		int           length  = text.length();
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			char key = text.charAt(i);
			switch (key) {
			case '&':
				builder.append("&amp;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '<':
				builder.append("&lt;");
				break;
			case '\'':
				builder.append("&apos;");
				break;
			case '"':
				builder.append("&quot;");
				break;
			case '\r':
				builder.append("&#xd;");
				break;
			case '\n':
				builder.append("&#xa;");
				break;
			default:
				builder.append(key);
				break;
			}
		}
		return builder.toString();
	}

	public static String encodeTextForXML(char[] src) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0, length = src.length; i < length; i++) {
			char key = src[i];
			switch (key) {
			case '&':
				builder.append("&amp;");
				break;
			case '>':
				builder.append("&gt;");
				break;
			case '<':
				builder.append("&lt;");
				break;
			case '\'':
				builder.append("&apos;");
				break;
			case '"':
				builder.append("&quot;");
				break;
			case '\r':
				builder.append("&#xd;");
				break;
			case '\n':
				builder.append("&#xa;");
				break;
			default:
				builder.append(key);
				break;
			}
		}
		return builder.toString();
	}

	public static String encodeTextForXML(char src) {
		switch (src) {
		case '&':
			return "&amp;";
		case '>':
			return "&gt;";
		case '<':
			return "&lt;";
		case '\'':
			return "&apos;";
		case '"':
			return "&quot;";
		case '\r':
			return "&#xd;";
		case '\n':
			return "&#xa;";
		default:
			return src + "";
		}
	}

	//	public static String encodeTextForXML(String text) {
	//		if (text == null) {
	//			return null;
	//		}
	//		char[]        src     = text.toCharArray();
	//		StringBuilder builder = new StringBuilder();
	//		for (int i = 0, length = src.length; i < length; i++) {
	//			char key = src[i];
	//			switch (key) {
	//			case '&':
	//				builder.append("&amp;");
	//				break;
	//			case '>':
	//				builder.append("&gt;");
	//				break;
	//			case '<':
	//				builder.append("&lt;");
	//				break;
	//			case '\'':
	//				builder.append("&apos;");
	//				break;
	//			case '"':
	//				builder.append("&quot;");
	//				break;
	//			case '\r':
	//				break;
	//			case '\n':
	//				builder.append("&#xa;");
	//				break;
	//			default:
	//				builder.append(key);
	//				break;
	//			}
	//		}
	//		return builder.toString();
	//	}

	//	public static String encodeTextForXML(char[] src) {
	//		StringBuilder builder = new StringBuilder();
	//		for (int i = 0, length = src.length; i < length; i++) {
	//			char key = src[i];
	//			switch (key) {
	//			case '&':
	//				builder.append("&amp;");
	//				break;
	//			case '>':
	//				builder.append("&gt;");
	//				break;
	//			case '<':
	//				builder.append("&lt;");
	//				break;
	//			case '\'':
	//				builder.append("&apos;");
	//				break;
	//			case '"':
	//				builder.append("&quot;");
	//				break;
	//			// case '\r':
	//			// break;
	//			// case '\n':
	//			// builder.append("&#xa;");
	//			// break;
	//			default:
	//				builder.append(key);
	//				break;
	//			}
	//		}
	//		return builder.toString();
	//	}

	//	public static String encodeTextForXML(char src) {
	//		switch (src) {
	//		case '&':
	//			return "&amp;";
	//		case '>':
	//			return "&gt;";
	//		case '<':
	//			return "&lt;";
	//		case '\'':
	//			return "&apos;";
	//		case '"':
	//			return "&quot;";
	//		default:
	//			return src + "";
	//		}
	//	}

	public static String getDateTimeString(java.util.Date date) {
		return getDateTimeString(date, DataType.DATETIME_FORMAT);
	}

	public static String getDateTimeString(java.util.Date date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	public static String getDateString(java.sql.Date date) {
		return getDateString(date, DataType.DATE_FORMAT);
	}

	public static String getDateString(java.sql.Date date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	public static String getTimeString(java.sql.Time date) {
		return getTimeString(date, DataType.TIME_FORMAT);
	}

	public static String getTimeString(java.sql.Time date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	public static String getTimestampString(java.sql.Timestamp date) {
		return getTimestampString(date, DataType.DATETIME_FORMAT);
	}

	public static String getTimestampString(java.sql.Timestamp date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	public static java.util.Date parseDateTime(String date) {
		return parseDateTime(date, DataType.DATETIME_FORMAT);
	}

	public static java.util.Date parseDateTime(String date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	public static java.sql.Date parseDate(String date) {
		return parseDate(date, DataType.DATE_FORMAT);
	}

	public static java.sql.Date parseDate(String date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return new java.sql.Date(fmt.parse(date).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	public static java.sql.Time parseTime(String date) {
		return parseTime(date, DataType.TIME_FORMAT);
	}

	public static java.sql.Time parseTime(String date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return new java.sql.Time(fmt.parse(date).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	public static java.sql.Timestamp parseTimestamp(String date) {
		return parseTimestamp(date, DataType.DATETIME_FORMAT);
	}

	public static java.sql.Timestamp parseTimestamp(String date, String pattern) {
		DateFormat fmt = new SimpleDateFormat(pattern);
		try {
			return new java.sql.Timestamp(fmt.parse(date).getTime());
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date or Time String is invalid.");
		}
	}

	private static boolean simpleMatch(String pattern, String str) {
		if (pattern == null || str == null) {
			return false;
		}
		int firstIndex = pattern.indexOf('*');
		if (firstIndex == -1) {
			return pattern.equals(str);
		}
		if (firstIndex == 0) {
			if (pattern.length() == 1) {
				return true;
			}
			int nextIndex = pattern.indexOf('*', firstIndex + 1);
			if (nextIndex == -1) {
				return str.endsWith(pattern.substring(1));
			}
			String part      = pattern.substring(1, nextIndex);
			int    partIndex = str.indexOf(part);
			while (partIndex != -1) {
				if (simpleMatch(pattern.substring(nextIndex), str.substring(partIndex + part.length()))) {
					return true;
				}
				partIndex = str.indexOf(part, partIndex + 1);
			}
			return false;
		}
		return (str.length() >= firstIndex && pattern.substring(0, firstIndex).equals(str.substring(0, firstIndex))
				&& simpleMatch(pattern.substring(firstIndex), str.substring(firstIndex)));
	}

	public static boolean simpleMatch(String[] patterns, String str) {
		if (patterns != null) {
			for (int i = 0; i < patterns.length; i++) {
				if (simpleMatch(patterns[i], str)) {
					return true;
				}
			}
		}
		return false;
	}

	//////////////////////////////////////////////////////////////////////////////////////////

	private static short toShortExact(int value) {
		if ((short) value != value) {
			throw new ArithmeticException();
		}
		return (short) value;
	}

	private static short toShortExact(long value) {
		if ((short) value != value) {
			throw new ArithmeticException();
		}
		return (short) value;
	}

	private static int toIntExact(long value) {
		if ((int) value != value) {
			throw new ArithmeticException();
		}
		return (int) value;
	}

	private static float toFloatExact(int value) {
		if ((float) value != (double) value) {
			throw new ArithmeticException();
		}
		return (float) value;
	}

	private static float toFloatExact(long value) {
		int val = toIntExact(value);
		return toFloatExact(val);
	}

	private static float toFloatExact(double value) {
		if ((float) value != value) {
			throw new ArithmeticException();
		}
		return (float) value;
	}

	private static double toDoubleExact(long value) {
		if ((long) ((double) value) != value) {
			throw new ArithmeticException();
		}
		return (double) value;
	}

	/**
	 * 支持[string]
	 */
	public static boolean castToBoolean(Object src) {
		Class<?> clazz = src.getClass();
		if (Boolean.class == clazz) {
			return ((Boolean) src).booleanValue();
		}
		if (String.class == clazz) {
			return Boolean.parseBoolean((String) src);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to boolean");
	}

	/**
	 * 支持[byte, int, long, string, bigint]
	 */
	public static short castToShort(Object src) {
		Class<?> clazz = src.getClass();
		if (Short.class == clazz) {
			return ((Short) src).shortValue();
		}
		if (Byte.class == clazz) {
			return ((Byte) src).shortValue();
		} else if (Integer.class == clazz) {
			return toShortExact(((Integer) src).intValue());
		} else if (Long.class == clazz) {
			return toShortExact(((Long) src).longValue());
		} else if (String.class == clazz) {
			return Short.parseShort((String) src);
		} else if (BigInteger.class == clazz) {
			return Short.parseShort(((BigInteger) src).toString());
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to short");
	}

	/**
	 * 支持[byte, short, long, string, bigint]
	 */
	public static int castToInt(Object src) {
		Class<?> clazz = src.getClass();
		if (Integer.class == clazz) {
			return ((Integer) src).intValue();
		}
		if (Byte.class == clazz) {
			return ((Byte) src).intValue();
		} else if (Short.class == clazz) {
			return ((Short) src).intValue();
		} else if (Long.class == clazz) {
			return toIntExact(((Long) src).longValue());
		} else if (String.class == clazz) {
			return Integer.parseInt((String) src);
		} else if (BigInteger.class == clazz) {
			return Integer.parseInt(((BigInteger) src).toString());
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to int");
	}

	/**
	 * 支持[byte, short, int, string, bigint]
	 * 
	 * 支持[DATE, SQLDATE, SQLTIME, TIMESTAMP]
	 */
	public static long castToLong(Object src) {
		Class<?> clazz = src.getClass();
		if (Long.class == clazz) {
			return ((Long) src).longValue();
		}
		if (Byte.class == clazz) {
			return ((Byte) src).longValue();
		} else if (Short.class == clazz) {
			return ((Short) src).longValue();
		} else if (Integer.class == clazz) {
			return ((Integer) src).longValue();
		} else if (String.class == clazz) {
			return Long.parseLong((String) src);
		} else if (BigInteger.class == clazz) {
			return Long.parseLong(((BigInteger) src).toString());
		} else if (Date.class == clazz) {
			return ((Date) src).getTime();
		} else if (java.sql.Date.class == clazz) {
			return ((java.sql.Date) src).getTime();
		} else if (java.sql.Time.class == clazz) {
			return ((java.sql.Time) src).getTime();
		} else if (java.sql.Timestamp.class == clazz) {
			return ((java.sql.Timestamp) src).getTime();
		}

		throw new NumberFormatException("unsupported types '" + clazz + "' to long");
	}

	public static float castToFloat(Object src) {
		Class<?> clazz = src.getClass();
		if (Float.class == clazz) {
			return ((Float) src).floatValue();
		}
		if (Byte.class == clazz) {
			return ((Byte) src).floatValue();
		} else if (Short.class == clazz) {
			return ((Short) src).floatValue();
		} else if (Integer.class == clazz) {
			return toFloatExact(((Integer) src).intValue());
		} else if (Long.class == clazz) {
			return toFloatExact(((Long) src).longValue());
		} else if (Double.class == clazz) {
			return toFloatExact(((Double) src).doubleValue());
		} else if (String.class == clazz) {
			return Float.parseFloat((String) src);
		} else if (BigDecimal.class == clazz) {
			return Float.parseFloat(((BigDecimal) src).toString());
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to float");
	}

	public static double castToDouble(Object src) {
		Class<?> clazz = src.getClass();
		if (Double.class == clazz) {
			return ((Double) src).doubleValue();
		}
		if (Byte.class == clazz) {
			return ((Byte) src).doubleValue();
		} else if (Short.class == clazz) {
			return ((Short) src).doubleValue();
		} else if (Integer.class == clazz) {
			return ((Integer) src).doubleValue();
		} else if (Long.class == clazz) {
			return toDoubleExact(((Long) src).longValue());
		} else if (Float.class == clazz) {
			return ((Float) src).doubleValue();
		} else if (String.class == clazz) {
			return Double.parseDouble((String) src);
		} else if (BigDecimal.class == clazz) {
			return Double.parseDouble(((BigDecimal) src).toString());
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to double");
	}

	public static BigInteger castToBigInteger(Object src) {
		Class<?> clazz = src.getClass();

		if (BigInteger.class == clazz) {
			return (BigInteger) src;
		}

		if ((String.class == clazz) || (Byte.class == clazz) || (Short.class == clazz) || (Integer.class == clazz) || (Long.class == clazz)) {
			return new BigInteger(src.toString());
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to BigInteger");
	}

	public static BigDecimal castToBigDecimal(Object src) {
		Class<?> clazz = src.getClass();

		if (BigDecimal.class == clazz) {
			return (BigDecimal) src;
		}

		if ((String.class == clazz) || (Byte.class == clazz) || (Short.class == clazz) || (Integer.class == clazz) || (Long.class == clazz) || (Float.class == clazz)
				|| (Double.class == clazz)) {
			return new BigDecimal(src.toString());
		} else if (BigInteger.class == clazz) {
			return new BigDecimal((BigInteger) src);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to BigDecimal");
	}

	public static String castToString(Object src) {
		return castToString(src, null);
	}

	public static String castToString(Object src, String format) {
		// // byte,short,int,long,float,double,string
		Class<?> clazz = src.getClass();

		if ((String.class == clazz) || (Byte.class == clazz) || (Short.class == clazz) || (Integer.class == clazz) || (Long.class == clazz) || (Float.class == clazz)
				|| (Double.class == clazz) || (Boolean.class == clazz) || (Character.class == clazz) || (BigInteger.class == clazz) || (BigDecimal.class == clazz)) {
			return src.toString();
		}

		if ((Date.class == clazz)) {
			return getDateTimeString((Date) src, (null != format) ? format : DataType.DATETIME_FORMAT);
		} else if (java.sql.Date.class == clazz) {
			return getDateString((java.sql.Date) src, (null != format) ? format : DataType.DATE_FORMAT);
		} else if (java.sql.Time.class == clazz) {
			return getTimeString((java.sql.Time) src, (null != format) ? format : DataType.TIME_FORMAT);
		} else if (java.sql.Timestamp.class == clazz) {
			return getTimestampString((java.sql.Timestamp) src, (null != format) ? format : DataType.DATETIME_FORMAT);
		}

		throw new NumberFormatException("unsupported types '" + clazz + "' to String");
	}

	// date and time 

	public static Date castToDateTime(Object src) {
		return castToDateTime(src, null);
	}

	public static Date castToDateTime(Object src, String format) {
		Class<?> clazz = src.getClass();
		if (Date.class == clazz) {
			return (Date) src;
		}
		if (java.sql.Date.class == clazz) {
			return new Date(((java.sql.Date) src).getTime());
		}
		if (java.sql.Time.class == clazz) {
			return new Date(((java.sql.Time) src).getTime());
		}
		if (java.sql.Timestamp.class == clazz) {
			return new Date(((java.sql.Timestamp) src).getTime());
		}
		if (Long.class == clazz) {
			return new Date(((Long) src).longValue());
		}
		if (String.class == clazz) {
			if (null == format) {
				return parseDateTime((String) src);
			}
			return parseDateTime((String) src, format);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to Date");
	}

	public static java.sql.Date castToSqlDate(Object src) {
		return castToSqlDate(src, null);
	}

	public static java.sql.Date castToSqlDate(Object src, String format) {
		Class<?> clazz = src.getClass();
		if (java.sql.Date.class == clazz) {
			return (java.sql.Date) src;
		}
		if (Date.class == clazz) {
			return new java.sql.Date(((Date) src).getTime());
		}
		if (java.sql.Time.class == clazz) {
			return new java.sql.Date(((java.sql.Time) src).getTime());
		}
		if (java.sql.Timestamp.class == clazz) {
			return new java.sql.Date(((java.sql.Timestamp) src).getTime());
		}
		if (Long.class == clazz) {
			return new java.sql.Date(((Long) src).longValue());
		}
		if (String.class == clazz) {
			if (null == format) {
				return parseDate((String) src);
			}
			return parseDate((String) src, format);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to java.sql.Date");
	}

	public static java.sql.Time castToSqlTime(Object src) {
		return castToSqlTime(src, null);
	}

	public static java.sql.Time castToSqlTime(Object src, String format) {
		Class<?> clazz = src.getClass();
		if (java.sql.Time.class == clazz) {
			return (java.sql.Time) src;
		}
		if (Date.class == clazz) {
			return new java.sql.Time(((Date) src).getTime());
		}
		if (java.sql.Date.class == clazz) {
			return new java.sql.Time(((java.sql.Date) src).getTime());
		}
		if (java.sql.Timestamp.class == clazz) {
			return new java.sql.Time(((java.sql.Timestamp) src).getTime());
		}
		if (Long.class == clazz) {
			return new java.sql.Time(((Long) src).longValue());
		}
		if (String.class == clazz) {
			if (null == format) {
				return parseTime((String) src);
			}
			return parseTime((String) src, format);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to java.sql.Time");
	}

	public static java.sql.Timestamp castToSqlTimestamp(Object src) {
		return castToSqlTimestamp(src, null);
	}

	public static java.sql.Timestamp castToSqlTimestamp(Object src, String format) {
		Class<?> clazz = src.getClass();
		if (java.sql.Timestamp.class == clazz) {
			return (java.sql.Timestamp) src;
		}
		if (Date.class == clazz) {
			return new java.sql.Timestamp(((Date) src).getTime());
		}
		if (java.sql.Date.class == clazz) {
			return new java.sql.Timestamp(((java.sql.Date) src).getTime());
		}
		if (java.sql.Time.class == clazz) {
			return new java.sql.Timestamp(((java.sql.Time) src).getTime());
		}
		if (Long.class == clazz) {
			return new java.sql.Timestamp(((Long) src).longValue());
		}
		if (String.class == clazz) {
			if (null == format) {
				return parseTimestamp((String) src);
			}
			return parseTimestamp((String) src, format);
		}
		throw new NumberFormatException("unsupported types '" + clazz + "' to java.sql.Timestamp");
	}
}
