package org.opentdc.users;

import java.util.Formatter;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Present
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class UserData {
	private String id;
	private String loginID;
	// TODO: private Contact contact; // contains all address data
	// TODO: List<Role> roles
	private String hashedPassword;
	private String salt;

	// TODO: authTypes: github, twitter, facebook, google

	public UserData() {
		this.loginID = "undefined";
		this.hashedPassword = "change_on_install";
		this.salt = "this is a very bad salt";
	}

	public UserData(String loginID, String hashedPassword, String salt) {
		this.loginID = loginID;
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
	public String getLoginID() {
		return loginID;
	}

	/**
	 * @param loginID
	 *            the loginID to set
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder _sb = new StringBuilder();
		Formatter _formatter = new Formatter(_sb, Locale.US);
		_formatter.format(
				"User [ID:%s\nloginID:%s\nhashedPassword:%s\nsalt:%s\n",
				getId(), getHashedPassword(), getSalt());
		_formatter.close();
		return _sb.toString();
	}
}