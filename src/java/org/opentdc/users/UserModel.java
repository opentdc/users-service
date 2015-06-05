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

import java.util.Comparator;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * UserData
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserModel {
	
	private String id;			// sortable
	private String loginId;
	private String contactId;   // contains all address data
	// TODO: List<Role> roles
	private String hashedPassword;
	private String salt;
	// TODO: authTypes: github, twitter, facebook, google
	private Date createdAt;
	private String createdBy;
	private Date modifiedAt;
	private String modifiedBy;

	public UserModel() {
	}

	public UserModel(String loginId, String contactId, String hashedPassword, String salt) {
		this.loginId = loginId;
		this.contactId = contactId;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the loginID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * @param loginID
	 *            the loginID to set
	 */
	public void setLoginId(String loginID) {
		this.loginId = loginID;
	}

	/**
	 * @return the hashedPassword
	 */
	public String getHashedPassword() {
		return hashedPassword;
	}

	/**
	 * @param hashedPassword
	 *            the hashedPassword to set
	 */
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/******************************* Comparator *****************************/
	public static Comparator<UserModel> UserComparator = new Comparator<UserModel>() {

		public int compare(UserModel obj1, UserModel obj2) {
			if (obj1.getId() == null) {
				return -1;
			}
			if (obj2.getId() == null) {
				return 1;
			}

			String _attr1 = obj1.getId();
			String _attr2 = obj2.getId();

			// ascending order
			return _attr1.compareTo(_attr2);

			// descending order
			// return _attr2.compareTo(_attr1);
		}
	};
}