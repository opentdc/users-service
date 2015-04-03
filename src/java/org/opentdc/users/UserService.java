/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Arbalo AG
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.opentdc.users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.opentdc.service.GenericService;
import org.opentdc.service.exception.DuplicateException;
import org.opentdc.service.exception.NotFoundException;

@Path("/api/users")
public class UserService extends GenericService {

	private static final Logger logger = Logger.getLogger(UserService.class.getName());
	private static Map<String, UserData> data = new HashMap<String, UserData>();

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserData> list() {
		List<UserData> _list = getData();
		logger.info("list() -> " + getDataSize() + " values");
		return _list;
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserData create(UserData dataObj) throws DuplicateException {
		if (getData(dataObj.getId()) != null) {
			throw new DuplicateException();
		}

		// TODO: do we need to validate dataObj with BeanPropertyBindingResult ?
		// see example in LocationService

		setNewID(dataObj);
		storeData(dataObj);
		return dataObj;
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public UserData read(@PathParam("id") String id) throws NotFoundException {
		UserData _dataObj = getData(id);
		if (_dataObj == null) {
			throw new NotFoundException();
		}
		// response.setId(id);
		logger.info("read(" + id + "): " + _dataObj);
		return _dataObj;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserData update(UserData dataObj) {
		if (getData(dataObj.getId()) == null) {
			setNewID(dataObj);
		}
		storeData(dataObj);
		return dataObj;
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") String id) throws NotFoundException {
		UserData _dataObj = getData(id);

		if (_dataObj == null) {
			throw new NotFoundException();
		}
		removeData(id);
		logger.info("delete(" + id + ")");
	}

	@DELETE
	@Path("/")
	public void deleteAll() {
		clearData();
		logger.info("all data deleted");
	}

	@GET
	@Path("/count")
	public int count() {
		return getDataSize();
	}

	// we encapsulate the access to the persistency in order to prepare for
	// pluggable persistence implementations
	// TODO: create external interface and implementations for internal,
	// mongodb, opencrx...
	private void setNewID(UserData dataObj) {
		String _id = UUID.randomUUID().toString();
		dataObj.setId(_id);
	}

	private void storeData(UserData dataObj) {
		data.put(dataObj.getId(), dataObj);
	}

	private UserData getData(String id) {
		return data.get(id);
	}

	private List<UserData> getData() {
		return new ArrayList<UserData>(data.values());
	}

	private int getDataSize() {
		int _retVal = 0;
		if (data != null) {
			_retVal = data.size();
		}
		return _retVal;
	}

	private void removeData(String id) {
		data.remove(id);
	}

	private void clearData() {
		data.clear();
	}
}
