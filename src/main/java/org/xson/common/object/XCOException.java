package org.xson.common.object;

public class XCOException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;

	public XCOException() {
		super();
	}

	public XCOException(String message) {
		super(message);
	}

	public XCOException(String message, Throwable cause) {
		super(message, cause);
	}

	public XCOException(Throwable cause) {
		super(cause);
	}

}
