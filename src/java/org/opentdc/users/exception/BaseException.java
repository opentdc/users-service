package org.opentdc.users.exception;

import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.opentdc.users.UserData;

public class BaseException extends WebApplicationException {

	private static final long serialVersionUID = 4518221111L;
	// see java.io.Serializable:
	// version number for serializable class
	// reserved in uids.txt: 45182.2.1.1.1.1 # BaseException

	private ExceptionData exceptionData;

	public BaseException(Status status, String message,
			Map<String, UserData> data) {

		super(Response.status(status).entity(new ExceptionData(message, data))
				.build());

		setExceptionData((ExceptionData) getResponse().getEntity());

	}

	public BaseException(Status status, String message) {
		this(status, message, null);
	}

	@Override
	public String getMessage() {
		return getExceptionData().getMessage();
	}

	public ExceptionData getExceptionData() {
		return exceptionData;
	}

	public void setExceptionData(ExceptionData exceptionData) {
		this.exceptionData = exceptionData;
	}

}
