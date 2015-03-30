package org.opentdc.users.exception;

import javax.ws.rs.core.Response.Status;

public class NotFoundException extends BaseException {

	private static final long serialVersionUID = 4518221113L;

	// see java.io.Serializable:
	// version number for serializable class
	// reserved in uids.txt: 45182.2.1.1.1.3 # NotFoundException

	public NotFoundException() {

		super(Status.NOT_FOUND, "Location not found");

	}

}
