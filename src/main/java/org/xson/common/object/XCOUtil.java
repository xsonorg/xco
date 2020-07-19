package org.xson.common.object;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

}
