package org.xson.common.object;

import java.io.Serializable;

public interface DataType extends Serializable {

	int		BYTE_TYPE				= 1;						// B
	int		SHORT_TYPE				= 2;						// H
	int		INT_TYPE				= 3;						// I
	int		LONG_TYPE				= 4;						// L
	int		FLOAT_TYPE				= 5;						// F
	int		DOUBLE_TYPE				= 6;						// D
	int		CHAR_TYPE				= 7;						// C
	int		BOOLEAN_TYPE			= 8;						// O
	int		STRING_TYPE				= 9;						// S
	int		XCO_TYPE				= 10;						// X
	int		DATE_TYPE				= 11;						// A
	int		SQLDATE_TYPE			= 12;						// E
	int		SQLTIME_TYPE			= 13;						// G
	int		TIMESTAMP_TYPE			= 14;						// J
	int		BIGINTEGER_TYPE			= 15;						// K
	int		BIGDICIMAL_TYPE			= 16;						// M

	int		BYTE_ARRAY_TYPE			= 21;						// BA
	int		SHORT_ARRAY_TYPE		= 22;
	int		INT_ARRAY_TYPE			= 23;
	int		LONG_ARRAY_TYPE			= 24;
	int		FLOAT_ARRAY_TYPE		= 25;
	int		DOUBLE_ARRAY_TYPE		= 26;
	int		CHAR_ARRAY_TYPE			= 27;
	int		BOOLEAN_ARRAY_TYPE		= 28;
	int		STRING_ARRAY_TYPE		= 29;
	int		XCO_ARRAY_TYPE			= 30;
	int		DATE_ARRAY_TYPE			= 31;
	int		SQLDATE_ARRAY_TYPE		= 32;
	int		SQLTIME_ARRAY_TYPE		= 33;
	int		TIMESTAMP_ARRAY_TYPE	= 34;

	int		BYTE_LIST_TYPE			= 41;						// BL
	int		SHORT_LIST_TYPE			= 42;
	int		INT_LIST_TYPE			= 43;
	int		LONG_LIST_TYPE			= 44;
	int		FLOAT_LIST_TYPE			= 45;
	int		DOUBLE_LIST_TYPE		= 46;
	int		CHAR_LIST_TYPE			= 47;
	int		BOOLEAN_LIST_TYPE		= 48;
	int		STRING_LIST_TYPE		= 49;
	int		XCO_LIST_TYPE			= 50;
	int		DATE_LIST_TYPE			= 51;
	int		SQLDATE_LIST_TYPE		= 52;
	int		SQLTIME_LIST_TYPE		= 53;
	int		TIMESTAMP_LIST_TYPE		= 54;

	int		BYTE_SET_TYPE			= 61;						// BS
	int		SHORT_SET_TYPE			= 62;
	int		INT_SET_TYPE			= 63;
	int		LONG_SET_TYPE			= 64;
	int		FLOAT_SET_TYPE			= 65;
	int		DOUBLE_SET_TYPE			= 66;
	int		CHAR_SET_TYPE			= 67;
	int		BOOLEAN_SET_TYPE		= 68;
	int		STRING_SET_TYPE			= 69;
	int		XCO_SET_TYPE			= 70;
	int		DATE_SET_TYPE			= 71;
	int		SQLDATE_SET_TYPE		= 72;
	int		SQLTIME_SET_TYPE		= 73;
	int		TIMESTAMP_SET_TYPE		= 74;

	// List, Set

	String	PROPERTY_K				= "K";
	String	PROPERTY_V				= "V";

	String	DATETIME_FORMAT			= "yyyy-MM-dd HH:mm:ss";
	String	DATE_FORMAT				= "yyyy-MM-dd";
	String	TIME_FORMAT				= "HH:mm:ss";

	// public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	// public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
}
