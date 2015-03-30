package org.opentdc.users.exception;

import javax.ws.rs.core.Response.Status;

/**
 * Indicates if an object is already created
 * 
 * @author Bruno Kaiser
 */
public class DuplicateException extends BaseException {

	private static final long serialVersionUID = 4518221112L;

	// see java.io.Serializable:
	// version number for serializable class
	// reserved in uids.txt: 45182.2.1.1.1.2 # DuplicateException

	public DuplicateException() {

		super(Status.CONFLICT, "Object is already stored");

	}

}
